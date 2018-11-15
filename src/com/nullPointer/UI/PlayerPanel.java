package com.nullPointer.UI;

import javax.swing.*;
import com.nullPointer.Model.GameEngine;
import com.nullPointer.Model.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class PlayerPanel extends JPanel implements Observer {
	private JPanel panel;
	private JScrollPane scrollPane;
	private JPanel userPanel, displayPanel;
	private JTextField textField;
	public PlayerPanel() {
//		this.setLayout(null);
        userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        userPanel.setBounds(0, 0, 120, 600);
		scrollPane = new JScrollPane(userPanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(0, 0, 120, 300);
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(100 ,300));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.black);	
		textField = new JTextField();
		textField.setPreferredSize(new Dimension(300, 300));
		textField.setEditable(false);
		
		
		ArrayList<Player> pList = GameEngine.getInstance().getPlayerController().getPlayers();
		ArrayList<JButton> pButtons = new ArrayList<JButton>();
		
		for(int i=0;i<pList.size();i++) {
			pButtons.add(new JButton(pList.get(i).getName()));
			pButtons.get(i).setPreferredSize(new Dimension(100,100));
			int currentPlayerIndex = i;
			pButtons.get(i).addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent e) {
					System.out.println(pList.get(currentPlayerIndex).getName());
					textField.setText(pList.get(currentPlayerIndex).getName()+"\n"+pList.get(currentPlayerIndex).getMoney());
				} 
			} );
			userPanel.add(pButtons.get(i));
			userPanel.validate();
		}
		panel.add(scrollPane);
		this.add(panel);
		this.add(textField);
		
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
