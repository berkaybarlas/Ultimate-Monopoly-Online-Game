package com.nullPointer;

import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.Cards.Roll3;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;
import com.nullPointer.Domain.Model.Square.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class SquareTest {

    Square bonus, go, goToJail, lux, rev, r3;
    Player p1, p2;
    PlayerController pc;
    GameEngine gg;

    @Before
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

    @Test
    public void evaluateSquareTests()
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

        gg.nextTurn();
        assertEquals(pc.getCurrentPlayer(), p2);

        int initMoneyP2 = p2.getMoney();
        bonus.evaluateSquare(gg);
        int finalMoneyP2 = p2.getMoney();
        assertEquals((finalMoneyP2-initMoneyP2), 300);
        bonus.evaluateSquare(gg, "flyover");
        finalMoneyP2 = p2.getMoney();
        assertEquals((finalMoneyP2 - initMoneyP2), 550);

        gg.nextTurn();
        assertEquals(pc.getCurrentPlayer(), p1);

        // yazar burada sıkılmaya başlamıştır
        Player ben = pc.getCurrentPlayer();
        boolean maphustaMıyım = ben.isInJail();
        assertFalse(maphustaMıyım);
        goToJail.evaluateSquare(gg);
        boolean pekiŞimdiMaphustaMıyım = ben.isInJail();
        assertTrue(pekiŞimdiMaphustaMıyım);
        // zavallı ben... şimdi kim bilir kaç yıl, kader kurbanı olarak yatacağım... Durumu tam olarak içselleştirebilmeniz için arka fon müziği: https://m.youtube.com/watch?v=-1r3kL_4iPA

        // *hapisteyimdir*
        // *hükümette üst düzey tanıdıklarım vardır*
        ben.setinJail(false);
        // *hapiste değilimdir*

        gg.nextTurn();
        assertEquals(pc.getCurrentPlayer(), p2);

        // Go ve Bonus ile günlerine bereketle başlayan oyuncularımız, p1'in maphus damına düşmesiiyle zor günlerin başladığı bir döneme girmişlerdir.
        // Kim bilir, şimdi p2'yi ne maceralar beklemektedir?

        Player koçBurssuz = pc.getCurrentPlayer();
        int babaParası = koçBurssuz.getMoney();
        lux.evaluateSquare(gg);
        int vergilendirilmişKutsalKazanç = koçBurssuz.getMoney();
        assertEquals((vergilendirilmişKutsalKazanç - babaParası), -75); // pırlantadan, yattan %0 KDV alırsan olacağı bu işte, adamlar 75M vergiyle yırttılar...


        // *betüş sesiyle* ZAMAAAN, GERİYE AKSIIINNNN!!!

        gg.nextTurn();
        assertEquals(pc.getCurrentPlayer(), p1);

        //ilk başta Sihirli Annem konsepti yapmayı planlamamıştım, ana John Lennon'un da dediği gibi, "İnsanoğlu plan yapar ve Tanrı güler."...
        Player duduPeri = pc.getCurrentPlayer();
        boolean zaman = duduPeri.getDirection();
        assertTrue(zaman);
        rev.evaluateSquare(gg);  // söylerken kollarınızı birden havaya kaldırın, sihir efektini postta ekleyecekler
        zaman = duduPeri.getDirection();
        assertFalse(zaman);

        // yazarın sıkıntısı geçmiş, yaptığı şaklabanlığın ne kadar saçma olduğunun farkına varmıştır. Yazarı rahatsız etmeyin, o zaten
        // bir köşede hatalarını düşünüp, belki de ağlamaktadır...

        gg.nextTurn();
        assertEquals(pc.getCurrentPlayer(), p2);


        Player current = pc.getCurrentPlayer();
        int currentOldMoney = current.getMoney();
        Player other = p1;
        assertEquals(other, p1);
        int otherOldMoney = other.getMoney();
        gg.getRegularDie().setLastValues(new ArrayList<Integer>(Arrays.asList(1, 3, 5)));  // hilebaz current zarları manipüle ediyor
        System.out.println();
        r3.evaluateSquare(gg);
        int currentNewMoney = current.getMoney();
        int otherNewMoney = other.getMoney();
        assertEquals((currentNewMoney-currentOldMoney), 1500);
        assertEquals((otherNewMoney-otherOldMoney), 200);  // gördüğünüz gibi hilebaz current 1500'ü cebe indirirken, zavallı other 200'le yetinmek zorunda kaldı. Bu dünyanın düzeni de böyle işte...

    }



}