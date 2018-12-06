package com.nullPointer.Domain.Model;

import com.nullPointer.Domain.Model.Cards.Card;
import com.nullPointer.Domain.Model.Square.Square;

import java.util.*;

public class DomainBoard {

    private int numSquares = 120;
    private HashMap<Integer, Square> domainSquareMap;
    private HashMap<Integer, ArrayList<Integer>> connectionsMap;
    private Queue<Card> CCCards, ChanceCards;
    private SquareFactory squareFactory = SquareFactory.getInstance();
    private CardFactory cardFactory = CardFactory.getInstance();

    private int layer1begin = 0;
    private int layer1end = 55;
    private int layer2begin = 56;
    private int layer2end = 95;
    private int layer3begin = 96;
    private int layer3end = 119;


    // List that holds card generation orders for CC & chance cards. I appended the CCIndexList & ChanceIndexList, you can make changes as you see fit.
    public ArrayList<Integer> meta_card_gen_info = new ArrayList<Integer>();

    public DomainBoard() {
        domainSquareMap = new HashMap<Integer, Square>(numSquares);
        connectionsMap = new HashMap<Integer, ArrayList<Integer>>(numSquares);
        CCCards = new LinkedList<Card>();
        ChanceCards = new LinkedList<Card>();
        createConnectionsMap();
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

        for (int i = 0; i < squareFactory.squareNames_outer.length; i++) {
            domainSquareMap.put(i + layer1begin, squareFactory.createOuterSquares(i));
        }

        for (int i = 0; i < squareFactory.squareNames_middle.length; i++) {
            domainSquareMap.put(i + layer2begin, squareFactory.createMiddleSquares(i));
        }

        for (int i = 0; i < squareFactory.squareNames_inner.length; i++) {
            domainSquareMap.put(i + layer3begin, squareFactory.createInnerSquares(i));
        }

    }

    // Creates map of connections. This feature is fundamental to the current board, and therefore is hard-coded.
    public void createConnectionsMap() {
        for (int i = 0; i < 55; i++) {
            connectionsMap.put(i, new ArrayList<Integer>(Arrays.asList(i + 1, -1, i - 1)));
        }
        connectionsMap.get(0).set(2, 55);
        connectionsMap.put(55, new ArrayList<Integer>(Arrays.asList(0, -1, 54)));


        for (int i = 56; i < 95; i++) {
            connectionsMap.put(i, new ArrayList<Integer>(Arrays.asList(i + 1, -1, i - 1)));
        }
        connectionsMap.get(56).set(2, 95);
        connectionsMap.put(95, new ArrayList<Integer>(Arrays.asList(56, -1, 94)));

        for (int i = 96; i < 119; i++) {
            connectionsMap.put(i, new ArrayList<Integer>(Arrays.asList(i + 1, -1, i - 1)));
        }
        connectionsMap.get(96).set(2, 119);
        connectionsMap.put(119, new ArrayList<Integer>(Arrays.asList(96, -1, 118)));

        connectionsMap.get(7).set(1, 61);         //Reading Railroad Transit Station
        connectionsMap.get(61).set(1, 7);

        connectionsMap.get(71).set(1, 105);      //Pennsylvania Railroad Transit Station
        connectionsMap.get(105).set(1, 61);

        connectionsMap.get(35).set(1, 81);      //B&O Railroad Transit Station
        connectionsMap.get(81).set(1, 35);

        connectionsMap.get(91).set(1, 117);    //Short Line Railroad Transit Station
        connectionsMap.get(117).set(1, 91);


    }

    public HashMap<Integer, Square> getSquareMap() {
        return domainSquareMap;
    }

    public HashMap<Integer, ArrayList<Integer>> getConnectionsMap() {
        return connectionsMap;
    }

    public Square getSquareAt(int pos) {
        return domainSquareMap.get(pos);
    }

    public Queue<Card> getCCCards() {
        return CCCards;
    }

    public Queue<Card> getChanceCards() {
        return ChanceCards;
    }

}