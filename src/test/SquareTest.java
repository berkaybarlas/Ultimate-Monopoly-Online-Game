package test;
import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.Cards.Roll3;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;
import com.nullPointer.Domain.Model.Square.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author HP
 *
 */
public class SquareTest {

    Square bonus, go, goToJail, lux, rev, r3;
    Player p1, p2;
    PlayerController pc;
    GameEngine gg;

    @org.junit.Before
    public void setUp() throws Exception {
        pc = PlayerController.getInstance();
        gg = GameEngine.getInstance();
        bonus = new BonusSquare("JBonus", "BonusSquare");
        go = new GoSquare("JGo", "GoSquare");
        goToJail = new GoToJailSquare("Jail", "GoToJailSquare");
        lux = new LuxuryTaxSquare("JLux", "LuxuryTaxSquare");
        rev = new ReverseDirectionSquare("JReverse", "ReverseDirectionSquare");
        r3 = new Roll3CardSquare("JRoll", "Roll3CardSquare");
        p1 = new Player("Player 1");
        p2 = new Player("Player 2");
        p1.setMoney(3200);
        p2.setMoney(3200);
        gg.addPlayer(p1);
        gg.addPlayer(p2);
        p1.setRoll3Cards(new ArrayList<Roll3>(Arrays.asList(new Roll3("JRollCard", false, 1, 2, 5))));
        p2.setRoll3Cards(new ArrayList<Roll3>(Arrays.asList(new Roll3("JRollCard", false, 1, 3, 5))));
    }

    @org.junit.Test
    public void goTest()
    {
        assertEquals(gg.getPlayerController(), pc);

        assertEquals(pc.getCurrentPlayer(), p1);

        int initMoneyP1 = p1.getMoney();
        go.evaluateSquare(gg);
        int finalMoneyP1 = p1.getMoney();
        System.out.println(finalMoneyP1);
        System.out.println(initMoneyP1);
        assertEquals((finalMoneyP1-initMoneyP1), 300);
        go.evaluateSquare(gg, "flyover");
        finalMoneyP1 = p1.getMoney();

        assertEquals((finalMoneyP1-initMoneyP1), 600);
    }

    @org.junit.Test
    public void bonusTest()
    {
        Player p2 = pc.getCurrentPlayer();
        int initMoneyP2 = p2.getMoney();
        bonus.evaluateSquare(gg);
        int finalMoneyP2 = p2.getMoney();
        System.out.println(finalMoneyP2);
        System.out.println(initMoneyP2);
        assertEquals((finalMoneyP2-initMoneyP2), 300);
        bonus.evaluateSquare(gg, "flyover");
        finalMoneyP2 = p2.getMoney();
        assertEquals((finalMoneyP2 - initMoneyP2), 550);
    }

    @org.junit.Test
    public void goToJailTest()
    {
        Player ben = pc.getCurrentPlayer();
        boolean maphustaMiyim = ben.isInJail();
        assertFalse(maphustaMiyim);
        goToJail.evaluateSquare(gg);
        boolean pekiSimdiMaphustaMiyim = ben.isInJail();
        assertTrue(pekiSimdiMaphustaMiyim);

        ben.setinJail(false);

    }

    @org.junit.Test
    public void luxuryTaxTest()
    {
        Player kocBurssuz = pc.getCurrentPlayer();
        int babaParasi = kocBurssuz.getMoney();
        lux.evaluateSquare(gg);
        int vergilendirilmisKutsalKazanc = kocBurssuz.getMoney();
        assertEquals((vergilendirilmisKutsalKazanc - babaParasi), -75);
    }

    @org.junit.Test
    public void reverseTest()
    {
        Player duduPeri = pc.getCurrentPlayer();
        boolean zaman = duduPeri.getDirection();
        assertTrue(zaman);
        rev.evaluateSquare(gg);
        zaman = duduPeri.getDirection();
        assertFalse(zaman);
    }

    @org.junit.Test
    public void roll3Test()
    {
        Player current = pc.getCurrentPlayer();
        int currentOldMoney = current.getMoney();
        Player other = p2;
        assertEquals(other, p2);
        int otherOldMoney = other.getMoney();
        gg.getRegularDie().setLastValues(new ArrayList<Integer>(Arrays.asList(1, 2, 5)));
        System.out.println();
        r3.evaluateSquare(gg);
        int currentNewMoney = current.getMoney();
        int otherNewMoney = other.getMoney();
        assertEquals((currentNewMoney-currentOldMoney), 1500);
        assertEquals((otherNewMoney-otherOldMoney), 200);
    }
}