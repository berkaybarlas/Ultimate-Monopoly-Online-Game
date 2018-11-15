package com.nullPointer.UI;
import javax.imageio.ImageIO;
import javax.swing.*;

import com.nullPointer.Model.GameEngine;
import com.nullPointer.Utils.ColorSet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Board extends JPanel implements Runnable {
	private Image image; 
	private File imageSrc = new File("./assets/ultimate_monopoly.png");
	
    private Point position;
    private int length;

    private List<Pawn> pawnList;

	private int smallSide;
	private Point initialPosition;

	public Board(Point position, int length) {
		try {
			image = ImageIO.read(imageSrc);
            image = image.getScaledInstance(length, length, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}

    	this.position = position;
        this.length = length;

		setPreferredSize(new Dimension(length,length));

		pawnList = new ArrayList<>();
		smallSide = length/17;

		initialPosition = new Point(14*smallSide - 20,14*smallSide - 20);
		
	}

	public void paint(Graphics g) {
		//g.setColor(color);
		//g.fillRect(position.x, position.y, length, length);
		g.drawImage(image, position.x, position.y, length, length, null);
		g.setColor(Color.RED);
		pawnList.forEach(pawn -> g.fillOval(pawn.getPosition().x, pawn.getPosition().y , 20, 20));
	}

	public void addPawn() {
		pawnList.add(new Pawn(initialPosition));
	}

	public void movePlayer(int playerIndex, int amount) throws InterruptedException {
		while(amount>0){	
			if(pawnList.get(playerIndex).getPosition().x > 4*smallSide -20 && pawnList.get(playerIndex).getPosition().y == 14*smallSide -20){
				for(int j=0;j<smallSide ;j++){
					pawnList.get(playerIndex).changeX(-1);
					this.repaint();
					Thread.sleep(10);
				}
				amount--;
				Thread.sleep(100);
			}
			//breaking point
			else if(pawnList.get(playerIndex).getPosition().x == 4*smallSide -20 && pawnList.get(playerIndex).getPosition().y == 14 * smallSide -20){
				pawnList.get(playerIndex).changeY(-1);
				for(int j=0; j<smallSide -1; j++){
					pawnList.get(playerIndex).changeY(-1);
					this.repaint();
					Thread.sleep(10);
				}
				amount--;
				Thread.sleep(100);
			}
			else if(pawnList.get(playerIndex).getPosition().x == 4*smallSide -20 && pawnList.get(playerIndex).getPosition().y < 14*smallSide -20 && pawnList.get(playerIndex).getPosition().y >4*smallSide -20){
				for(int j=0; j<smallSide ; j++){
					pawnList.get(playerIndex).changeY(-1);
					this.repaint();
					Thread.sleep(10);
				}
				amount--;
				Thread.sleep(100);
			}
			//breaking point
			else if(pawnList.get(playerIndex).getPosition().x == 4*smallSide -20 && pawnList.get(playerIndex).getPosition().y == 4*smallSide -20){
				pawnList.get(playerIndex).changeX(+1);
				for(int j=0;j<smallSide -1;j++){
					pawnList.get(playerIndex).changeX(+1);
					this.repaint();
					Thread.sleep(10);
				}
				amount--;
				Thread.sleep(100);
			}
			else if(pawnList.get(playerIndex).getPosition().x > 4*smallSide -20 && pawnList.get(playerIndex).getPosition().x < 14*smallSide -20 && pawnList.get(playerIndex).getPosition().y ==4*smallSide -20){
				for(int j=0;j<smallSide ;j++){
					pawnList.get(playerIndex).changeX(+1);
					this.repaint();
					Thread.sleep(10);
				}
				amount--;
				Thread.sleep(100);
			}
			//breaking point
			else if(pawnList.get(playerIndex).getPosition().x==14*smallSide -20 && pawnList.get(playerIndex).getPosition().y ==4*smallSide -20){
				pawnList.get(playerIndex).changeY(+1);
				for(int j=0;j<smallSide -1;j++){
					pawnList.get(playerIndex).changeY(+1);
					this.repaint();
					Thread.sleep(10);
				}
				amount--;
				Thread.sleep(100);
			}
			else if(pawnList.get(playerIndex).getPosition().x==14*smallSide -20 && pawnList.get(playerIndex).getPosition().y >4*smallSide -20){
				for(int j=0;j<smallSide ;j++){
					pawnList.get(playerIndex).changeY(+1);
					this.repaint();
					Thread.sleep(10);
				}
				amount--;
				Thread.sleep(100);
			}
		}
	}
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
				addPawn();
				movePlayer(0,3);
			} catch (InterruptedException e) {
				System.out.println("Program Interrupted");
				System.exit(0);
			}
			repaint();
		}

	}

}