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
        //setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        board = new Board(new Point(10,10),1000);
        contentPane.add(board, BorderLayout.LINE_START);

        buttonPanel = new ButtonPanel();
        contentPane.add(buttonPanel);

        JPanel rightSide = new JPanel();
        PlayerPanel playerPanel = new PlayerPanel();
        rightSide.add(playerPanel);
        MessageBox msg=new MessageBox();
        rightSide.add(msg);
        contentPane.add(rightSide, BorderLayout.LINE_END);

        diceDisplay = new DiceDisplay();
        contentPane.add(diceDisplay, BorderLayout.CENTER);

        this.add(contentPane);



    }

    public void paint(Graphics g) {
        super.paint(g);

        //board.paint(g);
    }
}
