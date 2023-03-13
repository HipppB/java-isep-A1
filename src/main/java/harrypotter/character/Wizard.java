package harrypotter.character;

import harrypotter.character.wand.Wand;
import harrypotter.character.house.House;
import harrypotter.potion.Potion;
import harrypotter.spell.normal.Spell;

public class Wizard extends Character {
    private Pet pet;
    private Wand wand;
    private House house;
    private Spell[] knownSpells;
    private Potion[] potions;

    public void defend() {}
}
