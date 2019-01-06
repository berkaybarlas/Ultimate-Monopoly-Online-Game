package com.nullPointer.Domain.Model.Bot;

import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.Player;
import com.nullPointer.Domain.Model.Square.PropertySquare;

import java.util.ArrayList;
import java.util.HashMap;

public class RandomBotStrategy extends BasicBotBehaviors implements BotBehaviourStrategy {
    private PlayerController playerController = PlayerController.getInstance();


    @Override
    public void buyAction() {
        if (rand.nextBoolean()) {
            communicationController.sendClientMessage("purchase");
            sendMessage("You guys will pay for this place!");
        } else {
            sendMessage("I'm not buying B*tches!");
        }
        randomMessage();
    }

    @Override
    public void improveAction() {
        Player currentPlayer = playerController.getCurrentPlayer();

        ArrayList<PropertySquare> props = new ArrayList<PropertySquare>();

        if (currentPlayer.getPropertySquares() != null) {
            HashMap<String, ArrayList<PropertySquare>> deeds = currentPlayer.getPropertyCardsMap();
            for (String s : deeds.keySet()) {
                ArrayList<PropertySquare> temp = deeds.get(s);
                if (temp.size() >= 2) {
                    props.addAll(temp);
                }
            }
        }

        PropertySquare randProp = props.get(rand.nextInt(props.size()));
        sendMessage("I decided to improve!");
        gameEngine.improveSelectedProperty(randProp);
    }

    private void tryPlayingCard(Player current) {
        cardAction(current);
    }


}
