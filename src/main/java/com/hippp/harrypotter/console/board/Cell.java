package com.hippp.harrypotter.console.board;

import com.hippp.harrypotter.console.Display;
import com.hippp.harrypotter.game.actions.ActionAbstract;
import com.hippp.harrypotter.game.actions.ActionTrade;
import com.hippp.harrypotter.game.character.Character;
import com.hippp.harrypotter.game.character.NPC;
import com.hippp.harrypotter.game.character.Wizard;
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
    private int[] position = new int[2];

    public Cell(int[] position) {
        this.object = null;
        this.character = null;
        this.isVisited = false;
        this.position = position;
    }

    public boolean isEmpty() {
        return this.object == null && this.character == null;
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
        System.out.println("Interact with " + this.character + this.object);
        if (isEmpty()) {
            return null;
        } else if (this.object != null) {
            System.out.println("You try to take the object" + object);
            AbstractObject object = this.takeObject(wizard.getSpells());


            if (object != null) {
                wizard.takeObject(object);

                System.out.println("You take the object" + object);
                return null;
            }


            return null;
        } else if (this.character != null && this.character instanceof NPC) {
            String[] dialog = ((NPC) this.character).talk();
            if (dialog != null) {
                Display.dialog(dialog);
                ActionTrade[] actionTrades = ((NPC) this.character).getTrades();
                if (actionTrades != null) {
                    return actionTrades;
                }
                return null;
            }
        }
        return null;
    }


    public AbstractObject takeObject(HashMap<String, Spell> spells) {
        System.out.println("You try the object" + object);
        if (this.object == null) {
            return null;
        }

        if (spells.containsKey("Wingardium Leviosa")) {
            Display.dialog(new String[]{"You try to use Wingardium Leviosa to make the object levitate"});
            this.object.setPosition(this.position);
            return this.object.takeObject(spells.get("Wingardium Leviosa"));
        }
        System.out.println("You don't have a spell to take the object" + object);
        return null;
    }


}
