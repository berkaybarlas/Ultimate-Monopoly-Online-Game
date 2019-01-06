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

    private Image logo;
    private File logoSrc = new File("./assets/monopoly_logo.png");
    private Image background;
    private File backgroundSrc = new File("./assets/background3.png");

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int buttonHeight = 60;
    private int buttonWidth = 300;
    private JLabel back;
    private JLabel logoIcon;

    public MenuWindow() {
        buttonPanel = new JPanel();
        //buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
        buttonPanel.setPreferredSize(new Dimension(buttonWidth, 3 * buttonHeight));
        try {
            logo = ImageIO.read(logoSrc);
            logo = logo.getScaledInstance(600, 400, Image.SCALE_SMOOTH);
            background = ImageIO.read(backgroundSrc);
            background = background.getScaledInstance(
                    screenSize.width,
                    screenSize.height,
                    Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setOpaque(false);
        addButtons(buttonPanel);
        ImageIcon logoImage = new ImageIcon(logo);
        logoIcon = new JLabel();
        logoIcon.setIcon(logoImage);
        add(logoIcon);
        ImageIcon backgroundIcon = new ImageIcon(background);
        back = new JLabel();
        back.setIcon(backgroundIcon);
        add(back);
    }

    @Override
    public boolean isOptimizedDrawingEnabled() {
        return false;
    }

    public void paint(Graphics g) {
        //g.setColor(color);
        //g.fillRect(position.x, position.y, length, length);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        super.paint(g);
        //g.drawImage(background, 0, 0, null);
        //g.drawImage(logo, position.x, position.y, null);
        buttonPanel.setLocation((screenSize.width - buttonPanel.getWidth()) / 2, 300);
        back.setLocation(0, 0);
        Point position = new Point((screenSize.width - logo.getWidth(null)) / 2, 0);
        logoIcon.setLocation(position.x, position.y);
    }

    private void addButtons(JPanel panel) {

        serverCreateButton = new CustomButton("Start Server");
        serverCreateButton.setToolTipText("Start the game server");
        //serverCreateButton.setBounds(screenSize.width / 2 - buttonWidth / 2, screenSize.height / 2 - buttonHeight / 2 - 75, buttonWidth,
        //       buttonHeight);
        serverCreateButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        serverCreateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                communicationController.startServer();
                communicationController.createClient();
                navigator.serverScreen();

//                String playerNumberString = (String) JOptionPane.showInputDialog(panel, "Please choose the number of players \n",
//                        "Player No Window", JOptionPane.PLAIN_MESSAGE, null, playerNumOptions, "2");
            }
        });
        serverCreateButton.setVisible(true);
        panel.add(serverCreateButton);


        joinButton = new CustomButton("Join Server");
        joinButton.setToolTipText("Join the game server");
        joinButton.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        joinButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String ip = JOptionPane.showInputDialog(panel, "Enter the IP address you'd like to join.", "IP address needed.");

                if (validateIP(ip)) {
                    communicationController.createClient(ip);
                    navigator.serverScreen();
                } else {
                    ip = "172.20." + ip;
                    if (validateIP(ip)) {
                        communicationController.createClient(ip);
                        navigator.serverScreen();
                    }
                }
            }
        });
        panel.add(joinButton);

    }

    private boolean validateIP(String string) {
        ////127.0.0.1
        boolean result = false;
        if (string == null || string == "")
            return false;
        String[] stringList = string.split("\\.");
        if (stringList.length == 4) {
            String notInteger = Arrays.stream(stringList).filter(part -> !isInteger(part)).findFirst().orElse(null);
            if (notInteger == null)
                result = true;
        }
        System.out.println("Validation result " + result);
        return result;
    }

    public static boolean isInteger(String s) {
        return isInteger(s, 10);
    }

    public static boolean isInteger(String s, int radix) {
        if (s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) return false;
                else continue;
            }
            if (Character.digit(s.charAt(i), radix) < 0) return false;
        }
        return true;
    }

}

