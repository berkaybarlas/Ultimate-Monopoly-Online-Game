package com.nullPointer.Domain.Model.Bot;

import com.nullPointer.Domain.Controller.CommunicationController;
import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;
import com.nullPointer.Domain.Observer;

import java.util.*;

public class BotBehaviour implements Observer {

    private GameEngine gameEngine = GameEngine.getInstance();
    private CommunicationController communicationController = CommunicationController.getInstance();
    private boolean lazyBot = false;
    private boolean randomBot = false;
    private boolean semiIntelligentBot = true;
    private ArrayList<String> acceptibleMessages = new ArrayList<String>(Arrays.asList("rollDice", "endTurn", "drawCard", "buy", "doubles", "empty"));
    private PlayerController playerController = PlayerController.getInstance();

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

    @Override
    public void onEvent(String message) {
        if (gameEngine.isBot() && gameEngine.isMyTurn()) {
            if (acceptibleMessages.contains(message)) {
                makeBotAction(message);
            }
        }
    }

    public void makeBotAction(String message) {

        try {
            Thread.sleep(1000);


            setBehaviour(playerController.getCurrentPlayer());

            BotBehaviourStrategy botBehaviourStrategy;

            if (isRandomBot()) {
                //randomAction(message);
                botBehaviourStrategy = new RandomBotStrategy();
            } else if (isSemiIntelligentBot()) {
                // semiIntelligentAction(message);
                botBehaviourStrategy = new SemiIntelligentBotStrategy();
            } else {
                botBehaviourStrategy = new LazyBotStrategy();
            }

            //  String botName = gameEngine.getPlayerController().getCurrentPlayer().getName();
            //    System.out.println(botName + "is a " + ((isLazyBot()) ? "lazy bot" : ((isRandomBot()) ? "random bot" : "semi-intelligent bot")));
            if (message.equals("endTurn")) {
                Thread.sleep(1000);
                System.out.println("[BotBehavior]: end turn.");
                yieldTurn();
            } else if (message.equals("rollDice")) {
                if (gameEngine.getRoll3())
                    gameEngine.roll3Dice();
                else
                    gameEngine.rollDice();

                communicationController.sendClientMessage("dice/" + gameEngine.getLastDiceValues());
            } else if (message.equals("doubles")) {
                gameEngine.rollDice();
                communicationController.sendClientMessage("dice/" + gameEngine.getLastDiceValues());
                System.out.println("[BotBehavior]: double.");
            } else if (message.equals("drawCard")) {
                communicationController.sendClientMessage("card/draw");
            } else if (message.contains("buy") || message.contains("improve") || message.contains("gained") || message.contains("empty")) {
                if (message.contains("buy")) {
                    botBehaviourStrategy.buyAction();
                } else if (message.contains("improve")) {
                    botBehaviourStrategy.improveAction();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}