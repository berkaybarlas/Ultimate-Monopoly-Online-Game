package com.nullPointer.UI;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import com.nullPointer.Controller.CommunicationController;
import com.nullPointer.Model.GameEngine;
import com.nullPointer.Model.RegularDie;
import com.nullPointer.Model.SpeedDie;

public class DiceDisplay extends JPanel implements Observer{

    private JLabel diceValues;
    private JLabel label;
    private RegularDie regularDie = RegularDie.getInstance();
    private SpeedDie speedDie = SpeedDie.getInstance();
    private GameEngine gameEngine = GameEngine.getInstance();

    public DiceDisplay() {

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //this.setPreferredSize(new Dimension(50 ,50 ));
        //JPanel contentPane = new JPanel(null);
    	//contentPane.setPreferredSize(new Dimension(200, 200));
    	//this.add(contentPane, BorderLayout.NORTH);

    	this.setBounds(0,0,100,300);
        this.setBackground(Color.WHITE);

        label=new JLabel("Dice Values");
        label.setLocation(0,0);
    	this.add(label);
    	diceValues = new JLabel();
        diceValues.setText("Total of dice: "+ regularDie.getLastValues().toString());
        diceValues.setLocation(0,30);
        this.add(diceValues);
    	this.setVisible(true);
        gameEngine.subscribe(this::onEvent);
	}

    public void paint() {
        diceValues.setText("Total of dice: "+ regularDie.getLastValues().toString());
        repaint();
    }

    @Override
    public void onEvent(String message) {
        if(message.equals("refresh")) {
            this.paint();
        }
    }
}
