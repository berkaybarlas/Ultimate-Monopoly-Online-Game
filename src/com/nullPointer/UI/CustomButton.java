package com.nullPointer.UI;

import com.nullPointer.Utils.ColorSet;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class CustomButton extends JButton {

    private ColorSet colorSet = ColorSet.getInstance();

    private Color primaryColor = colorSet.ButtonPrimary;

    private Color pressedColor = colorSet.ButtonPressed;

    public CustomButton(String text) {
        super(text);
        this.setToolTipText("Join the game server");
        this.setBackground(colorSet.ButtonPrimary);
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

}
