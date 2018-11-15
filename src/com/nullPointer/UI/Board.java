package com.nullPointer.UI;
import javax.imageio.ImageIO;
import javax.swing.*;
import com.nullPointer.Utils.ColorSet;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Board extends JPanel{
	private Image image;
	private File imageSrc = new File("./assets/ultimate_monopoly.png");
	
    private Color color = new Color(187, 229, 206);
    private Point position;
    private int length;
    
    private int width, height;


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

        //JLabel picLabel = new JLabel(new ImageIcon(image));
        //this.add(picLabel);
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(color);
        g.drawImage(image, position.x, position.y, length, length, null);
        g.fillRect(position.x, position.y, 300, 300);
    }
}

