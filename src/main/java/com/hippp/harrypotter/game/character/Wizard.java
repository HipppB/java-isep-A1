package com.hippp.harrypotter.game.character;

import com.hippp.harrypotter.game.character.house.House;
import com.hippp.harrypotter.game.character.wand.Wand;
import com.hippp.harrypotter.game.objects.AbstractObject;
import com.hippp.harrypotter.game.potion.Potion;
import com.hippp.harrypotter.game.spell.normal.Spell;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

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
    @Getter
    private HashMap<String, Spell> spells;

    @Getter
    private AbstractObject heldObject;
    @Getter
    private HashMap<Potion, Integer> potions;

    @Getter
    private HashMap<String, AbstractObject> objects; // TODO : Create inventory class

    public void defend() {
    }

    public boolean takeObject(AbstractObject object) {
        if (object.isHoldable() && this.heldObject == null) {

            this.heldObject = object;
            return true;
        }
        return false;
    }

    public void dropObject() {
        this.heldObject = null;
    }

    public void dropObjectError() {
        this.dropObject();
        super.attackDamage(10);
    }

    public void addPotion(Potion potion) {
        if (this.potions.containsKey(potion)) {
            this.potions.put(potion, this.potions.get(potion) + 1);
        } else {
            this.potions.put(potion, 1);
        }
    }

    public void addSpell(Spell spell) {
        System.out.println("Adding spell " + spell.getName());
        if (this.spells == null) {
            HashMap<String, Spell> spells = new HashMap<>();
            spells.put(spell.getName(), spell);
            this.spells = spells;
        } else {
            this.spells.put(spell.getName(), spell);
        }

    }


}
