package com.nullPointer.Domain.Controller;

import java.io.Serializable;

import com.nullPointer.Domain.Model.Player;

/**
 * @overview This class performs the operations that are needed to be done in order to complete money
 * transfers between the players and the game itself and/or other players.
 */
public class MoneyController implements Serializable {
    private static MoneyController _instance;
    private PlayerController playerController = PlayerController.getInstance();
    private int poolMoney = 0;

    private MoneyController() {

    }

    public static MoneyController getInstance() {
        if (_instance == null) {
            _instance = new MoneyController();
        }
        return _instance;
    }

    /**
     * @param player : current player
     * @param amount : how much will the player's money be increased
     * @requires player != null & amount >= 0
     * @modifies player's money
     * @effects increases the player's money by amount
     */
    public void increaseMoney(Player player, int amount) {
        player.setMoney(player.getMoney() + amount);
    }

    /**
     * @param player : current player
     * @param amount : how much will the player's money be decreased
     * @requires player != null & amount >= 0
     * @modifies player's money
     * @effects decreases the player's money by amount. If the player's money is less than 0,
     * the player is removed from the players list.
     */
    public void decreaseMoney(Player player, int amount) {
        player.setMoney(player.getMoney() - amount);
        
        if(player.getMoney()<0) {
        	System.out.println("[Money Controller]: Player is bankrupted");
        	(playerController).removePlayer(player);
        	//System.out.println(playerController.getPlayers().toString());
        }
    }

    /**
     * @param payer    : current player
     * @param receiver : Player who receives the money.
     * @param amount   : how much will the taken from payer and given to receiver
     * @requires player != null & amount >= 0
     * @modifies payer's and receiver's money
     * @effects transfers a certain amount of money ("amount") from the payer player to receiver player
     */
    public void transferMoney(Player payer, Player receiver, int amount) {
        decreaseMoney(payer, amount);
        increaseMoney(receiver, amount);
    }

    /**
     * @param player : current player
     * @param amount : how much will be collected from all players except "player"
     * @requires player != null & amount >= 0
     * @modifies all players' money
     * @effects collects money from all players (except "player") and the collected sum is given to "player"
     */
    public void getMoneyFromAllPlayers(Player player, int amount) {
        int gain = 0;
        for (Player p : playerController.getPlayers()) {
            if (!p.equals(player)) {
                decreaseMoney(p, amount);
                gain += amount;
            }
        }
        increaseMoney(player, gain);
    }

    public int getPoolMoney() {
        return poolMoney;
    }

    public void setPoolMoney(int poolMoney) {
        this.poolMoney = poolMoney;
    }

    public void increasePoolMoney(int poolMoney) {
        this.poolMoney += poolMoney;
    }

    public void decreasePoolMoney(int poolMoney) {
        this.poolMoney -= poolMoney;
    }

    /**
     * @param player : current player
     * @param amount : whether the player has more money than this value.
     * @requires player != null & amount >= 0
     * @effects checks whether the player has more money than "amount"
     */
    public boolean hasEnoughMoney(Player player, int amount) {
        if (player.getMoney() >= amount) {
            return true;
        } else {
            return false;
        }
    }

    public boolean repOk() {
        if (poolMoney >= 0)
            return true;
        return false;

    }

    public void exchangeMoneyControllerData(MoneyController moneyController) {
        this.poolMoney = moneyController.getPoolMoney();
    }

}