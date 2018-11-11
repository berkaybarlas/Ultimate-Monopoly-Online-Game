package com.nullPointer.Controller;

public class PlayerController {
    private static PlayerController _instance;

    private PlayerController(){

    }

    public static PlayerController getInstance() {
        if(_instance == null) {
            _instance = new PlayerController();
        }
        return _instance;
    }

}
