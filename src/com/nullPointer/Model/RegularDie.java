package com.nullPointer.Model;


import java.util.Random;

public class RegularDie extends  Die{
    private static RegularDie _instance;
    Random randomGen = new Random();

    private RegularDie(){

    }

    public static RegularDie getInstance() {
        if(_instance == null) {
            _instance = new RegularDie();
        }
        return _instance;
    }
}
