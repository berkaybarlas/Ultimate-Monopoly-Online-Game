package com.nullPointer.Domain.Model;

public class SpeedDie extends Die {
    private static SpeedDie _instance;

    private SpeedDie() {

    }

    public static SpeedDie getInstance() {
        if(_instance == null) {
            _instance = new SpeedDie();
        }
        return _instance;
    }
    public void roll(){
    	roll(1);
    }
}
