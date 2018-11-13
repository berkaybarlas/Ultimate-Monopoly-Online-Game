package com.nullPointer.Model;

import com.nullPointer.Controller.CommunicationController;
import com.nullPointer.Controller.MoneyController;
import com.nullPointer.Controller.PlayerController;
import com.nullPointer.UI.Board;
import com.nullPointer.UI.Navigator;

public class GameEngine {
    private RegularDie regularDie = RegularDie.getInstance();
    private SpeedDie speedDie = SpeedDie.getInstance();
    private PlayerController playerController = PlayerController.getInstance();
    private MoneyController moneyController = MoneyController.getInstance();
    private Navigator navigator = Navigator.getInstance();

    private static GameEngine _instance;

    private GameEngine() {

    }

    public static GameEngine getInstance() {
        if(_instance == null) {
            _instance = new GameEngine();
        }
        return _instance;
    }


    public void initPlayers() {

    }

    public void startGame() {
        navigator.gameScreen();
    }

    public void rollDice() {

    }

    public void playCard() {

    }

    public void movePlayer() {

    }

    public void drawCard() {

    }

    public void improveProperty() {

    }

    public void buyProperty() {

    }

    public void nextTurn() {

    }

    public void sendToJail() {

    }
}
