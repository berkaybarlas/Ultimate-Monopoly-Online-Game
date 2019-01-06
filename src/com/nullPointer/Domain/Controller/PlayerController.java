package com.nullPointer.Domain.Controller;

import com.nullPointer.Domain.Model.Cards.Card;
import com.nullPointer.Domain.Model.Player;
import com.nullPointer.Domain.Model.Square.PropertySquare;
import com.nullPointer.Domain.Model.Square.UtilitySquare;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;


public class PlayerController implements Serializable {

    private static PlayerController _instance;
    private ArrayList<Player> players = new ArrayList<Player>(12);
    private int currentPlayer = 0;
    private Player chosenPlayer = null;

    public Player getChosen() {
        return chosenPlayer;
    }

    public void setChosen(Player choose) {
        this.chosenPlayer = choose;
    }

    private PlayerController() {

    }

    public static synchronized PlayerController getInstance() {
        if (_instance == null) {
            _instance = new PlayerController();
        }
        return _instance;
    }

    public Player getCurrentPlayer() {
        if (players.size() > 0 && currentPlayer < players.size()) {
            return players.get(currentPlayer);
        }
        return null;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayer;
    }

    public void setCurrentPlayerIndex(int cP) {
        this.currentPlayer = cP;
    }

    ;

    /**
     * @return The next {@code Player} object in the list.
     * @requires players != null
     * @modifies currentPlayer
     * @effects Updates currentPlayer by setting it to currentPlayer+1 (mod size(players)) & returns the next {@code Player} object.
     */
    public Player nextPlayer() {
        if (players.size() > 0) {
            currentPlayer = (currentPlayer + 1) % players.size();
        }
        if (currentPlayer < players.size() && currentPlayer > -1) {
            return players.get(currentPlayer);
        }

        return null;

    }

    /**
     * @requires players != null & size(players) >= currentPlayer
     * @modifies players.get(currentPlayer)
     * @effects Alters {@code Player}'s inJail status by setting it <code>true</code>.
     */
    public void putInJail() {
        players.get(currentPlayer).setinJail(true);
    }

    /**
     * @requires players != null & size(players) >= currentPlayer
     * @modifies players.get(currentPlayer)
     * @effects Alters {@code Player}'s inJail status by setting it <code>true</code>.
     */
    public void getOutFromJail() {
        players.get(currentPlayer).setinJail(false);
    }

    /**
     * @param targetIndex new value of {@code TargetPosition}
     * @requires players != null & size(players) >= currentPlayer
     * @modifies players.get(currentPlayer)
     * @effects Alters {@code Player}'s {@code TargetPosition} by setting it to targetIndex.
     */
    public void movePlayer(int targetIndex) {
        getCurrentPlayer().setTargetPosition(targetIndex);
    }

    public void changeCurrentPosition(Player player, int newPos) {
        player.setPosition(newPos);
    }

    public void increaseCurrentPosition(Player player) {
        player.setPosition((player.getPosition() + 1) % 120);
    }

    public void removeProperty(PropertySquare propertySquare) {
        players.get(currentPlayer).getPropertyCardsMap().remove(propertySquare.getColor(), propertySquare);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void upgradePropertyList(PropertySquare property, Player player) {
        player.addSquare(property);
    }

    public void upgradeUtilityList(UtilitySquare utility, Player player) {
        player.getUtilityList().add(utility);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public void addCardToCurrentPlayer(Card card) {
        getCurrentPlayer().addCard(card);
    }

    public void exchangePlayerControllerData(PlayerController inputController) {
        players = inputController.getPlayers();
        currentPlayer = inputController.getCurrentPlayerIndex();
    }

    public void setPath(Player p, LinkedList<Integer> path) {
        p.setPath(path);
    }

    public LinkedList<Integer> getPath(Player p) {
        return p.getPath();
    }

    public boolean repOk() {
        if (players != null) {
            if (players.size() > 0) {
                for (Player p : players) {
                    if (!p.repOk()) return false;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "[PlayerController] " +
                "Players in the Controller: " + players;
    }
}
