package com.nullPointer.UI;


import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Observer;

import javax.swing.*;
import java.awt.*;

public class Navigator implements Observer {
    private static Navigator _instance;
    private static CardLayout layout;
    private static JPanel panels;
    private GameEngine gameEngine = GameEngine.getInstance();

    private Navigator() {
        gameEngine.subscribe(this);
    }

    public static Navigator getInstance() {
        if (_instance == null) {
            _instance = new Navigator();
        }
        return _instance;
    }

    public static CardLayout getLayout() {
        return layout;
    }

    public static void setLayout(CardLayout layout) {
        Navigator.layout = layout;
    }

    public static JPanel getPanels() {
        return panels;
    }

    public static void setPanels(JPanel panels) {
        Navigator.panels = panels;
    }

    public void menuScreen() {
        layout.show(panels, "Menu Panel");
    }

    public void serverScreen() {
        layout.show(panels, "Server Panel");
    }

    public void gameScreen() {
        layout.show(panels, "Game Panel");

    }

    @Override
    public void onEvent(String message) {
        if (message.contains("Screen")) {
            if (message.contains("gameScreen")) {
                gameScreen();
            } else if (message.contains("serverScreen")) {
                serverScreen();
            }

        }
    }
}
