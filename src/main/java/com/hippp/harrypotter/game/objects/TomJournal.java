package com.hippp.harrypotter.game.objects;

import com.hippp.harrypotter.game.character.Wizard;

public class TomJournal extends AbstractObject {


    public TomJournal() {
        super("Tom Jedusor's Journal", "A journal of Tom Jedusor", false, false, false, false, false, false, 0, 0);
        this.isDestroyed = false;
    }

    public AbstractObject takeObject(Wizard wizard) {
        if (wizard.getObjects().containsKey("Basilic Fang")) {
            this.isDestroyed = true;
        } else {
            wizard.attackDamage(10);
        }
        return null;
    }

}
