package com.nullPointer.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JPanel {
    private Board board;
    private DiceDisplay diceDisplay;
    private JButton rollDiceButton;
    private JButton purchaseButton;
    private JButton actionButton;
    private JButton startButton;
    public GameWindow() {
        super();
        board = new Board(new Point(550,50),700);
        setButtons();
    }

    public void setButtons() {
        rollDiceButton = new JButton("Roll Dice");
        rollDiceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //rollDice();
            }
        });
        this.add(rollDiceButton);
        rollDiceButton.setEnabled(false);

        purchaseButton = new JButton("Purchase Card");
        purchaseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //purchaseCard();
            }
        });
        this.add(purchaseButton);
        purchaseButton.setEnabled(false);

        actionButton = new JButton("Make action");
        actionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //makeAction();
            }
        });
        this.add(actionButton);
        actionButton.setEnabled(false);

        startButton = new JButton("Start Game");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //start();
            }
        });
        this.add(startButton);
        startButton.setEnabled(true);

    }

    public void paint(Graphics g) {
        super.paint(g);
        board.paint(g);
        rollDiceButton.setLocation(424,200);
        purchaseButton.setLocation(424,250);
        actionButton.setLocation(424,300);
        startButton.setLocation(424,400);
    }
}
