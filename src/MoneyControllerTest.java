import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.nullPointer.Domain.Controller.MoneyController;
import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.Player;

public class MoneyControllerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testIncreaseMoney() {
		MoneyController playerController = MoneyController.getInstance();
		Player ply = new Player("test player");
		ply.setMoney(100);
		int amount = 100;
		playerController.increaseMoney(ply, amount);
		assertEquals(200, ply.getMoney());
	}

	@Test
	public void testDecreaseMoney() {
		MoneyController playerController = MoneyController.getInstance();
		Player ply = new Player("test player");
		ply.setMoney(100);
		int amount = 100;
		playerController.decreaseMoney(ply, amount);
		assertEquals(0, ply.getMoney());
	}

	@Test
	public void testTransferMoney() {
		MoneyController playerController = MoneyController.getInstance();
		Player payer = new Player("payer player");
		Player receiver = new Player("receiver player");
		payer.setMoney(300);
		receiver.setMoney(200);
		int amount = 100;
		playerController.transferMoney(payer, receiver, amount);
		assertEquals(200, payer.getMoney());
		assertEquals(300, receiver.getMoney());	
		}

	@Test
	public void testGetMoneyFromAllPlayers() {
		MoneyController moneyController = MoneyController.getInstance();
		PlayerController playerController = PlayerController.getInstance();
		
		Player player1 = new Player("player1");
		playerController.addPlayer(player1);
		player1.setMoney(200);
		Player player2 = new Player("player2");
		playerController.addPlayer(player2);
		player2.setMoney(200);
		Player player3 = new Player("player3");
		playerController.addPlayer(player3);
		player3.setMoney(200);
		Player player4 = new Player("player4");
		playerController.addPlayer(player4);
		player4.setMoney(200);
		Player player5 = new Player("player5");
		playerController.addPlayer(player5);
		player5.setMoney(200);
		Player player6 = new Player("player6");
		playerController.addPlayer(player6);
		player6.setMoney(200);
		
		moneyController.getMoneyFromAllPlayers(player1, 100);
		assertEquals(700, player1.getMoney());
		assertEquals(100, player2.getMoney());
		assertEquals(100, player3.getMoney());
		assertEquals(100, player4.getMoney());
		assertEquals(100, player5.getMoney());
	}
	@Test
	public void hasEnoughMoney() {
		MoneyController moneyController = MoneyController.getInstance();
		PlayerController playerController = PlayerController.getInstance();

		Player ply = new Player("test player");
		playerController.addPlayer(ply);
		ply.setMoney(500);
		moneyController.decreaseMoney(ply, 200);
		
		assertEquals(true, moneyController.hasEnoughMoney(ply, 200));
		assertEquals(false, moneyController.hasEnoughMoney(ply, 400));
		
	}
}
