package com.nullPointer.Domain.Model;

import com.nullPointer.Domain.Controller.CommunicationController;
import com.nullPointer.Domain.Observer;

import java.util.Timer;
import java.util.TimerTask;

public class BotBehaviour implements Observer {

    private GameEngine gg = GameEngine.getInstance();
    private CommunicationController cc = CommunicationController.getInstance();

    public BotBehaviour()
    {
        gg.subscribe(this);
    }

    // Lazy bot
    @Override
    public void onEvent(String message) {

        System.out.println("Incoming message: " + message);

        if(gg.amIBot() && gg.isMyTurn())
        {
            Timer timer = new Timer();

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Bot is thinking...");
                    if(message.equals("rollDice"))
                    {
                        if(gg.getRoll3())  gg.roll3Dice();
                        else gg.rollDice();
                        System.out.println("The Bot has decided to Roll the Dice!");
                        cc.sendClientMessage("dice/" + gg.getLastDiceValues());
                    }
                    else if (message.equals("drawCard"))
                    {
                        System.out.println("The Bot has decided to Draw a Card!");
                        cc.sendClientMessage("card/draw");

                    }
//            else if(message.equals("game/start") || message.contains("create") ||message.contains("client") || message.contains("message") || message.contains("newPlayer") || message.contains())
//            {
//
//            }
                    else if (message.contains("buy") || message.contains("paid rent"))
                    {
                        System.out.println("The Bot has decided to Yield its Turn!");
                        cc.sendClientMessage("player/next");
                    }
                }
            }, 3*1000);

        }

    }
}

