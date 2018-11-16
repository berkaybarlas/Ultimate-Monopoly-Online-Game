package com.nullPointer.Model;
import java.util.*;

import com.nullPointer.Model.Cards.Card;
import com.nullPointer.Model.Square.PropertySquare;
import com.nullPointer.Model.Square.Square;
public class DomainBoard {

	private ArrayList<Square> squares;
	private Queue<Card> cards;

	public DomainBoard(){
		squares=new ArrayList<Square>();
		for(int i=0;i<15;i++)
		squares.add(new PropertySquare("First Property","PropertySquare",100,40));
		cards=new LinkedList<Card>();
	}


	public ArrayList<Square> getSquares() {
		return squares;
	}
	public Queue<Card> getCards() {
		return cards;
	}
}