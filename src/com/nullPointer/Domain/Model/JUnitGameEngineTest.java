package com.nullPointer.Domain.Model;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.nullPointer.Domain.Model.Cards.CCBeKindRewind;
import com.nullPointer.Domain.Model.Cards.Card;
import com.nullPointer.Domain.Model.Square.CommunityChestCardSquare;
import com.nullPointer.Domain.Model.Square.Square;

public class JUnitGameEngineTest {

	@Test
	public void testCalculatePath() {
		//fail("Not yet implemented");
	}

	@Test
	public void testRollDice() {
		RegularDie die = GameEngine.getInstance().getRegularDie();
		SpeedDie speeddie = GameEngine.getInstance().getSpeedDie();
		for(int i=0;i<100;i++){
			die.roll(2);
			speeddie.roll(1);
			ArrayList<Integer> list = new ArrayList<Integer>(3);
			list.add(die.getLastValues().get(0));
			list.add(die.getLastValues().get(1));
			list.add(speeddie.getLastValues().get(0));
			assertTrue(list.get(0)<=6 && list.get(0)>=1);
			assertTrue(list.get(1)<=6 && list.get(1)>=1);
			assertTrue(list.get(2)<=6 && list.get(2)>=1);
		}
	}

	@Test
	public void testCalculateMoveAmount() {
		RegularDie die = GameEngine.getInstance().getRegularDie();
		die.getLastValues().add(3);
		die.getLastValues().add(2);
		assertEquals(5, GameEngine.getInstance().calculateMoveAmount());

	}

	@Test
	public void testDrawCommunityChestCard() {

		Player currentPlayer = new Player("Tumay");
		DomainBoard b = new DomainBoard();
		Queue<Card> queue = new LinkedList<Card>();
		b.setCCCards(queue);
		CommunityChestCardSquare square = new CommunityChestCardSquare("CommunityChestCardSquare","CommunityChestCardSquare");
		Card card = new CCBeKindRewind("Be Kind, Rewind", true);
		b.getCCCards().add(card);
		String type = square.getType();
		Card card2;

		if (type.equals("CommunityChestCardSquare") || type.equals("ChanceCardSquare")) {
			if (type.equals("CommunityChestCardSquare")) {
				card2 = b.getCCCards().element();
				assertTrue(card2.getClass().getName().contains("com.nullPointer.Domain.Model.Cards.CC"));
			} 
		}
		/*else if(type.equals("ChanceCardSquare")) {
                card = domainBoard.getChanceCards().element();
            }
            else{
            	card = domainBoard.getRoll3Cards().element();
            }
            publishEvent("message/" + "[System]: " + currentPlayer.getName() + " drew " + card.getTitle());
            if (card.getImmediate()) {
                card.playCard(this);
                System.out.println();
            } else {
                playerController.addCardToCurrentPlayer(card);
            }
            nextTurn();
        } else {
            System.out.println("Error: drawCard has been called while player is outside Community Chest or Chance squares.");
        }*/


	}

	@Test
	public void testBuy() {
		//fail("Not yet implemented");
	}

	@Test
	public void testPayRent() {
		//fail("Not yet implemented");
	}

}
