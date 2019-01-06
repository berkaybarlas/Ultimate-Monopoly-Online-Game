package com.nullPointer.Domain.Model.Bot;

import com.nullPointer.Domain.Model.Player;
import com.nullPointer.Domain.Model.Square.PropertySquare;
import com.nullPointer.Domain.Model.Square.RailRoadTransitStationsSquare;
import com.nullPointer.Domain.Model.Square.Square;
import com.nullPointer.Domain.Model.Square.UtilitySquare;

import java.util.ArrayList;
import java.util.Iterator;

public class SemiIntelligentBotStrategy extends BasicBotBehaviors implements BotBehaviourStrategy  {
    @Override
    public void buyAction() {

        Player current = gameEngine.getPlayerController().getCurrentPlayer();

        sendMessage("I see that I can buy this place! Hmm, should I do that?");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        boolean doOthersHaveAnyPropertyFromThisColorGroup = false;
        Square currentProp = gameEngine.getDomainBoard().getSquareAt(current.getTargetPosition());
        //System.out.println("The property that " + current.getName() + " stands on right now is:   " + currentProp.getName());

        if (currentProp instanceof RailRoadTransitStationsSquare) {
            sendMessage("A railroad? Those places are very profitable. Must buy!");
            //buyAction(current);
        }

        if (currentProp instanceof UtilitySquare) {
            sendMessage("A utility square? I heard that those places pay off in many years! Must stay away!");
            tryImproving(current);
        } else if (currentProp instanceof PropertySquare) {
            if (currentProp.getName().equals("ST. CHARLES PLACE"))           // A card leads people here
            {
                sendMessage("St. Charles Place? That place is very profitable. Must buy!");
                buyAction(current);
            } else if (current.getPropertyCardsMap().get(((PropertySquare) currentProp).getColor()) != null)       // Current player has one of the properties of same colour, she can improve if she buys this!
            {
                sendMessage("It seems that I have bought a place from this colour group before. I better buy this too, I can then build houses!");
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
                        // System.out.println(p.getPropertyCardsMap().keySet());
                        // System.out.println("................................");
                        // System.out.println(((PropertySquare) currentProp).getColor());
                        if (p.getPropertyCardsMap().keySet().contains(((PropertySquare) currentProp).getColor())) {             //not currently working, hopefully will work with furkan's additions
                            System.out.println("TEST TESt");
                            doOthersHaveAnyPropertyFromThisColorGroup = true;
                        }
                    }
                }
                if (!doOthersHaveAnyPropertyFromThisColorGroup) {
                    sendMessage("It seems that nobody got a hold of this colour group yet. Might turn out valuable in the future!");
                    buyAction(current);
                } else {
                    sendMessage("Buying this place doesn't make sense. I better try to improve my existing properties.");
                }
            }
        }

    }

    @Override
    public void improveAction() {
        tryImproving(playerController.getCurrentPlayer());
    }

    private void tryPlayingCard(Player current) {
        cardAction(current);
    }
}
