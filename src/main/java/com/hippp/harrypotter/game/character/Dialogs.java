package com.hippp.harrypotter.game.character;

import com.hippp.harrypotter.game.actions.ActionTrade;
import lombok.Getter;

public class Dialogs {

    private String[] dialog;

    private Dialogs previousDialog;
    private Dialogs nextDialog;

    @Getter
    private ActionTrade[] actionTrades;

    private boolean isRead;
    private boolean isLocked;

    public Dialogs(String[] dialog, ActionTrade[] actionTrades, Boolean locked) {
        this.dialog = dialog;
        this.isRead = false;
        this.isLocked = locked;
        this.actionTrades = actionTrades;


    }

    public void addDialog(Dialogs dialogs) {
        if (this.nextDialog == null) {
            dialogs.previousDialog = this;
            this.nextDialog = dialogs;
        } else {
            this.nextDialog.addDialog(dialogs);
        }
    }

    public String[] getDialog() {
        if (!this.isLocked) {
            if (isRead && nextDialog != null)
                return nextDialog.getDialog();
            isRead = true;
            return dialog;
        }
        return new String[]{"Hello !"};
    }

    void unlockNextDialog() {
    }

    void unReadAllPrevious() {
        this.isRead = false;
        if (this.previousDialog != null) {
            this.previousDialog.unReadAllPrevious();
        }
    }


}
