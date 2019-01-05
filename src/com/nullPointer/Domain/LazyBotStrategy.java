package com.nullPointer.Domain;

public class LazyBotStrategy extends BasicBotBehaviors implements BotBehaviourStrategy {
    @Override
    public void buyAction() {
        communicationController.sendClientMessage("purchase");
    }

    @Override
    public void improveAction() {

    }
}
