package com.nullPointer.UI;
import javax.imageio.ImageIO;
import javax.swing.*;
import com.nullPointer.Utils.ColorSet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Board {
	private BufferedImage image; 
	private File imageSrc = new File("./assets/ultimate_monopoly.png");
	
    private Color color = new Color(187, 229, 206);
    private Point position = new Point(550,50);
    private int length = 700;
    
    private int width, height;
    
    public Board(Point position, int length) {
    	try {
			image = ImageIO.read(imageSrc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.position = position;
        this.length = length;
        
        width = image.getWidth(null);
        height = image.getHeight(null);
        
    }

    public void paint(Graphics g) {
        //g.setColor(color);
        //g.fillRect(position.x, position.y, length, length);
        g.drawImage(image, 10, 10, 600, 700, null);
    }
}
