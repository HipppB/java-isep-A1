package com.hippp.harrypotter.game.objects;

import com.hippp.harrypotter.game.character.Wizard;
import com.hippp.harrypotter.game.spell.normal.Spell;

import java.util.HashMap;

public class Rock extends AbstractObject {
    public Rock(boolean isHoldableAndThrowable, int timeBeforeRespawn) {
        super("rock", "I am a rock", false, false, false, isHoldableAndThrowable, false, isHoldableAndThrowable, 10, timeBeforeRespawn);
    }

    public Rock(boolean isHoldableAndThrowable) {
        super("rock", "I am a rock", false, false, false, isHoldableAndThrowable, false, isHoldableAndThrowable, 10, 0);
    }

    public Rock() {
        super("rock", "I am a rock", false, false, false, false, false, false, 10, 0);
    }

    @Override
    public AbstractObject interact() {
        if (this.isHoldable()) {
            this.isHeld = true;
            return this;
        }
        return null;
    }

    @Override
    public AbstractObject takeObject(Wizard wizard) {
        HashMap<String, Spell> spells = wizard.getSpells();
        if (this.isHoldable() && spells != null && spells.get("Wingardium LeviOsa") != null) {
            return this;
        }
        return null;
    }


}
