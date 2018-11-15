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

public class Board extends JPanel implements Runnable {
	private Image image; 
	private File imageSrc = new File("./assets/ultimate_monopoly.png");
	
    private Point position = new Point(10,10);
    private int length;
    
    private int width, height;

	private int[] lastXPositions=new int[12];
	private int[] lastYPositions=new int[12];
	private int eachmove;

	public Board(Point position, int length) {
		try {
			image = ImageIO.read(imageSrc);
            image = image.getScaledInstance(length, length, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(image.getHeight(null) + " | " + image.getWidth(null) );
    	this.position = position;
        this.length = length;
        width = image.getWidth(null);
        height = image.getHeight(null);

		setPreferredSize(new Dimension(length,length));
		eachmove=length/17;
		lastXPositions[0]=14*eachmove-20;
		lastYPositions[0]=14*eachmove-20;
		
	}

	public void paint(Graphics g) {
		//g.setColor(color);
		//g.fillRect(position.x, position.y, length, length);
		g.drawImage(image, position.x, position.y, length, length, null);
		g.setColor(Color.RED);
		g.fillOval(lastXPositions[0], lastYPositions[0], 20, 20);

	}
	public void move(int amount) throws InterruptedException{
		while(amount>0){	
			if(lastXPositions[0]>4*eachmove-20 && lastYPositions[0]==14*eachmove-20){
				for(int j=0;j<eachmove;j++){
					lastXPositions[0]--;
					this.repaint();
					Thread.sleep(10);
				}
				amount--;
				Thread.sleep(100);
			}
			//breaking point
			else if(lastXPositions[0]==4*eachmove-20 && lastYPositions[0]==14*eachmove-20){
				lastYPositions[0]--;
				for(int j=0;j<eachmove-1;j++){
					lastYPositions[0]--;
					this.repaint();
					Thread.sleep(10);
				}
				amount--;
				Thread.sleep(100);
			}
			else if(lastXPositions[0]==4*eachmove-20 && lastYPositions[0]<14*eachmove-20 && lastYPositions[0]>4*eachmove-20){
				for(int j=0;j<eachmove;j++){
					lastYPositions[0]--;
					this.repaint();
					Thread.sleep(10);
				}
				amount--;
				Thread.sleep(100);
			}
			//breaking point
			else if(lastXPositions[0]==4*eachmove-20 && lastYPositions[0]==4*eachmove-20){
				lastXPositions[0]++;
				for(int j=0;j<eachmove-1;j++){
					lastXPositions[0]++;
					this.repaint();
					Thread.sleep(10);
				}
				amount--;
				Thread.sleep(100);
			}
			else if(lastXPositions[0]>4*eachmove-20 && lastXPositions[0]<14*eachmove-20 && lastYPositions[0]==4*eachmove-20){
				for(int j=0;j<eachmove;j++){
					lastXPositions[0]++;
					this.repaint();
					Thread.sleep(10);
				}
				amount--;
				Thread.sleep(100);
			}
			//breaking point
			else if(lastXPositions[0]==14*eachmove-20 && lastYPositions[0]==4*eachmove-20){
				lastYPositions[0]++;
				for(int j=0;j<eachmove-1;j++){
					lastYPositions[0]++;
					this.repaint();
					Thread.sleep(10);
				}
				amount--;
				Thread.sleep(100);
			}
			else if(lastXPositions[0]==14*eachmove-20 && lastYPositions[0]>4*eachmove-20){
				for(int j=0;j<eachmove;j++){
					lastYPositions[0]++;
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
				move(3);
			} catch (InterruptedException e) {
				System.out.println("Program Interrupted");
				System.exit(0);
			}
			repaint();
		}

	}

}