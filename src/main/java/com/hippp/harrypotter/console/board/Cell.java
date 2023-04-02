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

    public boolean isCharacter() {
        return this.character != null;
    }

    public void setInCase(AbstractObject object) {
        this.object = object;
    }

    public void setInCase(Character character) {
        this.character = character;
        if (this.character != null && this.character instanceof Wizard) {
            this.isVisited = true;
        }
    }

    ActionAbstract[] interactWith(Wizard wizard) {
        if (isEmpty()) {
            return null;
        } else if (this.object != null) {
            AbstractObject object = this.takeObject(wizard.getSpells());
            if (object != null) {
                wizard.takeObject(object);
                return null;
            }
            return null;
        } else if (this.character != null) {
            return this.interactWithCharacter(this.character);
        }
        return null;
    }

    private ActionAbstract[] interactWithCharacter(Character character) {
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


    public AbstractObject takeObject(HashMap<String, Spell> spells) {
        if (this.object == null) {
            return null;
        }

        if (spells.containsKey("Wingardium LeviOsa")) {
            this.object.setPosition(this.position);
            return this.object.takeObject(spells.get("Wingardium LeviOsa"));
        }
        Display.displayMessage("You don't have a spell to take the object" + object);
        return null;
    }


}
