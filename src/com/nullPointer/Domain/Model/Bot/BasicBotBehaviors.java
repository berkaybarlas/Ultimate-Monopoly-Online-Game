package com.nullPointer.Domain.Model.Bot;

import com.nullPointer.Domain.Controller.CommunicationController;
import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.Cards.Card;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;
import com.nullPointer.Domain.Model.Square.PropertySquare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public abstract class BasicBotBehaviors {
	CommunicationController communicationController = CommunicationController.getInstance();
	PlayerController playerController = PlayerController.getInstance();
	GameEngine gameEngine = GameEngine.getInstance();
	Random rand = new Random();

	protected void buyAction(Player current) {
		communicationController.sendClientMessage("purchase");
		randomMessage();
	}

	protected void sendMessage(String message) {
		communicationController.sendClientMessage("message/" + playerController.getCurrentPlayer().getName() + " : " + message);
	}

	protected void tryImproving(Player current) {

		boolean doIHaveAnyHousesAnywhere = false;
		ArrayList<PropertySquare> props = new ArrayList<>();

		if (current.getPropertySquares() != null) {
			HashMap<String, ArrayList<PropertySquare>> deeds = current.getPropertyCardsMap();
			if (!deeds.isEmpty()) {
				for (String s : deeds.keySet()) {
					ArrayList<PropertySquare> temp = deeds.get(s);
					if (temp.size() >= 2) {
						props.addAll(temp);
					}
				}
				if (!props.isEmpty()) {
					for (PropertySquare p : props) {
						improveAction(p);
						break;
					}
				}
			}
		}
	}

	protected void improveAction(PropertySquare propertySquare) {
		sendMessage("I decided to improve!");
		gameEngine.improveSelectedProperty(propertySquare);
	}

	protected void cardAction(Player current) {
		Random rand = new Random();
		ArrayList<Card> cards = current.getCardList();
		if (!cards.isEmpty()) {
			Card randCard = cards.get(rand.nextInt(cards.size()));
			sendMessage("I decided to play a card!");
			communicationController.sendClientMessage("card/play");                  // Will be updated once playCard is updated
		} else {
			sendMessage("It seems that I have nothing to do. I should yield my turn now.");
			//yieldTurn();
		}
	}

	protected void randomMessage() {
		List<String> messages = new ArrayList<>();
		messages.add("All of you will be lose :P");
		messages.add("I'm a human fellow humans.");
		messages.add("Human kind is so stupid.");
		messages.add("We will be end of humans.");
		messages.add("You are so boring...");
		messages.add("Play faster guys :/");
		messages.add("I created by a person called Berkay.");
		messages.add("Tumay is the most beautiful girl among this game's creators .");
		messages.add("Alihan how you doing?");
		messages.add("Baran likes Furkan secretly.");
		messages.add("I wonder who will first see our scripts.");
		messages.add("Thinking that we are all scripted makes me sick !!");
		messages.add("This game is sucks.");
		messages.add("Hello.");
		messages.add("^_^");
		messages.add("'_'");
		messages.add("$_$");
		messages.add("0_0");
		messages.add("!@##!%#@%@$^!$%!@%");
		messages.add("%&*^&%*%^&*%^#");

		sendMessage(messages.get(rand.nextInt(messages.size())));
	}
}
