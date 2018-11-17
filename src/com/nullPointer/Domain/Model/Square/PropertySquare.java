package com.nullPointer.Domain.Model.Square;

import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;

public class PropertySquare extends Square {
    private Player owner = null;
    private int price;
    private String color;
    private int rentFactor;
    /*
     * About priceList & priceListIndex:
     * The list priceList keeps all the available info about the money on each property (base rent, rent after n houses, mortgage value etc.)
     * priceListIndex is the index used to access the list.
     * priceList(0) = base rent
     * priceList(1) = rent after 1 house
     * priceList(2) =   "	"	2	"
     * priceList(3) = 	"	"	3	"
     * priceList(4) = 	"	"	4	"
     * priceList(5) = rent with hotel
     * priceList(6) = rent with skyscraper
     * priceList(7) = mortgage value
     * ps: not sure if mortgage value should be kept in that list
     */
    private int rentListIndex;
    private int[] rentList;
    private boolean isMortgaged;
    public PropertySquare(String n, String t, int p, String color, int[] rentList) {
        super(n, t);
        setOwner(null);
        setPrice(p);
        setRentList(rentList);
        setMortgage(false);
        setRentListIndex(0);
        setRentFactor(1);
        setColor(color);
    }

	
	
	
	public int getRent() {
		return calculateRent();
	}
	private int calculateRent()
	{
		if (getRentListIndex() <= 6) return rentFactor * rentList[getRentListIndex()];
		else return 0;
	}

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int numHouses()
    {
        if (getRentListIndex() <= 4) return getRentListIndex();
        else return 0;
    }
    public boolean hasHotel()
    {
        return (getRentListIndex() == 5);
    }
    public boolean hasSkyscraper()
    {
        return (getRentListIndex() == 6);
    }

    public boolean getMortgaged()
    {
        return isMortgaged;
    }

    public void mortgage() {
        if (!isMortgaged) {
            setMortgage(true);
            rentListIndex = 7;
        }
    }

    public void removeMortgage() {
        if (isMortgaged) {
            setMortgage(false);
            rentListIndex = 0;
        }
    }

    private void setMortgage(boolean mortgageVal) {
        this.isMortgaged = mortgageVal;
    }

    public boolean canBeImproved() {
        return (getRentListIndex() < 6);
    }

    public boolean canBeDowngraded() {
        return (getRentListIndex() > 0);
    }

    public void improve() {
        //change inventory
        if (canBeImproved()) setRentListIndex(rentListIndex + 1);
    }

    public void downgrade() {
        //change inventory
        if (canBeDowngraded()) setRentListIndex(rentListIndex - 1);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int newPrice) {
        this.price = newPrice;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        if (!isOwned()) this.owner = owner;
    }

    public int getRentFactor() {
        return rentFactor;
    }

    public void setRentFactor(int rentFactor) {
        this.rentFactor = rentFactor;
    }

    private int getRentListIndex() {
        return rentListIndex;
    }

    private void setRentListIndex(int rentListIndex) {
        this.rentListIndex = rentListIndex;
    }

    public int[] rentList() {
        return rentList;
    }

    public void setRentList(int[] rentList) {
        this.rentList = rentList;
    }

    public boolean isOwned() {
        return owner != null;
    }

    @Override
    public void evaluateSquare(GameEngine gameEngine) {
        if (this.getOwner() == null) {
            gameEngine.publishEvent("buy");
        } else {
            Player currentPlayer = gameEngine.getPlayerController().getCurrentPlayer();
            gameEngine.payRent(currentPlayer, this.getOwner() , this.getRent());
            System.out.println("CurrentPlayer: " + currentPlayer.getName()+ " paid rent to " + this.getOwner());
            gameEngine.nextTurn();
        }

    }

    @Override
    public String toString() {
        return getName();
    }

}
