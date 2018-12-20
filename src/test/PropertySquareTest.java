package test;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;
import com.nullPointer.Domain.Model.Square.PropertySquare;
import java.util.*;
import static org.junit.Assert.*;

public class PropertySquareTest {

    Player p1, p2;
    PropertySquare pSq;
    GameEngine gameEngine;
    int[] imaginaryList = {100, 200, 300, 400, 500, 600, 700, 800};

    @org.junit.Before
    public void setUp() throws Exception {
        gameEngine = GameEngine.getInstance();
        p1 = new Player("Player 1");
        p2 = new Player("Player 2");
        p1.setMoney(3200);
        p2.setMoney(3200);
        gameEngine.addPlayer(p1);
        gameEngine.addPlayer(p2);
        pSq = new PropertySquare("JUnit Avenue", "PropertySquare",150, "Blue", imaginaryList);
    }

    @org.junit.Test
    public void isOwnedForUnowned() {
        assertFalse(pSq.isOwned());
        assertNull(pSq.getOwner());
    }

    @org.junit.Test
    public void isOwnedForOwned() {
        p1.setMoney(p1.getMoney() - pSq.getPrice());
        pSq.setOwner(p1);
        assertTrue(pSq.isOwned());
    }

    @org.junit.Test
    public void landingOnUnowned() {
        p1.setMoney(p1.getMoney() + pSq.getPrice());
        pSq.setOwner(null);
        int initMoney = p1.getMoney();
        gameEngine.addPlayer(p1);
        pSq.evaluateSquare(gameEngine);
        assertEquals(p1.getMoney(), initMoney);
    }

    @org.junit.Test
    public void landingOnOwnedWithEnoughMoney() {
        int initMoney1 = p1.getMoney();
        int initMoney2 = p2.getMoney();
        p1.setMoney(p1.getMoney() - pSq.getPrice());
        pSq.setOwner(p1);
        gameEngine.setCurrentPlayer(p2);
        pSq.evaluateSquare(gameEngine);
        assertEquals(p2.getMoney(), initMoney2 - pSq.getRent());
        assertEquals(p1.getMoney(), initMoney1 - pSq.getPrice() + pSq.getRent());
    }

    @org.junit.Test
    public void greaterRentPaymentForImproved() {
        int initRent = pSq.getRent();
        pSq.improve();
        int initMoney1 = p1.getMoney();
        int initMoney2 = p2.getMoney();
        pSq.setOwner(p1);
        gameEngine.setCurrentPlayer(p2);
        pSq.evaluateSquare(gameEngine);
        assertTrue(initRent < pSq.getRent());
        assertTrue(p2.getMoney() < initMoney2 - initRent);
        assertFalse(p1.getMoney() == initMoney1 + initRent);
    }

    @org.junit.Test
    public void lessRentPaymentForDowngraded() {
        pSq.improve();
        int initRent = pSq.getRent();
        int initMoney1 = p1.getMoney();
        int initMoney2 = p2.getMoney();
        pSq.downgrade();
        pSq.setOwner(p1);
        gameEngine.setCurrentPlayer(p2);
        pSq.evaluateSquare(gameEngine);
        assertTrue(p2.getMoney() > initMoney2 - initRent);
        assertTrue(p1.getMoney() < initMoney1 + initRent);
    }

    @org.junit.Test
    public void smallerRentForImproved() {
        pSq.improve();
        int upRent = pSq.getRent();
        pSq.downgrade();
        pSq.evaluateSquare(gameEngine);
        assertTrue(upRent > pSq.getRent());
    }

    @org.junit.Test
    public void landingOnOwnedWithNotEnoughMoney() {
        int initMoney1 = p1.getMoney();
        pSq.setOwner(p1);
        p2.setMoney(10);
        gameEngine.setCurrentPlayer(p2);
        pSq.evaluateSquare(gameEngine);
        assertEquals(0, p2.getMoney());
        assertTrue(p1.getMoney() != initMoney1 + pSq.getRent());
    }

    @org.junit.Test
    public void landingOnYourOwnProperty() {
        gameEngine.addPlayer(p1);
        p1.setMoney(p1.getMoney() - pSq.getPrice());
        int initMoney1 = p1.getMoney();
        pSq.evaluateSquare(gameEngine);
        assertEquals(initMoney1, p1.getMoney());
    }


    @org.junit.Test
    public void repOkCorrect() {
        assertTrue(pSq.repOk());
    }

    @org.junit.Test
    public void repOkIncorrect() {
        ArrayList<PropertySquare> incorrectSquares = new ArrayList<PropertySquare>();
        PropertySquare incSq1 = new PropertySquare(null,"PropertySquare",150,"Green", imaginaryList);
        incorrectSquares.add(incSq1);
        PropertySquare incSq2 = new PropertySquare("Incorrect Ave 2","PropertySquare",-90,"Green", imaginaryList);
        incorrectSquares.add(incSq2);
        PropertySquare incSq3 = new PropertySquare("Incorrect Ave 3","PropertySquare",150,"Green", null);
        incorrectSquares.add(incSq3);
        PropertySquare incSq4 = new PropertySquare("Incorrect Ave 4","PropertySquare",150,null, imaginaryList);
        incorrectSquares.add(incSq4);
        int[] incorrectList1 = {90, 7, 78};
        PropertySquare incSq5 = new PropertySquare("Incorrect Ave 5","PropertySquare",150,"Red", incorrectList1);
        incorrectSquares.add(incSq5);
        int[] incorrectList2 = {80, 90, -70, 120, 130, 140, 150, 160};
        PropertySquare incSq6 = new PropertySquare("Incorrect Ave 6","PropertySquare",150,"Red", incorrectList2);
        incorrectSquares.add(incSq6);
        for(PropertySquare propertySquare : incorrectSquares) {
            assertFalse(propertySquare.repOk());
        }
    }

}