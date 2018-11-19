package com.nullPointer.Domain.Model;

public class RegularDie extends  Die{
    private static RegularDie _instance;
    
    private RegularDie(){

    }

    public static RegularDie getInstance() {
        if(_instance == null) {
            _instance = new RegularDie();
        }
        return _instance;
    }
    public void roll(){
    	roll(2);
    }
}
