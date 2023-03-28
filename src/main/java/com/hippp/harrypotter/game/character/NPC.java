package com.hippp.harrypotter.game.character;

import com.hippp.harrypotter.game.actions.ActionTrade;
import lombok.Getter;

public class NPC extends Character {

    @Getter
    private boolean canTalk;

    private Dialogs dialogs;

    @Getter
    private int dialogueIndex;

    public NPC(String name, Dialogs dialogs) {
        super(name, 10000, 0);
        this.canTalk = true;
        this.dialogueIndex = 0;
        this.dialogs = dialogs;
    }

    public NPC(String name) {
        super(name, 10000, 0);
        this.canTalk = true;
        this.dialogueIndex = 0;
    }

    public String[] talk() {
        if (canTalk && this.dialogs != null) {
            String[] dialogue = this.dialogs.getDialog();
            return dialogue;
        } else {
            return new String[]{
                    "Hello, I'm " + this.name,
                    "I can't talk right now, come back later."
            };
        }
    }

    public ActionTrade[] getTrades() {
        if (this.dialogs != null) {
            return this.dialogs.getActionTrades();
        }
        return null;
    }

    public void addDialogue(Dialogs dialogue) {
        if (this.dialogs == null) {
            this.dialogs = dialogue;
        } else {
            this.dialogs.addDialog(dialogue);
        }
    }
}
