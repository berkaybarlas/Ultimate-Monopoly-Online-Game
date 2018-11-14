package com.nullPointer.UI;

import javax.swing.*;
import java.awt.*;

public class PlayerPanel extends JPanel {
	private JPanel panel;

	public PlayerPanel() {
		panel = new JPanel();
		panel.setSize(500,300);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.black);

		this.add(panel);
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawRect(800,800,1000,100);
	}
}
