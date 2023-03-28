package com.hippp.harrypotter.game.actions;

import com.hippp.harrypotter.game.character.Dialogs;
import com.hippp.harrypotter.game.potion.Potion;
import com.hippp.harrypotter.game.spell.normal.Spell;
import lombok.Getter;

public class ActionTrade extends ActionAbstract {
    // A trade is available after a dialog
    // It can take potions as inputs

    private Potion[] givenPotions;
    private Spell[] givenSpells;
    private Object[] receivedObject;
    @Getter
    private Dialogs dialog;

    public ActionTrade(Dialogs dialog, Potion[] givenPotions, Spell[] givenSpells, Object[] receivedObject) {
        super("Trade", ActionTypes.TRADE, 0);
        this.givenPotions = givenPotions;
        this.givenSpells = givenSpells;
        this.receivedObject = receivedObject;
        this.dialog = dialog;
    }

    public Potion[] getGivenPotions() {
        return givenPotions;
    }

}
