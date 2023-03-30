package com.hippp.harrypotter.console.board;

import com.hippp.harrypotter.console.Display;
import com.hippp.harrypotter.game.actions.ActionAbstract;
import com.hippp.harrypotter.game.actions.ActionTrade;
import com.hippp.harrypotter.game.character.Character;
import com.hippp.harrypotter.game.character.NPC;
import com.hippp.harrypotter.game.character.Wizard;
import com.hippp.harrypotter.game.objects.AbstractObject;
import lombok.Getter;

public class Cell {

    @Getter
    private AbstractObject object;
    @Getter
    private Character character;

    @Getter
    private boolean isVisited;

    public Cell() {
        this.object = null;
        this.character = null;
        this.isVisited = false;
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

    ActionAbstract[] interactWith() {
        if (isEmpty()) {
            return null;
        } else if (this.object != null) {
            AbstractObject object = this.object.interact();
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


    public AbstractObject takeObject() {
        if (this.object == null) {
            return null;
        }
        AbstractObject object = this.object.takeObject();
        if (object != null) {
            this.object = null;
        }
        return object;
    }


}
