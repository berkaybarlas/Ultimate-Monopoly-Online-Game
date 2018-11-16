package com.nullPointer.UI;

import com.nullPointer.Controller.CommunicationController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class MenuWindow extends JPanel {
    private JButton joinButton = null;
    private JButton serverCreateButton = null;
    private CommunicationController communicationController = CommunicationController.getInstance();
    private Navigator navigator = Navigator.getInstance();
	private static String[] playerNumOptions = {"2","3","4","5","6","7","8","9","10", "11", "12"};

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
                
                String playerNumberString = (String) JOptionPane.showInputDialog(panel, "Please choose the number of players \n",
        				"Player No Window", JOptionPane.PLAIN_MESSAGE, null, playerNumOptions, "2");      
            }
        });
        serverCreateButton.setVisible(true);
        panel.add(serverCreateButton);

        joinButton = new JButton("Join Server");
        joinButton.setToolTipText("Join the game server");
        joinButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ip = JOptionPane.showInputDialog(panel, "Enter the IP address you'd like to join.","IP address needed.");

                if(validateIP(ip)){
                    communicationController.createClient(ip);
                    navigator.serverScreen();
                }
            }
        });
        panel.add(joinButton);

    }

    private boolean validateIP(String string){
        ////127.0.0.1
        boolean result = false;
        String[] stringList = string.split("\\.");
        if(stringList.length == 4){
            String notInteger = Arrays.stream(stringList).filter(part->!isInteger(part)).findFirst().orElse(null);
            if(notInteger == null)
            result = true;
        }
        System.out.println("Validation result " + result);
        return result;
    }

    public static boolean isInteger(String s) {
        return isInteger(s,10);
    }

    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }
}

