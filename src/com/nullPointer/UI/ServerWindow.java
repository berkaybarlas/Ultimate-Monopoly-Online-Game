package com.nullPointer.UI;

import com.nullPointer.Controller.CommunicationController;
import com.nullPointer.Utils.ColorSet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ServerWindow extends JPanel {
    private JButton startGame;
    private JButton quitServer;
    private CommunicationController communicationController = CommunicationController.getInstance();
    private Navigator navigator = Navigator.getInstance();

    ClientDisplay clientDisplay;

    public ServerWindow() {

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(new JLabel("Server Screen"));
        this.add(buttonPanel);
        addButtons(buttonPanel);

        clientDisplay = new ClientDisplay("client1");

    }

    private void addButtons(JPanel panel) {

        startGame = new JButton("Start Game");
        startGame.setToolTipText("Start the game ");
        startGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startServer();
                //navigator.gameScreen();
            }
        });
        panel.add(startGame);

        quitServer = new JButton("Quit Server ");
        quitServer.setToolTipText("Quit from the server");
        quitServer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                communicationController.removeClient();
                navigator.menuScreen();
            }
        });
        panel.add(quitServer);

    }

    private void startServer() {
        communicationController.sendClientMessage("game/start");
    }

    public void paint(Graphics g) {
        super.paint(g);
        clientDisplay.paint(g);
    }

}

 class ClientDisplay {
    ClientDisplay(String name) {

    }

    public void paint(Graphics g) {
        Color color = new Color(0, 0,0);
        g.setColor(color);
        g.fillRect(300, 300, 200, 200);

        g.drawString("",  200 + 20, 200);

    }
}