package com.nullPointer.Domain.Controller;

import java.util.ArrayList;

import com.nullPointer.Domain.Model.Player;

public class MoneyController {
    private static MoneyController _instance;
    private ArrayList<Player> players=new ArrayList<Player>(12);
    private PlayerController playerController = PlayerController.getInstance();
    private int poolMoney;
    
    private MoneyController(){

    }

    public static MoneyController getInstance() {
        if(_instance == null) {
            _instance = new MoneyController();
        }
        return _instance;
    }
    
    public void increaseMoney(Player player, int amount) {
    		player.setMoney(player.getMoney()+amount);
    }
    
    public void decreaseMoney(Player player, int amount) {
    		player.setMoney(player.getMoney()-amount);
    }
    
    public void transferMoney(Player payer, Player receiver, int amount) {
    		decreaseMoney(receiver, amount);
    		increaseMoney(receiver, amount);
    }
    
    public void getMoneyFromAllPlayers(Player player, int amount) {
    		int gain = 0;
    		for(Player p:playerController.getPlayers()) {
    			if(!p.equals(player)) {
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
    
    public boolean hasEnoughMoney(Player player, int amount) {
    		if(player.getMoney()>=amount) {
    			return true;
    		} else {
    			return false;
    		}
    }
    
}
