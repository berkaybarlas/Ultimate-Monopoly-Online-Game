package com.nullPointer.Controller;
import java.util.*;
import com.nullPointer.Model.Player;
import com.nullPointer.Model.PropertySquare;
public class PlayerController {
    private static PlayerController _instance;
    private ArrayList<Player> players=new ArrayList<Player>(12);
	private int currentPlayer;

    private PlayerController(){

    }

    public static PlayerController getInstance() {
        if(_instance == null) {
            _instance = new PlayerController();
        }
        return _instance;
    }
    public Player getCurrentPlayer(){
    	return players.get(currentPlayer);
    }
    public Player nextPlayer(){
    	return players.get(currentPlayer+1);
    }
    public void putInJail(){
    	players.get(currentPlayer).setinJail(true);
    }
    public void getOutFromJail(){
    	players.get(currentPlayer).setinJail(false);
    }
    public void movePlayer(int newPosition){
    	players.get(currentPlayer).setPosition(newPosition);
    }
    public void removeProperty(PropertySquare propertySquare){
    	players.get(currentPlayer).getPropertyCardsMap().remove(propertySquare.getColor(),propertySquare);
    }
    public ArrayList<Player> getPlayers() {
		return players;
	}

}
