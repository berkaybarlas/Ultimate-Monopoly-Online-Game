package com.nullPointer.Domain.Model;

import java.util.*;

import com.nullPointer.Domain.Model.Cards.Card;
import com.nullPointer.Domain.Model.Cards.ChanceHolidayBonus;
import com.nullPointer.Domain.Model.Square.Square;

public class DomainBoard {

    private ArrayList<ArrayList<Square>> layers;
    private Queue<Card> CCCards, ChanceCards;
    private SquareFactory squareFactory = SquareFactory.getInstance();
    private CardFactory cardFactory = CardFactory.getInstance();


    // List that holds card generation orders for CC & chance cards. I appended the CCIndexList & ChanceIndexList, you can make changes as you see fit.
    public ArrayList<Integer> meta_card_gen_info = new ArrayList<Integer>();

    public DomainBoard() {
        layers = new ArrayList<>(3);
        layers.add(new ArrayList<Square>());
        layers.add(new ArrayList<Square>());
        layers.add(new ArrayList<Square>());
        CCCards = new LinkedList<Card>();
        ChanceCards = new LinkedList<Card>();
        createSquares();
        createCards();
    }

    /*
     * This is the real method for creating all cards, which will be activated
     * once all cards have been properly implemented.
     */
    private void createCardsReal() {

        // Creates an ArrayList of incrementing integers from 0 to CClength, then shuffles it to create the card list in random order.
        // indexList is to be pushed to servers for card generation info
        int CCLength = cardFactory.CCCard_names.length;
        ArrayList<Integer> CCindexList = new ArrayList<Integer>(CCLength);
        for (int index = 0; index < CCLength; index++) {
            CCindexList.add(index);
        }
        Collections.shuffle(CCindexList);
        for (int i = 0; i < CCLength; i++) {
            CCCards.add(cardFactory.createCCCard(CCindexList.get(i)));
        }


        // same goes for chance cards
        int ChanceLength = cardFactory.ChanceCard_names.length;
        ArrayList<Integer> ChanceIndexList = new ArrayList<Integer>(ChanceLength);
        for (int index = 0; index < ChanceLength; index++) {
            ChanceIndexList.add(index);
        }
        Collections.shuffle(ChanceIndexList);
        for (int j = 0; j < ChanceLength; j++) {
            ChanceCards.add(cardFactory.createChanceCard(ChanceIndexList.get(j)));
        }

        // set meta_card_gen_info
        meta_card_gen_info.addAll(CCindexList);
        meta_card_gen_info.addAll(ChanceIndexList);
    }

    public void createCards() {
        CCCards.add(cardFactory.createCCCard(8));
        ChanceCards.add(cardFactory.createChanceCard(12));
        meta_card_gen_info.add(8);
        meta_card_gen_info.add(12);
    }

    public void createSquares() {

        for (int i = 0; i < squareFactory.squareNames_inner.length; i++) {

        }

        for (int i = 0; i < squareFactory.squareNames_middle.length; i++) {
            layers.get(1).add(squareFactory.createMiddleSquare(i));
        }

        for (int i = 0; i < squareFactory.squareNames_outer.length; i++) {
        }

    }


    public ArrayList<Square> getSquaresInLayer(int layerIndex) {
        return layers.get(layerIndex);
    }

    public ArrayList<Square> getSquaresInLayer() {
        return layers.get(1);
    }

    public Square getSquareInLayerAtPosition(int layerIndex, int position) {
        return layers.get(layerIndex).get(position);
    }

    public Queue<Card> getCCCards() {
        return CCCards;
    }

    public Queue<Card> getChanceCards() {
        return ChanceCards;
    }

}