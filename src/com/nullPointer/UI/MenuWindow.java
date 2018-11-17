package com.nullPointer.UI;

import com.nullPointer.Domain.Controller.CommunicationController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class MenuWindow extends JPanel {
    JPanel buttonPanel;
    private JButton joinButton = null;
    private JButton serverCreateButton = null;
    private CommunicationController communicationController = CommunicationController.getInstance();
    private Navigator navigator = Navigator.getInstance();
	private static String[] playerNumOptions = {"2","3","4","5","6","7","8","9","10", "11", "12"};

    private Image image;
    private int imageLength;
    private File imageSrc = new File("./assets/monopoly_logo.png");

    public MenuWindow() {

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        this.add(buttonPanel, BorderLayout.CENTER);

        addButtons(buttonPanel);

        try {
            image = ImageIO.read(imageSrc);
            image = image.getScaledInstance(400, 265, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        //g.setColor(color);
        //g.fillRect(position.x, position.y, length, length);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        super.paint(g);
        buttonPanel.setLocation((screenSize.width - buttonPanel.getWidth())/2,300);
        Point position = new Point((screenSize.width - image.getWidth(null))/2,100);
        g.drawImage(image, position.x, position.y, null);

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

