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

        board = new Board(new Point(10,10),1000);
      
        contentPane.add(board, BorderLayout.LINE_START);
        new Thread(board).start();
        MessageBox msg=new MessageBox();
        contentPane.add(msg, BorderLayout.LINE_END);

        this.add(contentPane);


        buttonPanel = new ButtonPanel();
        //this.add(buttonPanel, BorderLayout.SOUTH);

        diceDisplay = new DiceDisplay();
        //this.add(diceDisplay, BorderLayout.NORTH);

    }

    public void paint(Graphics g) {
        super.paint(g);
        //board.paint(g);
    }
}
