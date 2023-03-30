package com.hippp.harrypotter.game.character;

import com.hippp.harrypotter.game.actions.ActionAbstract;
import com.hippp.harrypotter.game.actions.ActionTalk;
import com.hippp.harrypotter.game.actions.ActionTrade;
import lombok.Getter;

public class NPC extends Character {

    private ActionAbstract[] actions;

    @Getter
    private boolean canTalk;

    private ActionTalk actionTalk;

    @Getter
    private int dialogueIndex;

    private int maxDialogueIndex;

    public NPC(String name, ActionTalk actionTalk) {
        super(name, 10000, 0);
        this.canTalk = true;
        this.dialogueIndex = 0;
        this.actionTalk = actionTalk;
    }

    public NPC(String name) {
        super(name, 10000, 0);
        this.canTalk = true;
        this.dialogueIndex = 0;
    }

    public String[] talk() {
        if (canTalk && this.actionTalk != null) {
            String[] dialogue = this.actionTalk.getDialog();
            if (dialogue != null) {
                this.dialogueIndex++;
            } else {
                this.dialogueIndex = 0;
            }
            if (this.maxDialogueIndex < this.dialogueIndex) {
                this.maxDialogueIndex = this.dialogueIndex;
            }
            return dialogue;
        } else {
            return new String[]{
                    "Hello, I'm " + this.name,
                    "I can't talk right now, come back later."
            };
        }
    }

    public ActionTrade[] getTrades() {
        if (this.actionTalk != null) {
            return this.actionTalk.getActionTrades(this.maxDialogueIndex - 1);
        }
        return null;
    }

    public String[] trade(ActionTrade trade, Wizard wizard) {
        ActionTrade[] trades = this.getTrades();
        if (trades != null) {
            for (ActionTrade t : trades) {
                if (t == trade) {
                    t.execute(wizard);
                }
            }
        }
        return null;
    }

    public void addDialogue(ActionTalk dialogue) {
        if (this.actionTalk == null) {
            this.actionTalk = dialogue;
        } else {
            this.actionTalk.addDialog(dialogue);
        }
    }
}
