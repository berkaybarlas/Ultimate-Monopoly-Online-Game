package com.nullPointer.UI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.nullPointer.Controller.CommunicationController;

public class ButtonPanel extends JPanel{

    protected JButton purchaseButton;
    protected JButton actionButton;
    protected JButton rollDice;
    
    private CommunicationController communicationController = CommunicationController.getInstance();
    
	public ButtonPanel(){

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		purchaseButton = new JButton("Buy Property");
		actionButton = new JButton("Make action");
		rollDice = new JButton("Roll Dice");
		
		purchaseButton.setBounds(150,0,100,30);
		actionButton.setBounds(150,35,100,30);
		rollDice.setBounds(150,70,100,30);

		panel.add(rollDice);
		panel.add(purchaseButton, BorderLayout.CENTER);
		panel.add(actionButton);

        this.add(panel);

		purchaseButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				System.out.println("purchase");
				communicationController.sendClientMessage("purchase");
				// GameEngine.getInstance().buyProperty(pSquare, GameEngine.getInstance().getPlayerController().getCurrentPlayer());
				// also need to make a distinction between buying a utility and buying a property
			} 
		} );

		actionButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				System.out.println("action");
			} 
		} );
		
		rollDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("roll dice");
			} 
		} );
		
		this.setVisible(true);
		
	}
}
