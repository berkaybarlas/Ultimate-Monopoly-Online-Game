package com.nullPointer.UI;


import javax.swing.*;
import java.awt.*;

public class Navigator {
    private static Navigator _instance;
    private static CardLayout layout;
    private static JPanel panels;

    private Navigator() {
    }

    public static Navigator getInstance() {
        if(_instance == null) {
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
        layout.show(panels,"Menu Panel");
    }

    public void serverScreen() {
        layout.show(panels,"Server Panel");
    }

    public void gameScreen() {
        layout.show(panels,"Game Panel");

    }
}
