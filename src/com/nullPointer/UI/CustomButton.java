package com.nullPointer.UI;

import com.nullPointer.Utils.ColorSet;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class CustomButton extends JButton {



    private Color primaryColor = ColorSet.ButtonPrimary;

    private Color pressedColor = ColorSet.ButtonPressed;

    public CustomButton(String text) {
        super(text);
        this.setToolTipText("Join the game server");
        this.setBackground(primaryColor);
        //super.setBounds(this.getWidth() / 2 - 100, this.getHeight() / 2 - 100, 100, 400);
        this.setForeground(Color.WHITE);
        this.setFont(new Font("Tahoma", Font.BOLD, 20));
        this.setOpaque(true);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        JButton button = this;
        this.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) e.getSource();

                if (model.isRollover()) {
                    button.setBackground(primaryColor.brighter().brighter());
                } else if (model.isPressed()) {
                    button.setBackground(pressedColor);
                } else {
                    button.setBackground(primaryColor);
                }
            }
        });
    }

    public void setPrimaryColor(Color primaryColor) {
        this.setBackground(primaryColor);
        this.primaryColor = primaryColor;
        this.pressedColor = primaryColor.brighter().brighter();
    }
}
