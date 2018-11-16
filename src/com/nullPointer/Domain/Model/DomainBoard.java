package com.nullPointer.Domain.Model;

import java.util.*;

import com.nullPointer.Domain.Model.Cards.Card;
import com.nullPointer.Domain.Model.Square.Square;

public class DomainBoard {

    private ArrayList<ArrayList<Square>> layers;
    private Queue<Card> cards;
    private SquareFactory squareFactory = SquareFactory.getInstance();

    public DomainBoard() {
        layers = new ArrayList<>(3);
        layers.add(new ArrayList<Square>());
        layers.add(new ArrayList<Square>());
        layers.add(new ArrayList<Square>());
        createSquares();
        cards = new LinkedList<Card>();
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

    public Queue<Card> getCards() {
        return cards;
    }
}