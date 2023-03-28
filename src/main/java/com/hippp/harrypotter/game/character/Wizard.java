package com.hippp.harrypotter.game.character;

import com.hippp.harrypotter.game.character.house.House;
import com.hippp.harrypotter.game.character.wand.Wand;
import com.hippp.harrypotter.game.objects.AbstractObject;
import com.hippp.harrypotter.game.potion.Potion;
import com.hippp.harrypotter.game.spell.normal.Spell;
import lombok.Getter;
import lombok.Setter;

@Getter()
public class Wizard extends Character {
    public Wizard(String name) {
        super(name, 100, 10);
        this.name = name;
    }

    private String name;
    @Setter()
    private House house;
    private Pet pet;
    @Getter()
    @Setter()
    private Wand wand;

    @Getter
    @Setter
    private int year;
    private Spell[] knownSpells;

    @Getter
    private AbstractObject heldObject;
    private Potion[] potions;

    public void defend() {
    }

    public boolean takeObject(AbstractObject object) {
        if (object.isHoldable() && this.heldObject == null) {
            this.heldObject = object;
            return true;
        }
        return false;
    }

    public AbstractObject throwObject(AbstractObject object) {
        if (object.isThrowable() && this.heldObject != null) {
            AbstractObject thrownObject = this.heldObject;
            this.heldObject = null;
            return thrownObject;
        }
        return null;
    }
}
