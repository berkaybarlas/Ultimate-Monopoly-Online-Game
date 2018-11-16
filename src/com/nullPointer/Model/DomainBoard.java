package com.nullPointer.Model;

import java.util.*;

import com.nullPointer.Model.Cards.Card;
import com.nullPointer.Model.Square.ChanceCardSquare;
import com.nullPointer.Model.Square.CommunityChestCardSquare;
import com.nullPointer.Model.Square.GoSquare;
import com.nullPointer.Model.Square.GoToJailSquare;
import com.nullPointer.Model.Square.HollandTunnelSquare;
import com.nullPointer.Model.Square.PropertySquare;
import com.nullPointer.Model.Square.Square;

import sun.security.util.Length;

public class DomainBoard {

    private ArrayList<Square> squares;
    private Queue<Card> cards;
    private SquareFactory squareFactory = SquareFactory.getInstance();

    public DomainBoard() {
        squares = new ArrayList<Square>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        createSquares();
        cards = new LinkedList<Card>();
    }

    public void addSquare(Square sq) {
        squares.add(sq);
    }

    public void createSquares() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);

        for (int i = 0; i < squareFactory.squareNames_inner.length; i++) {

        }

        for (int i = 0; i < squareFactory.squareNames_middle.length; i++) {
            squares.add(squareFactory.createMiddleSquare(i));
        }

        for (int i = 0; i < squareFactory.squareNames_outer.length; i++) {
        }

    }


    public ArrayList<Square> getSquares() {
        return squares;
    }

    public Queue<Card> getCards() {
        return cards;
    }
}