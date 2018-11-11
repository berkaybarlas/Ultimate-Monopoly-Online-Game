package com.nullPointer.Model;

import java.util.Random;

public class SpeedDie extends Die {
    private static SpeedDie _instance;
    Random randomGen = new Random();
    private SpeedDie(){

    }

    public static SpeedDie getInstance() {
        if(_instance == null) {
            _instance = new SpeedDie();
        }
        return _instance;
    }
}
