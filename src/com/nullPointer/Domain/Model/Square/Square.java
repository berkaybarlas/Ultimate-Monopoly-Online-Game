package com.nullPointer.Domain.Model.Square;

import com.nullPointer.Domain.Model.GameEngine;

import java.io.Serializable;

public abstract class Square implements Serializable {
    private String name;
    private String type;
    private boolean flyover;

    public Square(String name, String type) {
        this.name = name;
        this.type = type;
        setFlyover(false);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean getFlyover()
    {
        return flyover;
    }

    public void setFlyover(boolean b)
    {
        this.flyover = b;
    }

    public abstract void evaluateSquare(GameEngine gameEngine);

    public void evaluateSquare(GameEngine gg, String args)
    {
        evaluateSquare(gg);
    }
}
