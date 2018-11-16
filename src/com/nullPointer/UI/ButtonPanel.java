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
    protected JButton upgradeButton;
    protected JButton rollDice;
    
    private CommunicationController communicationController = CommunicationController.getInstance();
    private GameEngine gameEngine = GameEngine.getInstance();
	public ButtonPanel(){

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		purchaseButton = new JButton("Buy Property");
		drawButton = new JButton("Draw card");
		playCardButton = new JButton("Play card");
		upgradeButton = new JButton("Upgrade Property");
		rollDice = new JButton("Roll Dice");
		
		purchaseButton.setBounds(150,0,100,30);
		drawButton.setBounds(150,35,100,30);
		playCardButton.setBounds(150,70,100,30);
		upgradeButton.setBounds(150,105,100,30);
		rollDice.setBounds(150,140,100,30);

		panel.add(rollDice);
		panel.add(purchaseButton, BorderLayout.CENTER);
		panel.add(drawButton);
		panel.add(playCardButton);
		panel.add(upgradeButton);

        this.add(panel);
        
		purchaseButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				System.out.println("purchase");
				communicationController.sendClientMessage("purchase");
				purchaseButton.setEnabled(false);
				 //GameEngine.getInstance().buyProperty(pSquare, GameEngine.getInstance().getPlayerController().getCurrentPlayer());
				// also need to make a distinction between buying a utility and buying a property
			//gameEngine.getInstance().buy();
			//communicationController.sendClientMessage("purchase/");
			} 
		} );

		drawButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				System.out.println("drawCard");
				communicationController.sendClientMessage("drawCard");
				purchaseButton.setEnabled(false);
			} 
		} );
		
		playCardButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				System.out.println("playCard");
				communicationController.sendClientMessage("playCard");
				purchaseButton.setEnabled(false);
			} 
		} );
		
		upgradeButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				System.out.println("upgradeProperty");
				communicationController.sendClientMessage("upgradeProperty");
				purchaseButton.setEnabled(false);
			} 
		} );
		
		rollDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("roll dice");
                gameEngine.rollDice();
                communicationController.sendClientMessage("dice/" + gameEngine.getLastDiceValues());
			} 
		} );
		purchaseButton.setEnabled(false);
		this.setVisible(true);
		
	}
}
