package com.nullPointer.Domain.Model.Square;

import com.nullPointer.Domain.Model.GameEngine;

public abstract class Square {
    private String name;
    private String type;

    public Square(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public abstract void evaluateSquare(GameEngine gameEngine);
}
