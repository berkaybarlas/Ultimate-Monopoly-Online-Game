package com.nullPointer.UI;

import com.nullPointer.Controller.CommunicationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuWindow extends JPanel {
    private JButton joinButton = null;
    private JButton serverCreateButton = null;
    private CommunicationController communicationController = CommunicationController.getInstance();
    private Navigator navigator = Navigator.getInstance();

    public MenuWindow() {

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(new JLabel("Menu"));
        this.add(buttonPanel);
        addButtons(buttonPanel);

    }

    private void addButtons(JPanel panel) {

        serverCreateButton = new JButton("Start Server");
        serverCreateButton.setToolTipText("Start the game server");
        serverCreateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                communicationController.startServer();
                communicationController.createClient();
                navigator.serverScreen();

            }
        });
        serverCreateButton.setVisible(true);
        panel.add(serverCreateButton);

        joinButton = new JButton("Join Server");
        joinButton.setToolTipText("Join the game server");
        joinButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                communicationController.createClient();
                navigator.serverScreen();
            }
        });
        panel.add(joinButton);

    }

}

