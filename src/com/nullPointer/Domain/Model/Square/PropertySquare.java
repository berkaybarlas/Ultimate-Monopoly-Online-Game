package com.nullPointer.Domain.Model.Square;

import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;

/**
 * @overview    This class represents the Property Squares that form the majority of squares in the monopoly game.
 *              These squares can be bought, upgraded, down graded, modified, mortgaged, etc.
 *
 */
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



    /**
     * @requires   -
     * @modifies   -
     * @effects    Calls the calculateRent method in order to calculate the rent according to the
     *              required parameters.
     */
	public int getRent() {
		return calculateRent();
	}

    /**
     * @requires    rentList is not null.
     *              rentFactor is nonzero.
     * @modifies    -
     * @effects     Calculates the rent according to the rentList index and retFactor
     *              of this PropertySquare and returns it.
     */
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

    /**
     * @requires    -
     * @modifies    rentListIndex
     * @effects     Sets the rentListIndex of this PropertySquare, so that an upgraded
     *              rent can be obtained with the calculateRent() method.
     */
    public void improve() {
        //change inventory
        if (canBeImproved()) setRentListIndex(rentListIndex + 1);
    }
    /**
     * @requires    -
     * @modifies    rentListIndex
     * @effects     Sets the rentListIndex of this PropertySquare, so that a downgraded
     *              rent can be obtained with the calculateRent() method.
     */
    public void downgrade() {
        //change inventory
        if (canBeDowngraded()) setRentListIndex(rentListIndex - 1);
    }

    public int getPrice() {
        return price;
    }

    public int getHousePrice()
    {
        return rentList[8];
    }

    public void setPrice(int newPrice) {
        this.price = newPrice;
    }

    public Player getOwner() {
        return owner;
    }

    /**
     * @param owner
     * @requires    -
     * @modifies    owner
     * @effects     Sets the owner Player of this PropertySquare.
     */
    public void setOwner(Player owner) {
        if (!isOwned()) this.owner = owner;
    }

    public int getRentFactor() {
        return rentFactor;
    }

    public void setRentFactor(int rentFactor) {
        this.rentFactor = rentFactor;
    }

    public int getRentListIndex() {
        return rentListIndex;
    }

    public void setRentListIndex(int rentListIndex) {
        this.rentListIndex = rentListIndex;
    }

    public int[] getRentList() {
        return rentList;
    }

    public void setRentList(int[] rentList) {
        this.rentList = rentList;
    }

    public boolean isOwned() {
        return owner != null;
    }

    /**
     * @param       gameEngine
     * @requires    currentPlayer is not null.
     * @modifies    currentPlayer
     * @effects     No money transfer occurs if the currentPlayer is the owner of this PropertySquare.
     *              If the currentPlayer is not the owner of this propertySquare, it pays the rent.
     *              if the currentPlayer does not have enough money to pay this PropertySquare's rent,
     *              it goes bankrupt.
     */
    @Override
    public void evaluateSquare(GameEngine gameEngine) {
        if (this.getOwner() == null) {
            gameEngine.publishEvent("buy");
        } else {
            Player currentPlayer = gameEngine.getPlayerController().getCurrentPlayer();
            if(!this.getOwner().getName().equals(currentPlayer.getName())) {
                gameEngine.payRent(currentPlayer, this.getOwner() , this.getRent());
                gameEngine.publishEvent("message/" + "[System]: " + currentPlayer.getName()+ " paid rent to " + this.getOwner().getName());
            }
        }

    }

    public boolean repOk() {
        if(this.getName() != null && price > 0 && color != null && rentFactor > 0 && rentList != null && rentList.length == 8) {
            for(int rentPrice : this.getRentList()) {
                if(rentPrice <= 0) return false;
            }
            return  true;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.getName() + ", " + this.getColor() + "( Current rent: " + this.getRent() + ")";
    }
}
