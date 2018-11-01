package com.nullPointer.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AppWindow extends JFrame {
    private GameWindow gameWindow;
    private JButton button = null;
    private JButton joinButton = null;
    private JButton serverButton = null;

    public AppWindow() {
        super("Ultimate Monopoly");

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


        JToolBar toolBar = new JToolBar();
        addButtons(toolBar);

        gameWindow = new GameWindow();

        JScrollPane scrollPane = new JScrollPane(gameWindow);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.setPreferredSize(new Dimension(1300, 800));
        contentPane.add(toolBar, BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        setContentPane(contentPane);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    protected void addButtons(JToolBar toolBar) {

        serverButton = new JButton("Start Server");
        serverButton.setToolTipText("Start the game server");
        serverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //startServer();
            }
        });
        toolBar.add(serverButton);

        joinButton = new JButton("Join Server");
        joinButton.setToolTipText("Join the game server");
        joinButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //joinServer();
            }
        });
        toolBar.add(joinButton);

        button = new JButton("Quit");
        button.setToolTipText("Quit the program");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        toolBar.add(button);
    }
}
