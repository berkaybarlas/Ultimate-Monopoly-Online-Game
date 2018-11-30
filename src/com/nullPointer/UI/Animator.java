package com.nullPointer.UI;
import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.JPanel;

import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;

public class Animator extends JPanel implements Runnable{

	Vector elementsToDraw = new Vector();
	private PlayerController playerController = PlayerController.getInstance();
	private GameEngine gameEngine = GameEngine.getInstance();
	private List<Player> playerList = new ArrayList<>();
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
				/*for(int i = 0; i<playerList.size(); i++){
				Player currentPlayer = playerList.get(i);
				if(currentPlayer.getPosition() != currentPlayer.getTargetPosition()) {
					try {
						//movePlayer(i ,1 );
						playerController.increaseCurrentPosition(currentPlayer);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}*/
			} catch (InterruptedException e) {
				System.out.println("Program Interrupted");
				System.exit(0);
			}
			repaint();
		}
	}
	public void paint(Graphics g) {
		super.paint(g);
		Enumeration e = elementsToDraw.elements();
		while (e.hasMoreElements())
			((Drawable) e.nextElement()).draw(g);
	}
	public void addDrawable(Drawable d) {
		elementsToDraw.addElement(d);
	}
	public void removeDrawable(Drawable d) {
		elementsToDraw.removeElement(d);
	}

}
