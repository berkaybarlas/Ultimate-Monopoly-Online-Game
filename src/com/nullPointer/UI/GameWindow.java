package com.nullPointer.UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class GameWindow extends JPanel {
    protected Board board;
    protected DiceDisplay diceDisplay;
    protected ButtonPanel buttonPanel;
    protected PlayerPanel playerPanel;

    public GameWindow(int width, int height) {
        super();

        int offset = 50;

        JPanel contentPane = new JPanel();
        //contentPane.setLayout(new BoxLayout(contentPane, BorderLayout));
        board = new Board(new Point(0,0), height - offset);

        contentPane.setBorder( new EmptyBorder(0,0,0,0) );
        contentPane.add(board, BorderLayout.LINE_START);
        new Thread(board).start();

        JPanel middleSide = new JPanel();
        middleSide.setLayout(new BoxLayout(middleSide, BoxLayout.Y_AXIS));

        diceDisplay = new DiceDisplay();
        middleSide.add(diceDisplay);
        buttonPanel = new ButtonPanel();
        middleSide.add(buttonPanel);
        contentPane.add(middleSide, BorderLayout.CENTER);
        
        JPanel rightSide = new JPanel();
        rightSide.setLayout(new BoxLayout(rightSide, BoxLayout.Y_AXIS));

        playerPanel = new PlayerPanel();
        rightSide.add(playerPanel);
        MessageBox msg=new MessageBox();
        rightSide.add(msg);
        contentPane.add(rightSide, BorderLayout.LINE_END);

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
