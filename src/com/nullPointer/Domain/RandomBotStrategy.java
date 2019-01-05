package com.nullPointer.Domain;

import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.Cards.Card;
import com.nullPointer.Domain.Model.Player;
import com.nullPointer.Domain.Model.Square.PropertySquare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomBotStrategy extends BasicBotBehaviors implements BotBehaviourStrategy {
    private PlayerController playerController = PlayerController.getInstance();

    @Override
    public void buyAction() {

    }

    @Override
    public void improveAction() {
        
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
        Random rand = new Random();
        PropertySquare randProp = props.get(rand.nextInt(props.size()));
        sendMessage("I decided to improve!");
        gameEngine.improveSelectedProperty(randProp);
    }

    private void randomAction(String msg) {
        ArrayList<String> AvailableActions = new ArrayList<String>();

        //AvailableActions.add("Yield");

        Player current = playerController.getCurrentPlayer();


        if (!current.getCardList().isEmpty()) AvailableActions.add("PlayCard");

        if (msg.equals("buy")) AvailableActions.add("Buy");

        Random rand = new Random();

        int action = rand.nextInt(AvailableActions.size());

        String command = AvailableActions.get(action);
        System.out.println("Random bot decided to " + command);
        if (command.equals("Yield")) {
            // yieldTurn();
        } else if (command.equals("Improve")) {
            improveAction(current, props);
        } else if (command.equals("PlayCard")) {
            cardAction(current);
        } else if (command.equals("Buy")) {
            buyAction(current);
        }
    }

    private void tryPlayingCard(Player current) {
        cardAction(current);
    }

    private void improveAction(Player current, ArrayList<PropertySquare> props) {

        // yieldTurn();
    }
}
