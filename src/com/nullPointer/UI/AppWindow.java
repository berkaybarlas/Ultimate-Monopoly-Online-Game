package com.nullPointer.UI;

import com.nullPointer.Domain.Controller.SaveLoadController;
import com.nullPointer.Domain.Model.Bot.BotBehaviour;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Observer;

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
    private JButton saveButton = null;
    private JButton menuButton = null;
    private JButton gameButton = null;
    private SaveLoadController saveGameController =  SaveLoadController.getInstance();
    private GameEngine gameEngine = GameEngine.getInstance();
    private Navigator navigator = Navigator.getInstance();

    private final CardLayout mainLayout = new CardLayout();
    private final JPanel panels = new JPanel(mainLayout);
    private final Border border = BorderFactory.createEmptyBorder(-10, 60, 60, 60);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public AppWindow() {
        super("Ultimate Monopoly");


        this.setExtendedState(JFrame.MAXIMIZED_BOTH);   // allows the app to open in fullscreen from the start

        int width = screenSize.width;   //used to be screenSize.width - 15
        int height = screenSize.height;  //used to be screenSize.height - 30

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
        new BotBehaviour();
        //scrollPane = new JScrollPane(gameWindow);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.PAGE_AXIS));
        contentPane.setPreferredSize(new Dimension(width, height));


        //contentPane.add(toolBar);
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

        //contentPane.add(panels, BorderLayout.CENTER);
        contentPane.add(panels);
        navigator.setLayout(mainLayout);
        navigator.setPanels(panels);

        gameEngine.subscribe(this::onEvent);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setVisible(true);   IDK if I should open this, window is already visible? -B.H.
    }


    protected void addButtons(JToolBar toolBar) {

        saveButton = new JButton("Save Game");
        saveButton.setToolTipText("Save the game server");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                	// whether the game is paused or not will be checked.
                    saveGameController.saveGame();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        toolBar.add(saveButton);

        loadButton = new JButton("Load");
        loadButton.setToolTipText("Load the program");
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					saveGameController.loadGame("savefile");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
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
