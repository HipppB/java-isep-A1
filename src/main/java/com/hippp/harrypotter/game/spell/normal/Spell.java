package com.hippp.harrypotter.game.spell.normal;

import com.hippp.harrypotter.game.objects.AbstractObject;
import com.hippp.harrypotter.game.spell.AbstractSpell;

public class Spell extends AbstractSpell {

    public Spell(String name) {
        super(name);
    }

    public boolean canMoveObject(AbstractObject object) {
        if (object.getSize() > 1 || !object.isHoldable()) {
            return false;
        }
        return true;
    }
}
