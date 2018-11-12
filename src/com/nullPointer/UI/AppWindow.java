package com.nullPointer.UI;

import com.nullPointer.Controller.CommunicationController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AppWindow extends JFrame {
    private GameWindow gameWindow;
    private MenuWindow menuWindow;
    private ServerWindow serverWindow;
    private JPanel currentFrame;
    private JButton button = null;
    private JButton joinButton = null;
    private JButton serverButton = null;
    private JButton messageButton = null;
    private JButton menuButton = null;
    private JButton gameButton = null;
    private JScrollPane scrollPane = null;
    private CommunicationController communicationController = CommunicationController.getInstance();

    private final CardLayout mainLayout = new CardLayout();
    private final JPanel panels = new JPanel(mainLayout);
    private final Border border = BorderFactory.createEmptyBorder(-10, 60, 60, 60);

    public AppWindow() {
        super("Ultimate Monopoly");

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


        JToolBar toolBar = new JToolBar();
        addButtons(toolBar);

        menuWindow = new MenuWindow();
        gameWindow = new GameWindow();
        serverWindow = new ServerWindow();
        //scrollPane = new JScrollPane(gameWindow);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setPreferredSize(new Dimension(1600, 1000));
        contentPane.add(toolBar, BorderLayout.NORTH);

        setContentPane(contentPane);

        JPanel menuPanel = menuWindow;
        menuPanel.setBorder(border);
        menuPanel.add(new JLabel("Menu"));
        panels.add(menuPanel, "Menu Panel");

        JPanel gamePanel = gameWindow;
        gamePanel.setBorder(border);
        gamePanel.add(new JLabel("Card 2"));
        panels.add(gamePanel, "Game Panel");

        JPanel serverPanel = serverWindow;
        serverPanel.setBorder(border);
        serverPanel.add(new JLabel("Server"));
        panels.add(serverPanel, "Second Panel");

        contentPane.add(panels, BorderLayout.CENTER);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    protected void addButtons(JToolBar toolBar) {

        serverButton = new JButton("Start Server");
        serverButton.setToolTipText("Start the game server");
        serverButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                communicationController.startServer();
                communicationController.createClient();
                serverButton.setEnabled(false);
                joinButton.setEnabled(false);
            }
        });
        toolBar.add(serverButton);

        joinButton = new JButton("Join Server");
        joinButton.setToolTipText("Join the game server");
        joinButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                communicationController.createClient();
                serverButton.setEnabled(false);
                joinButton.setEnabled(false);
            }
        });
        toolBar.add(joinButton);

        messageButton = new JButton("Send Message");
        messageButton.setToolTipText("Join the game server");
        messageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                communicationController.sendClientMessage("hello");
            }
        });
        toolBar.add(messageButton);

        menuButton = new JButton("Menu");
        menuButton.setToolTipText("Menu window");
        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainLayout.next(panels);
            }
        });
        toolBar.add(menuButton);

        gameButton = new JButton("Game");
        gameButton.setToolTipText("Game window");
        gameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainLayout.next(panels);
            }
        });
        toolBar.add(gameButton);

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
