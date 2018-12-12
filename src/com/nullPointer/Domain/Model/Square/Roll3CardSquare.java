package com.nullPointer.Domain.Model.Square;
import java.util.ArrayList;
import java.util.Collections;

import com.nullPointer.Domain.Model.GameEngine;

public class Roll3CardSquare extends Square {

	public Roll3CardSquare(String n, String t) {
		super(n, t);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		// maybe we can keep a roll 3 card deck here, and make the player draw a card and call its playCard()
		// open to suggestions
		gameEngine.publishEvent("roll3Dice");
		/*ArrayList<Integer> roll3 = gameEngine.getRoll3().getValues();
		while(roll3.size() != 3){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Collections.sort(roll3);*/
		
		
		
		gameEngine.nextTurn();
	}

}
