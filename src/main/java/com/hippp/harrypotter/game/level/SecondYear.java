package com.hippp.harrypotter.game.level;

import com.hippp.harrypotter.game.actions.ActionTalk;
import com.hippp.harrypotter.game.character.NPC;
import com.hippp.harrypotter.game.character.Wizard;
import com.hippp.harrypotter.game.character.enemy.Enemy;
import com.hippp.harrypotter.game.objects.AbstractObject;
import com.hippp.harrypotter.game.objects.GodicSword;
import com.hippp.harrypotter.game.objects.Rock;
import com.hippp.harrypotter.game.objects.TomJournal;

import java.util.List;

public class SecondYear extends AbstractLevel {

    private boolean objectifSword = false;
    private boolean objectifRocks = false;
    private boolean basilicIsDead = false;
    private AbstractObject requiredObject;


    public SecondYear(Wizard wizard) {
        super("Second Year", 2, wizard);
    }

    @Override
    public void init() {
        Enemy enemy = new Enemy("Basilic", 100, 10);


        this.setAvailableEnemies(List.of(new Enemy[]{enemy}));
        for (int i = 0; i < 8; i++) {
            this.addAvailableObject(new Rock(true));
        }
        if (super.getWizard().getHouse().getName().equals("Gryffindor")) {
            requiredObject = new GodicSword();
            enemy.addObjectNeededToKill(requiredObject);
            initIfGryffindor();
        } else {
            requiredObject = new TomJournal();
            initIfNotGryffindor();
        }

        this.isRunning = true;

    }

    public void initIfGryffindor() {
        objectifSword = true;
        // needs to get the Godic Sword
        ActionTalk getInstructionsFromHagrid = new ActionTalk(
                new String[]{"Hi, I am Hagrid, I am the gamekeeper and groundskeeper for Hogwarts.",
                        "You need to get the Godic Sword to kill the Basilic",
                        "You can summon it by placing 8 rocks in a square"
                }, null, false);
        NPC npc = new NPC("Hagrid", getInstructionsFromHagrid);

        ActionTalk getWarningFromHagrid = new ActionTalk(
                new String[]{"Be careful, the Basilic is very dangerous, you need to be very careful",
                        "Do not approach it unless you successfully summoned the Godic Sword"
                }, null, false);
        npc.addDialogue(getWarningFromHagrid);

        this.setAvailableNPCs(List.of(new NPC[]{npc}));


    }

    public void initIfNotGryffindor() {

        ActionTalk getInstructionsFromHagrid = new ActionTalk(
                new String[]{"Hi, I am Hagrid, I am the gamekeeper and groundskeeper for Hogwarts.",
                        "You are not a Gryffindor, you won't be able to kill the Basilic without the Godic Sword",
                        "But you won't be able to get the Sword due to your house",
                        "You can still try to kill the Basilic, but you will need to be very careful, ask me for advice if you need it"
                }, null, false);
        NPC hagrid = new NPC("Hagrid", getInstructionsFromHagrid);

        ActionTalk getHintsFromHagrid = new ActionTalk(
                new String[]{"You are not a Gryfondor, their is no way for you to directly kill the Basilic" +
                        "But you can kill him indirectly..." +
                        "By destroying the journal of Tom Jedusor, the Basilic's master" +
                        "But be careful, the journal is protected by a spell" +
                        "If you try to destroy it without the Basilic's fang, you will die" +
                        "But if you manage to get the Basilic's fang, you will be able to destroy the journal and kill the Basilic"
                }, null, false);

        hagrid.addDialogue(getHintsFromHagrid);
        ActionTalk howToFromHagrid = new ActionTalk(
                new String[]{
                        "How to get the Basilic's fang?",
                        "You need to take it from the Basilic's mouth",
                        "But be careful, if it sees you, you will be killed.",
                        "The only way I think you could do it is to come right in front of it with a rock and throw the rock on it",
                        "As it doesn't see directly in front you should be able to succeed ! Be aware he likes looking to the north."
                }, null, false);

        hagrid.addDialogue(howToFromHagrid);

        this.setAvailableNPCs(List.of(new NPC[]{hagrid}));

    }


    public void summonObject() {
        if (this.objectifRocks) return;
        this.addAvailableObject(requiredObject);
        this.objectifRocks = true;

    }

    @Override
    public void checkStatus() {
        if (this.getWizard().getLife() <= 0) {
            this.isRunning = false;
            this.isLost = true;
        }
        if ((this.objectifSword && this.getAvailableEnemies().get(0).getLife() <= 0)
                || this.requiredObject.isDestroyed()) {
            this.isRunning = false;
            this.isWon = true;
            this.basilicIsDead = true;
        }


    }


}
