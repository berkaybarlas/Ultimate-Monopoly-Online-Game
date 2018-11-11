package com.nullPointer.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JPanel {
    private Board board;
    private DiceDisplay diceDisplay;
    private ButtonPanel buttonPanel;

    public GameWindow() {
        super();
        board = new Board(new Point(10,10),1000);
        buttonPanel = new ButtonPanel();
       this.add(buttonPanel, BorderLayout.SOUTH);
       
       diceDisplay = new DiceDisplay();
       this.add(diceDisplay, BorderLayout.NORTH);     
       
    }

    public void paint(Graphics g) {
        super.paint(g);
        board.paint(g);
    }
}
