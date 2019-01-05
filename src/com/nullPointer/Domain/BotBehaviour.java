package com.nullPointer.Domain;

import com.nullPointer.Domain.Controller.CommunicationController;
import com.nullPointer.Domain.Model.Cards.Card;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;
import com.nullPointer.Domain.Model.Square.PropertySquare;
import com.nullPointer.Domain.Model.Square.RailRoadTransitStationsSquare;
import com.nullPointer.Domain.Model.Square.Square;
import com.nullPointer.Domain.Model.Square.UtilitySquare;

import java.util.*;

public class BotBehaviour implements Observer {

    private GameEngine gameEngine = GameEngine.getInstance();
    private CommunicationController communicationController = CommunicationController.getInstance();
    private boolean lazyBot = false;
    private boolean randomBot = false;
    private boolean semiIntelligentBot = true;
    private ArrayList<String> acceptibleMessages = new ArrayList<String>(Arrays.asList("rollDice", "drawCard", "buy", "paid rent", "empty"));


    public BotBehaviour() {
        gameEngine.subscribe(this);
    }

    public void setBehaviour(Player p) {
        int n = p.getBotBehaviourNumber();
        if (n == 1) {
            setLazyBot(true);
            setRandomBot(false);
            setSemiIntelligentBot(false);
        } else if (n == 2) {
            setLazyBot(false);
            setRandomBot(true);
            setSemiIntelligentBot(false);
        } else if (n == 3) {
            setLazyBot(false);
            setRandomBot(false);
            setSemiIntelligentBot(true);
        } else {
            throw new IllegalArgumentException("Unknown behaviour index: " + n);
        }
    }

    public boolean isLazyBot() {
        return lazyBot;
    }

    public void setLazyBot(boolean lazyBot) {
        this.lazyBot = lazyBot;
    }

    public boolean isRandomBot() {
        return randomBot;
    }

    public void setRandomBot(boolean randomBot) {
        this.randomBot = randomBot;
    }

    public boolean isSemiIntelligentBot() {
        return semiIntelligentBot;
    }

    public void setSemiIntelligentBot(boolean semiIntelligentBot) {
        this.semiIntelligentBot = semiIntelligentBot;
    }

    private void yieldTurn() {
        System.out.println(gameEngine.getPlayerController().getCurrentPlayer().getName() + " decided to Yield its Turn!");
        communicationController.sendClientMessage("player/next");
    }

    private void randomAction(String msg) {
        ArrayList<String> AvailableActions = new ArrayList<String>();

        AvailableActions.add("Yield");

        Player current = gameEngine.getPlayerController().getCurrentPlayer();
        ArrayList<PropertySquare> props = new ArrayList<PropertySquare>();

        if (current.getPropertySquares() != null) {
            HashMap<String, ArrayList<PropertySquare>> deeds = current.getPropertyCardsMap();
            for (String s : deeds.keySet()) {
                ArrayList<PropertySquare> temp = deeds.get(s);
                if (temp.size() >= 2) {
                    props.addAll(temp);
                }
            }
            if (!props.isEmpty()) AvailableActions.add("Improve");
        }

        if (!current.getCardList().isEmpty()) AvailableActions.add("PlayCard");

        if (msg.equals("buy")) AvailableActions.add("Buy");

        Random rand = new Random();

        int action = rand.nextInt(AvailableActions.size());

        String command = AvailableActions.get(action);
        System.out.println("Random bot decided to " + command);
        if (command.equals("Yield")) {
            yieldTurn();
        } else if (command.equals("Improve")) {
            improveAction(current, props);
        } else if (command.equals("PlayCard")) {
            cardAction(current);
        } else if (command.equals("Buy")) {
            buyAction(current);
        }
    }

    private void semiIntelligentAction(String msg) {
        Player current = gameEngine.getPlayerController().getCurrentPlayer();

        System.out.println(current.getName() + " is thinking very hard right now, believe me.");
        if (msg.contains("buy")) {
            System.out.println(current.getName() + " sees that it can buy this place! Hmm, should it do that?");
            boolean doOthersHaveAnyPropertyFromThisColorGroup = false;
            Square currentProp = gameEngine.getDomainBoard().getSquareAt(current.getTargetPosition());
            System.out.println("The property that " + current.getName() + " stands on right now is:   " + currentProp.getName());
            if (currentProp instanceof RailRoadTransitStationsSquare) {
                System.out.println("A railroad? Those places are very profitable. Must buy!");
                buyAction(current);
            }
            if (currentProp instanceof UtilitySquare) {
                System.out.println("A utility square? I heard that those places pay off in many years! Must stay away!");
                tryImproving(current, msg);
            } else if (currentProp instanceof PropertySquare) {
                if (currentProp.getName().equals("ST. CHARLES PLACE"))           // A card leads people here
                {
                    System.out.println("St. Charles Place? That place is very profitable. Must buy!");
                    buyAction(current);
                } else if (current.getPropertyCardsMap().get(((PropertySquare) currentProp).getColor()) != null)       // Current player has one of the properties of same colour, she can improve if she buys this!
                {
                    System.out.println("It seems that I have bought a place from this colour group before. I better buy this too, I can then build houses!");
                    buyAction(current);
                } else                                                            // Nobody bought any property from this color group, might be a valuable investment!
                {
                    ArrayList<Player> others = new ArrayList<Player>();
                    others.addAll(gameEngine.getPlayerController().getPlayers());
                    Iterator<Player> iter = others.iterator();
                    while (iter.hasNext()) {
                        Player p = iter.next();
                        if (p.getName().equals(current.getName())) {
                            continue;
                        } else {
                            System.out.println(p.getPropertyCardsMap().keySet());
                            System.out.println("................................");
                            System.out.println(((PropertySquare) currentProp).getColor());
                            if (p.getPropertyCardsMap().keySet().contains(((PropertySquare) currentProp).getColor())) {             //not currently working, hopefully will work with furkan's additions
                                System.out.println("TEST TESt");
                                doOthersHaveAnyPropertyFromThisColorGroup = true;
                            }
                        }
                    }
                    if (!doOthersHaveAnyPropertyFromThisColorGroup) {
                        System.out.println("It seems that nobody got a hold of this colour group yet. Might turn out valuable in the future!");
                        buyAction(current);
                    } else {
                        System.out.println("Buying this place doesn't make sense. I better try to improve my existing properties.");
                        tryImproving(current, msg);
                    }
                }
            }
        } else {
            System.out.println("Well, I can't buy this thing. Let's see if I have any properties to improve.");
            tryImproving(current, msg);
        }
    }

    private void tryImproving(Player current, String msg) {

        boolean doIHaveAnyHousesAnywhere = false;
        ArrayList<PropertySquare> props = new ArrayList<PropertySquare>();

        if (current.getPropertySquares() != null) {
            HashMap<String, ArrayList<PropertySquare>> deeds = current.getPropertyCardsMap();
            if (!deeds.isEmpty()) {
                for (String s : deeds.keySet()) {
                    ArrayList<PropertySquare> temp = deeds.get(s);
                    if (temp.size() >= 2) {
                        props.addAll(temp);
                    }
                }
                if (!props.isEmpty()) {
                    for (PropertySquare p : props) {
                        if (p.numHouses() != 0)                  // If I already improved it, why not upgrade?
                        {
                            doIHaveAnyHousesAnywhere = true;
                            improveAction(current, p);
                            break;
                        }
                    }
                    if (!doIHaveAnyHousesAnywhere)              // If I haven't upgraded anything, I better start with the one with the smallest house pr
                    {
                        PropertySquare propertyWithTheSmallestHousePrice = props.get(0);
                        for (PropertySquare p : props) {
                            if (p.getHousePrice() < propertyWithTheSmallestHousePrice.getHousePrice()) {
                                propertyWithTheSmallestHousePrice = p;
                            }
                        }
                        improveAction(current, propertyWithTheSmallestHousePrice);
                    }
                } else {
                    tryPlayingCard(current);
                }
            } else {
                tryPlayingCard(current);
            }
        }
    }

    private void tryPlayingCard(Player current) {
        cardAction(current);
    }

    private void cardAction(Player current) {
        Random rand = new Random();
        ArrayList<Card> cards = current.getCardList();
        if (!cards.isEmpty()) {
            Card randCard = cards.get(rand.nextInt(cards.size()));
            System.out.println(current.getName() + " decided to play a card!");
            communicationController.sendClientMessage("card/play");                  // Will be updated once playCard is updated
        } else {
            System.out.println("It seems that I have nothing to do. I should yield my turn now.");
            yieldTurn();
        }
    }


    private void improveAction(Player current, ArrayList<PropertySquare> props) {
        Random rand = new Random();
        PropertySquare randProp = props.get(rand.nextInt(props.size()));
        System.out.println(current.getName() + " decided to improve!");
        gameEngine.improveSelectedProperty(randProp);
        yieldTurn();
    }

    private void improveAction(Player current, PropertySquare p) {
        System.out.println(current.getName() + " decided to improve!");
        gameEngine.improveSelectedProperty(p);
        yieldTurn();
    }

    private void buyAction(Player current) {
        System.out.println(current.getName() + " decided to buy!");
        communicationController.sendClientMessage("purchase");
        yieldTurn();
    }

    @Override
    public void onEvent(String message) {
        if (gameEngine.isBot() && gameEngine.isMyTurn()) {
            for (String type : acceptibleMessages) {
                if (message.contains(type)) {

                    Timer timer = new Timer();

                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            setBehaviour(gameEngine.getPlayerController().getCurrentPlayer());
                            String botName = gameEngine.getPlayerController().getCurrentPlayer().getName();
                            System.out.println(botName + "is a " + ((isLazyBot()) ? "lazy bot" : ((isRandomBot()) ? "random bot" : "semi-intelligent bot")));
                            System.out.println(botName + " is thinking about " + message);
//                            if(message.equals("endTurn"))
//                            {
//                                yieldTurn();
//                            }
                            if (message.equals("rollDice")) {
                                if (gameEngine.getRoll3())
                                    gameEngine.roll3Dice();
                                else gameEngine.rollDice();

                                System.out.println(botName + " must Roll the Dice!");
                                communicationController.sendClientMessage("dice/" + gameEngine.getLastDiceValues());

                            } else if (message.equals("drawCard")) {
                                System.out.println(botName + " must Draw a Card!");
                                communicationController.sendClientMessage("card/draw");
                                yieldTurn();

                            } else if (message.contains("buy") || message.contains("improve") || message.contains("paid rent") || message.contains("gained") || message.contains("empty")) {
                                if (isLazyBot()) {
                                    yieldTurn();
                                }
                                if (isRandomBot()) {
                                    randomAction(message);
                                }
                                if (isSemiIntelligentBot()) {
                                    semiIntelligentAction(message);
                                }
                            }
                        }
                    }, 3 * 1000);

                }

            }
        }

    }

}


