package com.nullPointer.UI;

import java.awt.*;

public class Pawn {
    private Point position;
    private int positionIndex;

    public Pawn(Point point) {
        this.position = point;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void changeX(int x) {
        position.x = position.x + x;
    }

    public void changeY(int y) {
        position.y = position.y + y;
    }

    public int getPositionIndex() {
        return positionIndex;
    }

    public void setPositionIndex(int positionIndex) {
        this.positionIndex = positionIndex;
    }
}
