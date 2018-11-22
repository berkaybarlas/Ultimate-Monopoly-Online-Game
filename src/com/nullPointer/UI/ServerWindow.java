package com.nullPointer.UI;

import com.nullPointer.Domain.Controller.CommunicationController;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Server.ServerInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class ServerWindow extends JPanel implements Observer {
    private JButton startGame;
    private JButton quitServer;
    private CommunicationController communicationController = CommunicationController.getInstance();
    private GameEngine gameEngine = GameEngine.getInstance();
    private ServerInfo serverInfo = ServerInfo.getInstance();
    private Navigator navigator = Navigator.getInstance();
    private JPanel buttonPanel;
    private List<ClientDisplay> clientDisplayList;

    public ServerWindow() {

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(new JLabel("Server Screen"));
        this.add(buttonPanel);
        addButtons(buttonPanel);
        gameEngine.subscribe(this);
        createClientDisplay();

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

    public List<ClientDisplay> createClientDisplay() {
        List<Integer> clientList = serverInfo.getClientList();
        clientDisplayList = new ArrayList<>();

        for (int i = 0; i < clientList.size(); i++) {
            JPanel clientPanel = new JPanel();
            ClientDisplay clientDisplay = new ClientDisplay("Computer" + (i + 1), new Point(200, i * 200));
            this.add(clientPanel);
            clientDisplayList.add(clientDisplay);
        }
        return clientDisplayList;
    }

    public void addClient() {
        int clientNumber = clientDisplayList.size();
        String clientName = "Computer" + (clientNumber + 1);
        Point clientDisplayPosition = new Point(200, clientNumber * 200);
        clientDisplayList.add(new ClientDisplay(clientName, clientDisplayPosition));
    }

    @Override
    public void onEvent(String message) {
        if (message.equals("newClient")) {
            this.addClient();
            repaint();
        }
    }

    public void paint(Graphics g) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        super.paint(g);
        clientDisplayList.forEach(clientDisplay -> clientDisplay.paint(g));
        buttonPanel.setLocation((screenSize.width - buttonPanel.getWidth()) / 2, 300);
    }
}

class ClientDisplay {

    String clientName;
    Point position;
    int width = 300;
    int height = 100;

    ClientDisplay(String name, Point position) {
        clientName = name;
        this.position = position;
    }

    public void paint(Graphics g) {
        Color color = new Color(255, 255, 255);
        g.setColor(color);
        g.fillRect(position.x, position.y, width, height);

        color = new Color(0, 0, 0);
        g.setColor(color);
        g.drawString(clientName, position.x + 20, position.y + height / 2);

    }
}