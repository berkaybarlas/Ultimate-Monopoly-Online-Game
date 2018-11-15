package com.nullPointer.UI;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JPanel {
    private Board board;
    private DiceDisplay diceDisplay;
    private ButtonPanel buttonPanel;

    public GameWindow() {
        super();

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
        board = new Board(new Point(100,0),1000);
        contentPane.add(board, BorderLayout.LINE_START);

        buttonPanel = new ButtonPanel();
        contentPane.add(buttonPanel);

        JPanel rightSide = new JPanel();
        rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));

        PlayerPanel playerPanel = new PlayerPanel();
        rightSide.add(playerPanel);
        MessageBox msg=new MessageBox();
        rightSide.add(msg);
        contentPane.add(rightSide);

        diceDisplay = new DiceDisplay();
        contentPane.add(diceDisplay);

        this.add(contentPane);

    }
    
    

    public ButtonPanel getButtonPanel() {
		return buttonPanel;
	}



	public void paint(Graphics g) {
        super.paint(g);

        //board.paint(g);
    }
}
