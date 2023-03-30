package com.hippp.harrypotter.game.actions;

public class ActionTalk extends ActionAbstract {

    private String[] dialog;

    private ActionTalk previousDialog;
    private ActionTalk nextDialog;

    private ActionTrade[] actionTrades;

    private boolean isRead;
    private boolean isLocked;

    public ActionTalk(String[] dialog, ActionTrade[] actionTrades, Boolean locked) {
        super("Talk", ActionTypes.TALK, 0);
        this.dialog = dialog;
        this.isRead = false;
        this.isLocked = locked;
        this.actionTrades = actionTrades;


    }

    public void addDialog(ActionTalk actionTalk) {
        if (this.nextDialog == null) {
            actionTalk.previousDialog = this;
            this.nextDialog = actionTalk;
        } else {
            this.nextDialog.addDialog(actionTalk);
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

    public ActionTrade[] getActionTrades(int index) {
        if (index == 0) {
            return this.actionTrades;
        } else if (this.nextDialog != null) {
            return this.nextDialog.getActionTrades(index - 1);
        }
        return null;
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
