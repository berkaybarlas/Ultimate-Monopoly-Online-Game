package com.nullPointer;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.nullPointer.UI.AppWindow;
import com.nullPointer.UI.MessageBox;

public class Main {

    public static void main(String[] args) {
        AppWindow frame = new AppWindow();
    	       
        frame.pack();
        frame.setVisible(true);
    }
}
