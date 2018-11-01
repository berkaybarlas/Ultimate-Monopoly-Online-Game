package com.nullPointer.Utils;

import java.awt.*;

public enum ColorSet {

    BLACK("Black", new Color(0,0,0)),
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
        return value;
    }

}
