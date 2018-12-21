package com.nullPointer.Utils;

import java.awt.Color;
import java.util.ArrayList;

public class ColorSet {

    public static ArrayList<Color> playerColors = new ArrayList<>();

    public final static Color BLACK = new Color(0,0,0);

    public final static Color WHITE = new Color(255, 255, 255);

    public final static Color BOARDBACKGROUND = new Color(187, 229, 206);

    public final static Color computer1Color = new Color(255, 0, 0);

    public final static Color computer2Color = new Color(255, 127, 0);

    public final static Color computer3Color = new Color(255, 255, 0);

    public final static Color computer4Color = new Color(127, 255, 0);

    public final static Color computer5Color = new Color(0, 255, 0);

    public final static Color computer6Color = new Color(0, 255, 127);

    public final static Color computer7Color = new Color(0, 255, 255);

    public final static Color computer8Color = new Color(0, 127, 255);

    public final static Color computer9Color = new Color(0, 0, 255);

    public final static Color computer10Color = new Color(127, 0, 255);

    public final static Color computer11Color = new Color(255, 0, 255);

    public final static Color computer12Color = new Color(255, 0, 127);

    public final static Color computerDefault = new Color(90, 96, 97);

    public final static Color SERVERBACKGROUND_LIGHT = new Color(230,241,227);

    public final static Color SERVERBACKGROUND_DARK = new Color(136, 201, 161);

    public ColorSet() {
        
    }
    public final static Color ButtonPressed = new Color(255, 142, 139);

    public final static Color ButtonPrimary = new Color(206, 14, 16);

    public static ArrayList<Color> getPlayerColors() {
        playerColors.add(computer1Color);
        playerColors.add(computer2Color);
        playerColors.add(computer3Color);
        playerColors.add(computer4Color);
        playerColors.add(computer5Color);
        playerColors.add(computer6Color);
        playerColors.add(computer7Color);
        playerColors.add(computer8Color);
        playerColors.add(computer9Color);
        playerColors.add(computer10Color);
        playerColors.add(computer11Color);
        playerColors.add(computer12Color);
        playerColors.add(computerDefault);
        return playerColors;
    }

}
