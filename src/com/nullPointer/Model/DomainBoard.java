package com.nullPointer.Model;
import java.util.*;

import com.nullPointer.Model.Cards.Card;
import com.nullPointer.Model.Square.Square;
public class DomainBoard {

	private ArrayList<Square> squares;
	private ArrayList<Card> cards;

	public DomainBoard(){
		squares=new ArrayList<Square>();
		cards=new ArrayList<Card>();
	}


	public ArrayList<Square> getSquares() {
		return squares;
	}
	public ArrayList<Card> getCards() {
		return cards;
	}
}