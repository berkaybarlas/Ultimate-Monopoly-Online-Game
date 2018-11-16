package com.nullPointer.Model.Square;

import java.util.ArrayList;

import com.nullPointer.Model.GameEngine;
import com.nullPointer.Model.Player;

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
    private int priceListIndex;
    private ArrayList<Integer> priceList;
    private boolean isMortgaged;

    public PropertySquare(String n, String t, int p, String color, ArrayList<Integer> priceList) {
        super(n, t);
        setOwner(null);
        setPrice(p);
        setPriceList(priceList);
        setMortgage(false);
        setPriceListIndex(0);
        setRentFactor(1);
        setColor(color);
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public int getRent() {
        return calculateRent();
    }

    private int calculateRent() {
        if (getPriceListIndex() <= 6) return rentFactor * priceList.get(getPriceListIndex());
        else return 0;
    }

    public int numHouses() {
        if (getPriceListIndex() <= 4) return getPriceListIndex();
        else return 0;
    }

    public boolean hasHotel() {
        return (getPriceListIndex() == 5);
    }

    public boolean hasSkyscraper() {
        return (getPriceListIndex() == 6);
    }

    public boolean getMortgaged() {
        return isMortgaged;
    }

    public void mortgage() {
        if (!isMortgaged) {
            setMortgage(true);
            priceListIndex = 7;
        }
    }

    public void removeMortgage() {
        if (isMortgaged) {
            setMortgage(false);
            priceListIndex = 0;
        }
    }

    private void setMortgage(boolean mortgageVal) {
        this.isMortgaged = mortgageVal;
    }

    public boolean canBeImproved() {
        return (getPriceListIndex() < 6);
    }

    public boolean canBeDowngraded() {
        return (getPriceListIndex() > 0);
    }

    public void improve() {
        //change inventory
        if (canBeImproved()) setPriceListIndex(priceListIndex + 1);
    }

    public void downgrade() {
        //change inventory
        if (canBeDowngraded()) setPriceListIndex(priceListIndex - 1);
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

    private int getPriceListIndex() {
        return priceListIndex;
    }

    private void setPriceListIndex(int priceListIndex) {
        this.priceListIndex = priceListIndex;
    }

    public ArrayList<Integer> getPriceList() {
        return priceList;
    }

    public void setRentList(ArrayList<Integer> priceList) {
        this.priceList = priceList;
    }

    public boolean isOwned() {
        return owner != null;
    }

    @Override
    public void evaluateSquare(GameEngine gameEngine) {
        if (this.getOwner() == null) {
            gameEngine.publishEvent("buy");
        } else {
            gameEngine.payRent(gameEngine.getPlayerController().getCurrentPlayer(), this.getOwner() , this.getRent());
            System.out.println("CurrentPlayer paid rent");
            gameEngine.nextTurn();
        }

    }

    @Override
    public String toString() {
        return getName();
    }

}
