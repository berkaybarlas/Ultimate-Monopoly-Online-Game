package com.nullPointer.UI;

import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.*;

import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;

public class Animator extends JPanel implements Runnable {

    private static Animator _instance;
    Vector elementsToDraw = new Vector();
    private PlayerController playerController = PlayerController.getInstance();
    private GameEngine gameEngine = GameEngine.getInstance();
    private List<Player> playerList = new ArrayList<>();
    private JPanel animFrame;

    public Animator(JPanel jFrame) {
    	animFrame=jFrame;
        setPreferredSize(new Dimension(1,1));
        setOpaque(false);
        _instance = this;

    }
    public static Animator getInstance(){
        return _instance;
    }

    @Override
    public void run() {
        //System.out.println("[Animator]: Thread started");
        while (true) {
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                System.out.println("Program Interrupted");
                System.exit(0);
            }
            animFrame.repaint();
        }
    }

    @Override
    public void paint(Graphics g) {
        //System.out.println("Paint Method Called");
        Enumeration e = elementsToDraw.elements();
        while (e.hasMoreElements())
            ((Drawable) e.nextElement()).draw(g);
    }

    public void addDrawable(Drawable d) {
        elementsToDraw.addElement(d);
    }

    public void removeDrawable(Drawable d) {
        elementsToDraw.removeElement(d);
    }
}
