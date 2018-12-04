package com.nullPointer.UI;

import com.nullPointer.Domain.Controller.CommunicationController;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;
import com.nullPointer.Domain.Observer;
import com.nullPointer.Domain.Server.ServerInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;


public class ServerWindow extends JPanel implements Observer {
    private JButton startGame;
    private JButton addPlayer;
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

        addPlayer = new JButton("Add player");
        addPlayer.setToolTipText("add new player ");
        addPlayer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Player player = new Player("Test", serverInfo.getClientID());
                communicationController.sendClientMessage(player);
                //navigator.gameScreen();
            }
        });
        panel.add(addPlayer);

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
            if(clientList.size() <= 6) {
                ClientDisplay clientDisplay = new ClientDisplay("Computer " + (i + 1), new Point(100, i * 150));
                clientDisplayList.add(clientDisplay);
            } else {
                ClientDisplay clientDisplay = new ClientDisplay("Computer " + (i + 1), new Point(500, i * 150));
                clientDisplayList.add(clientDisplay);
            }
        }
        return clientDisplayList;
    }

    public void addClient() {
        createClientDisplay();
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

class ClientDisplay extends JPanel{

    String clientName;
    Point position;
    int width = 300;
    int height = 100;
    Random rand = new Random();
    Color clientColor;

    ClientDisplay(String name, Point position) {
        clientName = name;
        this.position = position;
        clientColor = new Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256));
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(clientColor);
        g2.setFont(new Font("Corbel", Font.PLAIN, 20));
        g2.drawString(clientName, position.x + 100, position.y + height / 2);
        g2.setStroke(new BasicStroke(2.0F));
        g2.drawRect(position.x, position.y, width, height);
    }
}