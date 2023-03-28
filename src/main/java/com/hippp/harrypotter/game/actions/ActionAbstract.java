package com.hippp.harrypotter.game.actions;

public class ActionAbstract {
    // An action is an event that can be triggered by the player
    // It can be a fight, a conversation, a choice, etc.

    private String name;
    private int stepId;
    private ActionTypes type;

    public ActionAbstract(String name, ActionTypes type, int stepId) {
        this.name = name;
        this.stepId = stepId;
        this.type = type;
    }


}
