package com.hippp.harrypotter.game.actions;

import com.hippp.harrypotter.game.character.Character;

public class ActionTalkTo extends ActionAbstract {

    private Character character;

    // This action is created when the player talks to a character
    public ActionTalkTo(String name, Character character, int stepId) {
        super(name, ActionTypes.TALK, stepId);
    }

}
