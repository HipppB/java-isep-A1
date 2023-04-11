package com.hippp.harrypotter.console.board;

import com.hippp.harrypotter.console.Display;
import com.hippp.harrypotter.game.actions.ActionAbstract;
import com.hippp.harrypotter.game.character.Character;
import com.hippp.harrypotter.game.character.NPC;
import com.hippp.harrypotter.game.character.Wizard;
import com.hippp.harrypotter.game.character.enemy.Enemy;
import com.hippp.harrypotter.game.objects.AbstractObject;
import com.hippp.harrypotter.game.spell.normal.Spell;
import lombok.Getter;

import java.util.HashMap;

public class Cell {

    @Getter
    private AbstractObject object;
    @Getter
    private Character character;

    @Getter
    private boolean isVisited;

    @Getter
    private final int[] position;

    public Cell(int[] position) {
        this.object = null;
        this.character = null;
        this.isVisited = false;
        this.position = position;
    }

    public boolean isEmpty() {
        return this.object == null && this.character == null;
    }

    public boolean isObject() {
        return this.object != null;
    }

    public boolean isObject(String name) {
        return this.object != null && this.object.getName().equals(name);
    }

    public boolean isCharacter() {
        return this.character != null;
    }

    public void setInCase(AbstractObject object) {
        this.object = object;
        if (this.object != null) {
            this.object.setPosition(this.position);

        }
    }

    public void setInCase(Character character) {
        this.character = character;
        if (this.character != null && this.character instanceof Wizard) {
            this.isVisited = true;
        }
    }

    ActionAbstract[] interactWith(Wizard wizard) {
        System.out.println("INTERACTING");
        if (isEmpty()) {
            return null;
        } else if (this.object != null) {
            AbstractObject object = this.takeObject(wizard);
            System.out.println("INTERACTING 2" + object.getName());
            if (object != null) {
                wizard.takeObject(object);
                return null;
            }
            return null;
        } else if (this.character != null) {
            return this.interactWithCharacter(this.character, wizard);
        }
        return null;
    }

    private ActionAbstract[] interactWithCharacter(Character character, Wizard wizard) {
        if (character instanceof NPC) {
            String[] dialog = ((NPC) this.character).talk();
            if (dialog == null) return null;
            Display.dialog(dialog);
            return ((NPC) this.character).getTrades();
        } else if (character instanceof Enemy) {
            return new ActionAbstract[]{((Enemy) character).getAttackAction()};
        }
        return null;
    }


    public AbstractObject takeObject(Wizard wizard) {
        if (this.object == null) {
            return null;
        }


        HashMap<String, Spell> spells = wizard.getSpells();
        System.out.println(spells);
        if (spells.containsKey("Wingardium LeviOsa")) {
            System.out.println("Using Wingardium LeviOsa");
            System.out.println(this.position[0] + " " + this.position[1]);
            this.object.setPosition(this.position);
            System.out.println(this.object.getPosition()[0] + " " + this.object.getPosition()[1]);
            return this.object.takeObject(wizard);
        }
        Display.displayMessage("You don't have a spell to take the object" + object);
        return null;
    }


}
