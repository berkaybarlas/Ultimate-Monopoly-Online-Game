package com.nullPointer.Utils;

import java.awt.Color;

public class ColorSet {

    public final static Color BLACK = new Color(0, 0, 0);

    public final static Color BOARDBACKGROUND = new Color(187, 229, 206);

    public final static Color ButtonPrimary = new Color(206, 14, 16);

    public final static Color ButtonPressed = new Color(255, 142, 139);

    private static ColorSet _instance;

    public ColorSet() {

    }

    public static ColorSet getInstance() {
        if (_instance == null) {
            _instance = new ColorSet();
        }
        return _instance;
    }


}
