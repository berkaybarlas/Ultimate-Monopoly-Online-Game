package com.nullPointer.UI;

import com.nullPointer.Domain.Controller.CommunicationController;
import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Controller.SaveLoadController;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;
import com.nullPointer.Domain.Observer;
import com.nullPointer.Domain.Server.ServerInfo;
import com.nullPointer.Utils.ColorSet;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.Random;


public class ServerWindow extends JPanel implements Observer {
    private JButton startGame, addPlayer, loadGame, saveGame, quitServer, rightButton, leftButton;
    private CommunicationController communicationController = CommunicationController.getInstance();
    private PlayerController playerController = PlayerController.getInstance();
    private GameEngine gameEngine = GameEngine.getInstance();
    private ServerInfo serverInfo = ServerInfo.getInstance();
    private Navigator navigator = Navigator.getInstance();
    private SaveLoadController saveLoadController = SaveLoadController.getInstance();
    private JPanel buttonPanel, playerPanel, cPanel, pPanel;
    private JScrollPane scrollPane;
    private JTextField textField;
    private List<ClientDisplay> clientDisplayList;
    private ArrayList<CustomButton> bList = new ArrayList<CustomButton>();
    private ArrayList<Image> pawnImages = new ArrayList<Image>();
    private ArrayList<File> pawnFiles = new ArrayList<File>();
    private int cnt = 0;
    private int buttonHeight = 40;
    private int buttonWidth = 180;

    private JPanel savePanel;


    private int pButtonHeight = 50;
    private int pButtonWidth = 200;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private Image background, rightButtonImg, leftButtonImg, p1, p2, p3, p4, p5, p6;
    private File backgroundSrc = new File("./assets/background2.jpg");
    private File RBISrc = new File("./assets/b1.png");
    private File LBISrc = new File("./assets/b2.png");
    private File P1Src = new File("./assets/pawns/hat.png");
    private File P2Src = new File("./assets/pawns/iron.png");
    private File P3Src = new File("./assets/pawns/rende.png");
    private File P4Src = new File("./assets/pawns/car.png");
    private File P5Src = new File("./assets/pawns/ship.png");
    private File P6Src = new File("./assets/pawns/boot.png");

    private ImageIcon dispImg;
    private Image logo;
    private File logoSrc = new File("./assets/monopoly_logo.png");

    JLabel back, buffer, logoIcon;

    public ServerWindow() {

        buttonPanel = new JPanel();
        //buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        //buttonPanel.add(new JLabel("Server Screen"));
        buttonPanel.setPreferredSize(new Dimension(buttonWidth, 4 * buttonHeight));
        buttonPanel.setOpaque(false);
        this.add(buttonPanel);
        addButtons(buttonPanel);

        savePanel = new JPanel();

        gameEngine.subscribe(this);

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

        createClientDisplay();
        createPlayerDisplay();

        ImageIcon backgroundIcon = new ImageIcon(background);

        ImageIcon logoImage = new ImageIcon(logo);
        logoIcon = new JLabel();
        logoIcon.setIcon(logoImage);
        add(logoIcon);
        back = new JLabel();
        back.setIcon(backgroundIcon);
        add(back);
        this.setOpaque(false);

    }

    private void addButtons(JPanel panel) {

        startGame = new CustomButton("Start Game");
        startGame.setToolTipText("Start the game ");
        startGame.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        startGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startServer();
                System.out.println("Game started with " + GameEngine.getInstance().getPlayerController().getPlayers().size() + " players.");
                //navigator.gameScreen();
            }
        });
        panel.add(startGame);

        loadGame = new CustomButton("Load Game");
        loadGame.setToolTipText("Load game data");
        loadGame.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        loadGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> savedFiles = saveLoadController.getSavedFiles();

                String loadFileName = (String) JOptionPane.showInputDialog(savePanel, "Choose file \n",
                        "Load Panel", JOptionPane.PLAIN_MESSAGE, null, savedFiles.toArray(), null);
                System.out.println("[ServerWindow]: " + loadFileName);

                try {
                    saveLoadController.loadGame(loadFileName);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        });
        panel.add(loadGame);

        quitServer = new CustomButton("Quit Server ");
        quitServer.setToolTipText("Quit from the server");
        quitServer.setPreferredSize(new Dimension(buttonWidth, buttonHeight));
        quitServer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                communicationController.removeClient();
                navigator.menuScreen();
            }
        });
        panel.add(quitServer);

    }

    private void startServer() {
        communicationController.sendClientMessage("game/start");
    }

    public List<ClientDisplay> createClientDisplay() {
        List<Integer> clientList = serverInfo.getClientList();
        clientDisplayList = new ArrayList<>();
        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        for (int i = 0; i < clientList.size(); i++) {
            if (i % 2 == 0) {
                ClientDisplay clientDisplay = new ClientDisplay("Computer " + (i + 1), new Point(50, i * (height - 200) / 12 + 100), ColorSet.getPlayerColors().get(i));
                clientDisplayList.add(clientDisplay);
            } else {
                ClientDisplay clientDisplay = new ClientDisplay("Computer " + (i + 1), new Point(300, (i - 1) * (height - 200) / 12 + 100), ColorSet.getPlayerColors().get(i));
                clientDisplayList.add(clientDisplay);
            }
        }
        return clientDisplayList;
    }

    public void addClient() {
        createClientDisplay();
    }

    public void createPlayerDisplay() {
        int panelWidth = pButtonWidth + 55;
        int panelHeight = 6 * (pButtonHeight + 5);
        playerPanel = new JPanel();
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.setBackground(ColorSet.SERVERBACKGROUND_LIGHT);
        cPanel = new JPanel();
        cPanel.setPreferredSize(new Dimension(panelWidth, 500));
        cPanel.setOpaque(false);
        scrollPane = new JScrollPane(playerPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(panelWidth, panelHeight));
        scrollPane.setBorder(BorderFactory.createLineBorder(ColorSet.SERVERBACKGROUND_LIGHT, 2, true));
        initTextField();
        initPawnImages();
        addPlayer = new CustomButton("Add Player");
        addPlayer.setToolTipText("Press to add your player!");
        addPlayer.setPreferredSize(new Dimension(230, buttonHeight));
        addPlayer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Player player = new Player(textField.getText(), serverInfo.getClientID(),cnt);
                communicationController.sendClientMessage(player);
                //navigator.gameScreen();
//                Board.getInstance().addNewPawn(player,pawnFiles.get(cnt), null);
                Board.getInstance().getPlayerCoords().put(player, null);
                textField.setText("Enter player name here!");
            }
        });
        initSelectionButtons();
        cPanel.add(textField);
        cPanel.add(leftButton);
        cPanel.add(pPanel);
        cPanel.add(rightButton);
        cPanel.add(addPlayer);
        this.add(scrollPane);
        this.add(cPanel);
    }

    public void initTextField() {
        textField = new JTextField("Enter player name here!");
        textField.setPreferredSize(new Dimension(230, 50));
        textField.setFont(new Font("Corbel", Font.PLAIN, 15));
        textField.setBackground(ColorSet.SERVERBACKGROUND_LIGHT);
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                textField.setText("");
            }
        });
    }

    public void initPawnImages() {
        try {
            p1 = ImageIO.read(P1Src);
            p1 = p1.getScaledInstance(((BufferedImage) p1).getWidth() / 8, ((BufferedImage) p1).getHeight() / 8, Image.SCALE_SMOOTH);
            pawnImages.add(p1);
            pawnFiles.add(P1Src);
            p2 = ImageIO.read(P2Src);
            p2 = p2.getScaledInstance(((BufferedImage) p2).getWidth() / 8, ((BufferedImage) p2).getHeight() / 8, Image.SCALE_SMOOTH);
            pawnImages.add(p2);
            pawnFiles.add(P2Src);
            p3 = ImageIO.read(P3Src);
            p3 = p3.getScaledInstance(((BufferedImage) p3).getWidth() / 8, ((BufferedImage) p3).getHeight() / 8, Image.SCALE_SMOOTH);
            pawnImages.add(p3);
            pawnFiles.add(P3Src);
            p4 = ImageIO.read(P4Src);
            p4 = p4.getScaledInstance(((BufferedImage) p4).getWidth() / 8, ((BufferedImage) p4).getHeight() / 8, Image.SCALE_SMOOTH);
            pawnImages.add(p4);
            pawnFiles.add(P4Src);
            p5 = ImageIO.read(P5Src);
            p5 = p5.getScaledInstance(((BufferedImage) p5).getWidth() / 8, ((BufferedImage) p5).getHeight() / 8, Image.SCALE_SMOOTH);
            pawnImages.add(p5);
            pawnFiles.add(P5Src);
            p6 = ImageIO.read(P6Src);
            p6 = p6.getScaledInstance(((BufferedImage) p6).getWidth() / 8, ((BufferedImage) p6).getHeight() / 8, Image.SCALE_SMOOTH);
            pawnImages.add(p6);
            pawnFiles.add(P6Src);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initSelectionButtons() {
        rightButton = new JButton();
        leftButton = new JButton();
        try {
            rightButtonImg = ImageIO.read(RBISrc);
            rightButtonImg = rightButtonImg.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            rightButton.setIcon(new ImageIcon(rightButtonImg));
            leftButtonImg = ImageIO.read(LBISrc);
            leftButtonImg = leftButtonImg.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            leftButton.setIcon(new ImageIcon(leftButtonImg));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pPanel = new JPanel();
        pPanel.setPreferredSize(new Dimension(100, 45));
        pPanel.setBackground(ColorSet.SERVERBACKGROUND_LIGHT);
        pPanel.setBorder(BorderFactory.createLineBorder(ColorSet.ButtonPrimary, 2, true));
        dispImg = new ImageIcon(pawnImages.get(cnt));
        buffer = new JLabel();
        buffer.setIcon(dispImg);
        pPanel.add(buffer);
        rightButton.setBorder(null);
        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cnt++;
                dispImg = new ImageIcon(pawnImages.get(cnt % pawnImages.size()));
                buffer.setIcon(dispImg);
                validate();
                repaint();
            }
        });
        leftButton.setBorder(null);
        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cnt--;
                dispImg = new ImageIcon(pawnImages.get((cnt + pawnImages.size()) % pawnImages.size()));
                buffer.setIcon(dispImg);
                validate();
                repaint();
            }
        });
    }

    public void updateButtonColor() {
        playerPanel.removeAll();
        playerPanel.validate();
        repaint();
        ArrayList<Player> pList = PlayerController.getInstance().getPlayers();
        for (Player player : pList) {
            addPlayerButton(player);
        }
        playerPanel.validate();
        repaint();
    }

    public void addPlayerButton(Player player) {
        List<Integer> clientList = serverInfo.getClientList();
        CustomButton newButton = new CustomButton(player.getName());
        newButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                player.setClientID(serverInfo.getClientID());
                communicationController.sendClientMessage(PlayerController.getInstance());
                // Basladigi konumun dogru, ancak oyun ilk basladiginda gosterilmiyor. Zar attiktan sonra o konumdan harekete basliyor.
                // URGENT
                // Iki ayri bilgisayarla oynayinca isler karisiyor (iki oyuncu birbirinden ayrilamiyor gibi bir durum var.)
                // Ayrica pawn secenekleri de, ornegin, bir bilgisayarda iki utu, diger bilgisayarda iki sapka gibi oluyor.
                int xCoord = Board.getInstance().getSquareMap().get(player.getPosition())[0].x + Board.getInstance().getSquareMap().get(player.getPosition())[1].x;
                int yCoord = Board.getInstance().getSquareMap().get(player.getPosition())[0].y + Board.getInstance().getSquareMap().get(player.getPosition())[1].y;
//                Board.getInstance().addNewPawn(player,pawnFiles.get(player.getPlaceHolder()),new Point(xCoord, yCoord));
                // Basladigi konumdan duzgun hareket ediyor ama baska gozukmuyor. Needs to be fixed.
                Board.getInstance().getPlayerCoords().put(player, new Point(xCoord, yCoord));
            }
        });
        int clientPosition = clientList.indexOf(player.getClientID());
        if (clientPosition == -1 ) {
            clientPosition = 12;
            /**
             *
             *
             * this client does not exits if nobody choose this player it should be bot automaticly
             *
             *
            */ //hata
        }
        newButton.setPrimaryColor(ColorSet.getPlayerColors().get(clientPosition));
        newButton.setPreferredSize(new Dimension(pButtonWidth + 47, pButtonHeight));
        newButton.setMaximumSize(new Dimension(pButtonWidth + 47, pButtonHeight));
        newButton.setMinimumSize(new Dimension(pButtonWidth + 47, pButtonHeight));
        bList.add(newButton);

        playerPanel.add(newButton);
        playerPanel.add(Box.createRigidArea(new Dimension(5, 5)));

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    public void addPlayer() {
        ArrayList<Player> pList = playerController.getPlayers();
        if (pList.size() > 0) {
            Player player = pList.get(pList.size() - 1);
            addPlayerButton(player);
        }
    }

    public void addOtherPlayers() {
        if (bList.size() == 0) {
            ArrayList<Player> pList = playerController.getPlayers();
            for (Player player : pList) {
                addPlayerButton(player);
            }
        }
    }

    public void paint(Graphics g) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        super.paint(g);
        //g.drawImage(background, 0, 0, null);
        back.setLocation(0, 0);
        clientDisplayList.forEach(clientDisplay -> clientDisplay.paint(g));
        //bList.forEach(customButton -> customButton.paint(g));
        buttonPanel.setLocation((screenSize.width - buttonPanel.getWidth()) / 2, 400);
        scrollPane.setLocation((screenSize.width) / 4 * 3, screenSize.height / 2 - 270);
        cPanel.setLocation((screenSize.width) / 4 * 3, scrollPane.getHeight() + scrollPane.getY());

        Point position = new Point((screenSize.width - logo.getWidth(null)) / 2, 0);
        logoIcon.setLocation(position.x, position.y);
    }

    @Override
    public void onEvent(String message) {
        if (message.equals("newClient")) {
            this.addClient();
            repaint();
        } else if (message.equals("newPlayer")) {
            addPlayer();
        } else if (message.equals("refreshPlayerDisplay")) {
            //playerController = PlayerController.getInstance();
            //addOtherPlayers();
            updateButtonColor();
            repaint();
        }
    }
}

class ClientDisplay {

    String clientName;
    Point position;
    int width = 200;
    int height = 80;
    Random rand = new Random();
    Color clientColor;

    ClientDisplay(String name, Point position, Color color) {
        clientName = name;
        this.position = position;
        clientColor = color;
    }

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(clientColor);
        g2.setFont(new Font("Corbel", Font.PLAIN, 20));
        g2.drawString(clientName, position.x + 50, position.y + height / 2);
        g2.setStroke(new BasicStroke(2.0F));
        g2.drawRoundRect(position.x, position.y, width, height, 5, 5);
    }
}