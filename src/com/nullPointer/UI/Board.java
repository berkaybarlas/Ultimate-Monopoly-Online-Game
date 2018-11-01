package com.nullPointer.UI;

import com.nullPointer.Utils.ColorSet;

import java.awt.*;

public class Board {
    private Color color = new Color(187, 229, 206);
    private Point position = new Point(550,50);
    private int length = 700;
    public Board(Point position, int length) {
        this.position = position;
        this.length = length;
    }

    public void paint(Graphics g) {
        g.setColor(color);
        g.fillRect(position.x, position.y, length, length);
    }
}
