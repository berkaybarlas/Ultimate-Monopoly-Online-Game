package com.nullPointer.Domain.Controller;
import java.util.*;
import com.nullPointer.Domain.Model.Player;
import com.nullPointer.Domain.Model.Square.PropertySquare;
import com.nullPointer.Domain.Model.Square.UtilitySquare;
public class PlayerController {

	private static PlayerController _instance;
	private ArrayList<Player> players=new ArrayList<Player>(12);
	private int currentPlayer = 0;

	private PlayerController(){
		players.add(new Player("Furkan"));
		players.add(new Player("Tumay"));
		players.add(new Player("Berkay"));
		players.add(new Player("Baran"));
		players.add(new Player("Alihan"));
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
		currentPlayer = (currentPlayer + 1) % players.size();
		return players.get(currentPlayer);
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

	public void upgradePropertyList(PropertySquare property, Player player){
		player.addSquare(property);
	}

	public void upgradeUtilityList(UtilitySquare utility, Player player) {
		player.getUtilityList().add(utility);
	}

	public void addPlayer() {
	    //players.add(new Player());
    }

}
