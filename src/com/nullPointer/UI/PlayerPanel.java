package com.nullPointer.UI;

import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;
import com.nullPointer.Domain.Observer;
import com.nullPointer.Utils.ColorSet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayerPanel extends JPanel implements Observer {

    private JPanel panel;
    private JScrollPane scrollPane;
    private JPanel userPanel;
    private GameEngine gameEngine = GameEngine.getInstance();
    private PlayerController playerController = PlayerController.getInstance();
    private JTextArea textArea;
    ArrayList<Player> pList;
    private int lastSelected = 0;
    ArrayList<JButton> playerButtons = new ArrayList<JButton>();

    public PlayerPanel() {
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(500, 310));
        this.setMaximumSize(new Dimension(500, 310));

        userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        //userPanel.setPreferredSize(new Dimension(120,300));
        scrollPane = new JScrollPane(userPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        //scrollPane.setBounds(0, 0, 120, 300);

        panel = new JPanel();
        panel.setPreferredSize(new Dimension(100, 300));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.black);
        panel.add(scrollPane);
        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(380, 300));
        textArea.setEditable(false);

        addPlayerButtons();
        this.add(panel);
        this.add(textArea);
        gameEngine.subscribe(this);
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (pList.size() != 0) {
            textArea.setText(pList.get(lastSelected).toString());
        }
        //g.drawRect(800, 800, 1000, 100);
    }

    public void refreshPlayerButtons() {

        for (int i = 0; i < pList.size(); i++) {
            int currentPlayerIndex = i;
            Player player = pList.get(i);

            if (player == playerController.getCurrentPlayer()) {
                playerButtons.get(i).setForeground(Color.GREEN);
            } else {
                playerButtons.get(i).setForeground(Color.BLACK);
            }
            userPanel.validate();
        }
    }

    public void addPlayerButtons() {
        pList = playerController.getPlayers();

        for (int i = 0; i < pList.size(); i++) {
            playerButtons.add(new JButton(pList.get(i).getName()));
            playerButtons.get(i).setPreferredSize(new Dimension(100, 100));
            int currentPlayerIndex = i;
            Player player = pList.get(i);
            if (player == playerController.getCurrentPlayer()) {
                playerButtons.get(i).setForeground(Color.GREEN);
            } else {
                playerButtons.get(i).setForeground(Color.BLACK);
            }
            playerButtons.get(i).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Player currentPlayer = pList.get(currentPlayerIndex);
                    textArea.setText(currentPlayer.toString());
                    lastSelected = currentPlayerIndex;
                    PlayerController.getInstance().setChosen(player);
                }
            });
            userPanel.add(playerButtons.get(i));
            userPanel.validate();
        }
    }

    @Override
    public void onEvent(String message) {
        if (message.equals("refresh")) {
            refreshPlayerButtons();
            if (pList != null && pList.size() > 0) {
                textArea.setText(pList.get(lastSelected).toString());
                this.repaint();
            }
        } else if (message.equals("initializePlayers")) {
            addPlayerButtons();
        }
    }
}
