package com.nullPointer.UI;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import com.nullPointer.Model.GameEngine;

public class DiceDisplay extends JPanel{
    private JButton rollDiceButton;
    private JLabel diceValues;
    
    public DiceDisplay() {
		// TODO Auto-generated constructor stub
    	JPanel panel = new JPanel();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(Box.createVerticalGlue());
		
	
    	//JPanel contentPane = new JPanel(null); 
    	//contentPane.setPreferredSize(new Dimension(200, 200));
    	//this.add(contentPane, BorderLayout.NORTH);
    	rollDiceButton = new JButton("Roll Dice");
    	//rollDiceButton.setBounds(150,15,100,30);
    	this.add(rollDiceButton);
    	diceValues=new JLabel("Dice Values");
    	this.add(diceValues);
    	this.setVisible(true);
    	
        rollDiceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        		ArrayList<Integer> dice=GameEngine.getInstance().rollDice();
        		diceValues.setText("Total of dice: "+dice.get(0));
        		
        		repaint();
            }
        });
        
	}
}
