package com.hippp.harrypotter.game.actions;

import com.hippp.harrypotter.game.Game;

public class ActionDrop extends ActionAbstract {

    public ActionDrop() {
        super("Drop", ActionTypes.DROP, 0);
    }

    public void execute(Game game, int[] position) {
        game.getWizard().dropObject();

    }
}
