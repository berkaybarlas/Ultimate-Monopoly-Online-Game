package com.nullPointer.Domain.Model.Square;

import com.nullPointer.Domain.Model.GameEngine;

import java.io.Serializable;

public abstract class Square implements Serializable {
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
