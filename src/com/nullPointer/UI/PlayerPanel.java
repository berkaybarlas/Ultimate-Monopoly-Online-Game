package com.nullPointer.UI;

import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;
import com.nullPointer.Domain.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayerPanel extends JPanel implements Observer {

    private JPanel panel;
    private JScrollPane scrollPane;
    private JPanel userPanel, displayPanel;
    private JTextField textField;
    private GameEngine gameEngine = GameEngine.getInstance();
    private JTextArea textArea;
    ArrayList<Player> pList;
    private int lastSelected = 0;

    public PlayerPanel() {

        userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        userPanel.setBounds(0, 0, 120, 600);
        scrollPane = new JScrollPane(userPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(0, 0, 120, 300);
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(100, 300));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.black);
        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(390, 300));
        textArea.setEditable(false);

        addPlayerButtons();
        panel.add(scrollPane);
        this.add(panel);
        this.add(textArea);
        gameEngine.subscribe(this);
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (pList.size() != 0) {
            textArea.setText(pList.get(lastSelected).toString());
        }
        g.drawRect(800, 800, 1000, 100);
    }

    public void addPlayerButtons() {
        pList = gameEngine.getPlayerController().getPlayers();
        ArrayList<JButton> pButtons = new ArrayList<JButton>();

        for (int i = 0; i < pList.size(); i++) {
            pButtons.add(new JButton(pList.get(i).getName()));
            pButtons.get(i).setPreferredSize(new Dimension(100, 100));
            int currentPlayerIndex = i;
            Player player = pList.get(i);
            pButtons.get(i).addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Player currentPlayer = pList.get(currentPlayerIndex);
//                    System.out.println(.getName());
                    textArea.setText(currentPlayer.toString());
                    lastSelected = currentPlayerIndex;
                    gameEngine.getPlayerController().setChosen(player);
                }
            });
            userPanel.add(pButtons.get(i));
            userPanel.validate();
        }
    }

    @Override
    public void onEvent(String message) {
        if (message.equals("refresh")) {
            if (pList != null && pList.size() > 0) {
                textArea.setText(pList.get(lastSelected).toString());
                this.repaint();
            }
        } else if (message.equals("initializePlayers")) {
            addPlayerButtons();
        }
    }
}
