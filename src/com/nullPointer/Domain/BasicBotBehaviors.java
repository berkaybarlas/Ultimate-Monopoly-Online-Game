package com.nullPointer.Domain;

import com.nullPointer.Domain.Controller.CommunicationController;
import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.Cards.Card;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;
import com.nullPointer.Domain.Model.Square.PropertySquare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public abstract class BasicBotBehaviors {
    CommunicationController communicationController = CommunicationController.getInstance();
    PlayerController playerController = PlayerController.getInstance();
    GameEngine gameEngine = GameEngine.getInstance();

    protected void buyAction(Player current) {
        communicationController.sendClientMessage("purchase");
        //yieldTurn();
    }

    protected void sendMessage(String message) {
        communicationController.sendClientMessage("message/" + playerController.getCurrentPlayer().getName() + " : " + message);
    }

    protected void tryImproving(Player current, String msg) {

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
                }
            }
        }
    }

    protected void improveAction(Player current, PropertySquare p) {
        sendMessage("I decided to improve!");
        gameEngine.improveSelectedProperty(p);
        //yieldTurn();
    }

    protected void cardAction(Player current) {
        Random rand = new Random();
        ArrayList<Card> cards = current.getCardList();
        if (!cards.isEmpty()) {
            Card randCard = cards.get(rand.nextInt(cards.size()));
            sendMessage("I decided to play a card!");
            communicationController.sendClientMessage("card/play");                  // Will be updated once playCard is updated
        } else {
            sendMessage("It seems that I have nothing to do. I should yield my turn now.");
            //yieldTurn();
        }
    }
}
