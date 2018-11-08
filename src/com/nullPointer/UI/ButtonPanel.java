package com.nullPointer.UI;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonPanel extends JPanel{

    private JButton purchaseButton;
    private JButton actionButton;
    private JButton startButton;
    
	public ButtonPanel(){
		
		this.setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JPanel contentPane = new JPanel(null);
		contentPane.setPreferredSize(new Dimension(200, 200));
	
		this.add(contentPane, BorderLayout.SOUTH);

		purchaseButton = new JButton("Purchase Card");
		actionButton = new JButton("Make action");
		startButton = new JButton("Start Game");
		
		purchaseButton.setBounds(150,0,100,30);
		actionButton.setBounds(150,35,100,30);
		startButton.setBounds(150,70,100,30);
	
		contentPane.add(purchaseButton);
		contentPane.add(actionButton);
		contentPane.add(startButton);
		

		purchaseButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				System.out.println("purchase");
			} 
		} );

		actionButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				System.out.println("action");
			} 
		} );
		
		startButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				System.out.println("start");
			} 
		} );
		
		this.setVisible(true);

	}
}
