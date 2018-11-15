package com.nullPointer.UI;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import com.nullPointer.Model.GameEngine;

public class DiceDisplay extends JPanel{
    private JButton rollDiceButton;
    
    public DiceDisplay() {
		// TODO Auto-generated constructor stub
    	JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
    	JPanel contentPane = new JPanel(null); 
    	contentPane.setPreferredSize(new Dimension(200, 200));
    	this.add(contentPane, BorderLayout.NORTH);
	
    	rollDiceButton = new JButton("Roll Dice");
    	rollDiceButton.setBounds(150,15,100,30);
    	
    	this.add(contentPane, BorderLayout.NORTH);
    	
        rollDiceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //rollDice();
        		ArrayList<Integer> dice=GameEngine.getInstance().rollDice();
        		//add(new JLabel(""+dice.get(0)));
            }
        });
        this.setVisible(true);
	}
}
