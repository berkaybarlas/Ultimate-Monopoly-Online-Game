package com.nullPointer.UI;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import com.nullPointer.Model.GameEngine;

public class DiceDisplay extends JPanel{

    private JLabel diceValues;
    
    public DiceDisplay() {

		//this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //this.setPreferredSize(new Dimension(50 ,50 ));
        //JPanel contentPane = new JPanel(null);
    	//contentPane.setPreferredSize(new Dimension(200, 200));
    	//this.add(contentPane, BorderLayout.NORTH);

    	this.setBounds(0,0,100,300);
        this.setBackground(Color.WHITE);

    	diceValues=new JLabel("Dice Values");
    	diceValues.setLocation(0,0);
    	this.add(diceValues);
    	this.setVisible(true);

        
	}

    public void paint() {
        ArrayList<Integer> dice=GameEngine.getInstance().rollDice();
        diceValues.setText("Total of dice: "+dice.get(0));
        //repaint();
    }
}
