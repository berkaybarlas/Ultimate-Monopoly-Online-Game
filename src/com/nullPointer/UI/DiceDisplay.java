package com.nullPointer.UI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class DiceDisplay extends JPanel{
    private JButton rollDiceButton;
    private Random diceValue;
    
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
        		diceValue = new Random();
        		System.out.println(1+diceValue.nextInt(6));
            }
        });
        
        this.setVisible(true);
	}
}
