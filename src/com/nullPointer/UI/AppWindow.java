package com.nullPointer.UI;

import com.nullPointer.Domain.Controller.CommunicationController;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Observer;
import com.nullPointer.Domain.SaveLoadManager;
import com.nullPointer.Domain.Server.ServerInfo;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class AppWindow extends JFrame implements Observer {
    private GameWindow gameWindow;
    private MenuWindow menuWindow;
    private ServerWindow serverWindow;
    private JButton loadButton = null;
    private JButton messageButton = null;
    private JButton menuButton = null;
    private JButton gameButton = null;
    private CommunicationController communicationController = CommunicationController.getInstance();
    private GameEngine gameEngine = GameEngine.getInstance();
    private Navigator navigator = Navigator.getInstance();
    private ServerInfo serverInfo = ServerInfo.getInstance();

    private final CardLayout mainLayout = new CardLayout();
    private final JPanel panels = new JPanel(mainLayout);
    private final Border border = BorderFactory.createEmptyBorder(-10, 60, 60, 60);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public AppWindow() {
        super("Ultimate Monopoly");

        int width = screenSize.width - 15;
        int height = screenSize.height - 30;

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        GameEngine.getInstance().subscribe(this);
        JToolBar toolBar = new JToolBar();
        addButtons(toolBar);

        menuWindow = new MenuWindow();
        gameWindow = new GameWindow(width, height);
        serverWindow = new ServerWindow();

        //scrollPane = new JScrollPane(gameWindow);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.setPreferredSize(new Dimension(width, height));
        contentPane.add(toolBar, BorderLayout.NORTH);

        setContentPane(contentPane);

        JPanel menuPanel = menuWindow;
        //menuPanel.setBorder(border);
        //menuPanel.add(new JLabel("Menu"));
        panels.add(menuPanel, "Menu Panel");

        JPanel gamePanel = gameWindow;
        gamePanel.setBorder(border);
        //gamePanel.add(new JLabel("Game Screen"));
        panels.add(gamePanel, "Game Panel");

        JPanel serverPanel = serverWindow;
        serverPanel.setBorder(border);
        //serverPanel.add(new JLabel("Server Screen"));
        panels.add(serverPanel, "Server Panel");

        contentPane.add(panels, BorderLayout.CENTER);
        navigator.setLayout(mainLayout);
        navigator.setPanels(panels);

        gameEngine.subscribe(this::onEvent);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    protected void addButtons(JToolBar toolBar) {

        messageButton = new JButton("Save Game");
        messageButton.setToolTipText("Save the game server");
        messageButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    SaveLoadManager.getInstance().saveGame();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        toolBar.add(messageButton);

        loadButton = new JButton("Load");
        loadButton.setToolTipText("Load the program");
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        toolBar.add(loadButton);

        menuButton = new JButton("Menu");
        menuButton.setToolTipText("Menu window");
        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                navigator.menuScreen();
            }
        });
        toolBar.add(menuButton);

        gameButton = new JButton("Game");
        gameButton.setToolTipText("Game window");
        gameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                navigator.gameScreen();
            }
        });
        toolBar.add(gameButton);

    }

    @Override
    public void onEvent(String message) {

    }
}
