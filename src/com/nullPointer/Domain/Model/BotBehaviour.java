package com.nullPointer.Domain.Model;

import com.nullPointer.Domain.Controller.CommunicationController;
import com.nullPointer.Domain.Observer;

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
        System.out.println("Bot is thinking...");

        if(gg.amIBot() && gg.isMyTurn())
        {
            if(message.equals("rollDice"))
            {
                if(gg.getRoll3())  gg.roll3Dice();
                else gg.rollDice();
                cc.sendClientMessage("dice/" + gg.getLastDiceValues());
            }
            else if (message.equals("drawCard"))
            {
                cc.sendClientMessage("card/draw");
            }
            else
            {
                cc.sendClientMessage("next");
            }
        }

    }
}

