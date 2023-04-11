package com.hippp.harrypotter.game.actions;

import com.hippp.harrypotter.game.character.Wizard;
import com.hippp.harrypotter.game.potion.Potion;
import com.hippp.harrypotter.game.spell.normal.Spell;
import lombok.Getter;

public class ActionTrade extends ActionAbstract {
    // A trade is available after a dialog
    // It can take potions as inputs

    @Getter
    private Potion[] givenPotions;
    @Getter
    private Spell[] givenSpells;
    @Getter
    private Object[] receivedObject;
    @Getter
    private ActionTalk dialog;

    public ActionTrade(ActionTalk dialog, Potion[] givenPotions, Spell[] givenSpells, Object[] receivedObject) {
        super("Trade", ActionTypes.TRADE, 0);
        this.givenPotions = givenPotions;
        this.givenSpells = givenSpells;
        this.receivedObject = receivedObject;
        this.dialog = dialog;
    }


    public void execute(Wizard wizard) {
        // give the potions to the wizard
        if (this.givenPotions != null)
            for (Potion potion : this.givenPotions) {
                wizard.addPotion(potion);
            }
        if (this.givenSpells != null)

            for (Spell spell : this.givenSpells) {
                wizard.addSpell(spell);
            }
        if (this.receivedObject != null)
            for (Object object : this.receivedObject) {
                wizard.takeObject((com.hippp.harrypotter.game.objects.AbstractObject) object);
            }
    }

}
