package com.nullPointer;

import com.nullPointer.Model.GameEngine;
import com.nullPointer.UI.AppWindow;

public class Main {

    public static void main(String[] args) {
    	GameEngine g=new GameEngine();
        AppWindow frame = new AppWindow(g);       
        frame.pack();
        frame.setVisible(true);
        
        new Thread(g).start();
    }
}
 