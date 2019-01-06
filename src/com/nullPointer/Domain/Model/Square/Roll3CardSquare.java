package com.nullPointer.Domain.Model.Square;

import com.nullPointer.Domain.Controller.CommunicationController;
import com.nullPointer.Domain.Controller.MoneyController;
import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.Cards.Roll3;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;
import com.nullPointer.Domain.Model.RegularDie;

import java.util.ArrayList;
import java.util.Collections;

public class Roll3CardSquare extends Square {

    private MoneyController moneyController = MoneyController.getInstance();
    public Roll3CardSquare(String name, String type) {
        super(name, type);
    }


    /**
     * @param gameEngine engine that controls the system
     * @requires gameEngine != null
     * @modifies all players who are in possession of a Roll3 card.
     * @effects increases money of all players by amounts decided by Roll3 rules.
     * 1 match: +50
     * 2 matches: +200
     * 3 matches: if currentPlayer has 3 matches, +1500; else +1000
     */
    @Override
    public void evaluateSquare(GameEngine gameEngine) {
        gameEngine.setRoll3(true);
        Player currentPlayer = PlayerController.getInstance().getCurrentPlayer();
        currentPlayer.addRoll3Card((Roll3)gameEngine.getDomainBoard().getRoll3Cards().poll());
        gameEngine.publishEvent("rollDice");
        
        RegularDie die = gameEngine.getRegularDie();
        ArrayList<Integer> diceValues = die.getLastValues();
        while (diceValues.size() != 3) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(diceValues);
        System.out.println("[Roll3CardSquare] Dice Values: " + diceValues.toString());
        ArrayList<Player> players = gameEngine.getPlayerController().getPlayers();

        Player current = gameEngine.getPlayerController().getCurrentPlayer();

        for (int i = 0; i < players.size(); i++) {
            int totalMatch = 0;
            totalMatch = checkRoll3ForAPlayer(players.get(i), diceValues);
            if (totalMatch == 1) {
                moneyController.increaseMoney(players.get(i), 50);
                System.out.println("[Roll3CardSquare] " + players.get(i).getName() + " gained " + 50);
            } else if (totalMatch == 2) {
                moneyController.increaseMoney(players.get(i), 200);
                System.out.println("[Roll3CardSquare] " + players.get(i).getName() + " gained " + 200);
            } else if (totalMatch == 3) {
                if (players.get(i).equals(current)) {
                    moneyController.increaseMoney(players.get(i), 1500);
                    System.out.println("[Roll3CardSquare] " + players.get(i).getName() + " gained " + 1500);
                } else {
                    moneyController.increaseMoney(players.get(i), 1000);
                    System.out.println("[Roll3CardSquare] " + players.get(i).getName() + " gained " + 1000);
                }
            }
        }
        gameEngine.setRoll3(false);
        gameEngine.publishEvent("empty");
        CommunicationController.getInstance().sendClientMessage(gameEngine.getPlayerController());
    }


    private int checkRoll3ForAPlayer(Player player, ArrayList<Integer> diceValues) {
        int total = 0;
        ArrayList<Integer> alreadyExists = new ArrayList<Integer>();
        ArrayList<Roll3> cards = player.getRoll3Cards();
        for (int i = 0; i < cards.size(); i++) {
            Roll3 card = cards.get(i);
            for (int k = 0; k < diceValues.size(); k++) {
                if (card.getValues().contains(diceValues.get(k)) && !alreadyExists.contains(diceValues.get(k))) {
                    total++;
                    alreadyExists.add(diceValues.get(k));
                }
            }
        }
        return total;
    }

}
