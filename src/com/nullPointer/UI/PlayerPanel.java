package com.nullPointer.UI;

import javax.swing.*;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class PlayerPanel extends JPanel implements Observer {

	private JPanel panel;
	private JScrollPane scrollPane;
	private JPanel userPanel, displayPanel;
	private JTextField textField;
	private GameEngine gameEngine = GameEngine.getInstance();
	private JTextArea textArea;
	ArrayList<Player> pList;
	public PlayerPanel() {

        userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        userPanel.setBounds(0, 0, 120, 600);
		scrollPane = new JScrollPane(userPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 120, 300);
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(100 ,300));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.black);	
//		textField = new JTextField();
//		textField.setPreferredSize(new Dimension(300, 300));
//		textField.setEditable(false);
		textArea = new JTextArea();
		textArea.setPreferredSize(new Dimension(390, 300));
		textArea.setEditable(false);
		
		pList = GameEngine.getInstance().getPlayerController().getPlayers();
		ArrayList<JButton> pButtons = new ArrayList<JButton>();
		
		for(int i=0;i<pList.size();i++) {
			pButtons.add(new JButton(pList.get(i).getName()));
			pButtons.get(i).setPreferredSize(new Dimension(100,100));
			int currentPlayerIndex = i;
			pButtons.get(i).addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent e) {
					System.out.println(pList.get(currentPlayerIndex).getName());
//					textField.setText();
//					textArea.append(pList.get(currentPlayerIndex).toString());
					textArea.setText(pList.get(currentPlayerIndex).toString());
				} 
			} );
			userPanel.add(pButtons.get(i));
			userPanel.validate();
		}
		
		panel.add(scrollPane);
		this.add(panel);
//		this.add(textField);
		this.add(textArea);
		
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawRect(800,800,1000,100);
	}

	@Override
	public void onEvent(String message) {
		if(message.equals("refresh")) {
			this.repaint();
		}
	}
}
