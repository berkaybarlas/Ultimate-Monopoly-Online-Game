package com.nullPointer.Controller;

public class MoneyController {
    private static MoneyController _instance;

    private MoneyController(){

    }

    public static MoneyController getInstance() {
        if(_instance == null) {
            _instance = new MoneyController();
        }
        return _instance;
    }
}
