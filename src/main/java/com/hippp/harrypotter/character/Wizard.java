package com.hippp.harrypotter.character;

import com.hippp.harrypotter.character.house.House;
import com.hippp.harrypotter.character.wand.Wand;
import com.hippp.harrypotter.potion.Potion;
import com.hippp.harrypotter.spell.normal.Spell;

public class Wizard extends Character {
    private Pet pet;
    private Wand wand;
    private House house;
    private Spell[] knownSpells;
    private Potion[] potions;

    public void defend() {}
}
