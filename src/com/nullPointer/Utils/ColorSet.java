package com.nullPointer.Utils;

import java.awt.Color;

public class ColorSet {

    public final static Color BLACK = new Color(0,0,0);

    public final static Color BOARDBACKGROUND = new Color(187, 229, 206);

    public ColorSet() {

    }

    public static Color getBLACK() {
        return BLACK;
    }

    public static Color getBackground() {
        return BOARDBACKGROUND;
    }

    /*
    BOARDBACKGROUND("Board", new Color(187, 229, 206)),
    WHITE("White", new Color(256,256,256));

    private final String key;
    private final Color value;

    ColorSet(String key, Color value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Color getValue() {
        return new Color(0,0,0);
    }
        */

}
