import static org.junit.Assert.*;

import java.util.*;
import org.junit.Test;
import com.nullPointer.Domain.Model.*;
import com.nullPointer.Domain.Model.Cards.*;
import com.nullPointer.Domain.Model.Square.CommunityChestCardSquare;
import com.nullPointer.Domain.Model.Square.PropertySquare;

public class GameEngineTest {
	Player p1, p2;
	PropertySquare pSq;
	GameEngine gameEngine;
	int[] rentList = {100, 200, 300, 400, 500, 600, 700, 800};

	@org.junit.Before
	public void setUp() throws Exception {
		gameEngine = GameEngine.getInstance();
		p1 = new Player("Player 1");
		p2 = new Player("Player 2");
		p1.setMoney(3200);
		p2.setMoney(3200);
		gameEngine.addPlayer(p1);
		gameEngine.addPlayer(p2);
		pSq = new PropertySquare("Test Avenue", "PropertySquare",150, "Blue", rentList);	
		gameEngine.getDomainBoard().getSquareMap().clear();
		gameEngine.getDomainBoard().getSquareMap().put(0, pSq);
		//System.out.println("Test Board 0 indexed Sqaure "+gameEngine.getDomainBoard().getSquareAt(0).getName());
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

		if (type.equals("CommunityChestCardSquare")) {
			if (type.equals("CommunityChestCardSquare")) {
				card2 = b.getCCCards().element();
				assertTrue(card2.getClass().getName().contains("com.nullPointer.Domain.Model.Cards.CC"));
			} 
		}
	}

	@Test
	public void testBuyAnUnownedProperty() {
		//setting p1 as the current player
		gameEngine.getPlayerController().setCurrentPlayerIndex(0);
		p1.setTargetPosition(0);
		gameEngine.buy();
		assertEquals(pSq, p1.getPropertySquares().get(0));
		assertEquals(3050,p1.getMoney());
	}
	@Test
	public void testPayRent() {
		p1.addSquare(pSq);
		p1.setMoney(3050);
		pSq.setOwner(p1);
		
		gameEngine.getPlayerController().setCurrentPlayerIndex(1);
		p2.setTargetPosition(0);
		gameEngine.payRent(p2, p1, 100);
		assertEquals(3100,p2.getMoney());
		assertEquals(3150, p1.getMoney());
	}

}