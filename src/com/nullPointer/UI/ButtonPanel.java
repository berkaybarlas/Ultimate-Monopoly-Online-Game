package com.nullPointer.UI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.nullPointer.Controller.CommunicationController;
import com.nullPointer.Model.GameEngine;

public class ButtonPanel extends JPanel{

    protected JButton purchaseButton;
    protected JButton drawButton;
    protected JButton playCardButton;
    protected JButton improveButton;
    protected JButton rollDice;
    
    private CommunicationController communicationController = CommunicationController.getInstance();
    private GameEngine gameEngine = GameEngine.getInstance();
	public ButtonPanel(){

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		purchaseButton = new JButton("Buy Property");
		drawButton = new JButton("Draw card");
		playCardButton = new JButton("Play card");
		improveButton = new JButton("improve Property");
		rollDice = new JButton("Roll Dice");
		
		purchaseButton.setBounds(150,0,100,30);
		drawButton.setBounds(150,35,100,30);
		playCardButton.setBounds(150,70,100,30);
		improveButton.setBounds(150,105,100,30);
		rollDice.setBounds(150,140,100,30);

		panel.add(rollDice);
		panel.add(purchaseButton, BorderLayout.CENTER);
		panel.add(drawButton);
		panel.add(playCardButton);
		panel.add(improveButton);

        this.add(panel);
        
		purchaseButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				System.out.println("purchase");
				communicationController.sendClientMessage("purchase");
				purchaseButton.setEnabled(false);;
			} 
		} );

		drawButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				System.out.println("drawCard");
				//communicationController.sendClientMessage("drawCard");
				purchaseButton.setEnabled(false);
			} 
		} );
		
		playCardButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				System.out.println("playCard");
				//communicationController.sendClientMessage("playCard");
				playCardButton.setEnabled(false);
			} 
		} );
		
		improveButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				System.out.println("improveProperty");
				//communicationController.sendClientMessage("improveProperty");
				improveButton.setEnabled(false);
			} 
		} );
		
		rollDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("roll dice");
                gameEngine.rollDice();
                communicationController.sendClientMessage("dice/" + gameEngine.getLastDiceValues());
				rollDice.setEnabled(false);
			} 
		} );
		purchaseButton.setEnabled(false);
		drawButton.setEnabled(false);
		playCardButton.setEnabled(false);
		improveButton.setEnabled(false);
		this.setVisible(true);
		
	}
}
