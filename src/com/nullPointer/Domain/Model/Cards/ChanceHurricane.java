package com.nullPointer.Domain.Model.Cards;

import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Square.PropertySquare;
import com.nullPointer.Domain.Model.Square.Square;

public class ChanceHurricane extends ChanceCard {

    public ChanceHurricane(String title, boolean isImmediate) {
        super(title, isImmediate);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void playCard(GameEngine gameEngine) {

        while (gameEngine.getChosenSquareIndex() == -1) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("[Hurricane Card] Square: " + gameEngine.getDomainBoard().getSquareAt(gameEngine.getChosenSquareIndex()));
        System.out.println("[Hurricane Card] Remove 1 House from each property in any players 1 color group. (Downgrade Skyscrapers to Hotels; Hotels to 4 houses.) ");
        //Remove 1 House from each property in any players 1 color group. (Downgrade Skyscrapers to Hotels; Hotels to 4 houses.)
        //in the end of the turn
        Square currentSquare = gameEngine.getDomainBoard().getSquareAt(gameEngine.getChosenSquareIndex());
        PropertySquare propertySquare = ((PropertySquare) currentSquare);
        if (propertySquare.isOwned()) {
            if (propertySquare.numHouses() > 0 || propertySquare.hasHotel() || propertySquare.hasSkyscraper())
                propertySquare.downgrade();
        }
        gameEngine.setSquareUnselected();

    }

}
