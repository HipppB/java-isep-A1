package com.hippp.harrypotter.game.level;

import com.hippp.harrypotter.game.actions.ActionTalk;
import com.hippp.harrypotter.game.actions.ActionTrade;
import com.hippp.harrypotter.game.character.NPC;
import com.hippp.harrypotter.game.character.Wizard;
import com.hippp.harrypotter.game.character.enemy.Enemy;
import com.hippp.harrypotter.game.objects.AbstractObject;
import com.hippp.harrypotter.game.objects.Rock;
import com.hippp.harrypotter.game.spell.normal.Spell;

public class FirstYear extends AbstractLevel {

    private boolean trollIsDead = false;
    private boolean trollIsFound = false;

    public FirstYear(Wizard wizard) {
        super("Première année", 1, wizard);

    }

    @Override
    public void init() {

        // create NPC
        NPC professorMcGonagall = new NPC("Professeur McGonagall");
        NPC ronWeasley = new NPC("Ron Weasley");
        NPC hermioneGranger = new NPC("Hermione Granger");
        NPC professorFlitwick = new NPC("Professeur Flitwick");

        // create dialogs

        ActionTalk welcomeDialog = new ActionTalk(new String[]{
                "Welcome to Poudlard !",
                "I'm Professeur McGonagall, the headmaster of this school.",
                "I'm sure you'll have a great time here !"
        }, null, false);
        professorMcGonagall.addDialogue(welcomeDialog);

        ActionTalk InfoDialog = new ActionTalk(new String[]{
                "Hey ! I hope you are doing well !",
                "Wow, you got a wand !",
                "I'm sure you'll be able to do great things with it !",
                "I'm Hermione Granger, by the way !",
                "I'm a first year student like you !",
                "Do you want to see the spell I've just learned ?"
        }, null, false);

        Spell wingardiumLeviosa = new Spell("Wingardium LeviOsa");
        ActionTrade giveSpell = new ActionTrade(null, null, new Spell[]{wingardiumLeviosa}, null);
        ActionTalk giveSpellDialog = new ActionTalk(new String[]{
                "I will teach you the spell 'Wingardium LeviOsa' !",
                "It will allow you to move objects with your wand !",
                "But be careful, you can't move everything ! Only some objects !",

        }, new ActionTrade[]{giveSpell}, false);
        hermioneGranger.addDialogue(InfoDialog);
        hermioneGranger.addDialogue(giveSpellDialog);

        ActionTalk infoDialog2 = new ActionTalk(new String[]{
                "Hello ! I'm Professeur Flitwick, the head of the Ravenclaw house.",
                "You should be careful with the troll !",
                "Just thinking about him makes me shiver !"
        }, null, false);

        professorFlitwick.addDialogue(infoDialog2);

        super.setAvailableNPCs(
                new NPC[]{
                        professorMcGonagall,
                        ronWeasley,
                        hermioneGranger,
                        professorFlitwick
                }
        );

        super.setAvailableEnemies(new Enemy[]{
                new Enemy("Troll", 30, 10) // 30 life -> 3 rocks
        });

        super.setAvailableObjects(new Rock[]{
                new Rock(true, 10),
                new Rock(true),
                new Rock(true)
        });
        this.isRunning = true;

    }

    public void checkStatus() {

        if (this.getWizard().getLife() <= 0) {
            this.isRunning = false;
            this.isLost = true;
        }
        if (super.getAvailableEnemies()[0].getLife() <= 0) {
            this.trollIsDead = true;

        } else {
            for (AbstractObject object : super.getAvailableObjects()) {
                object.respawn();
            }
        }
    }


    // Level description :
    // The Philosopher’s Stone
    // Vous devez affronter le méchant Troll qui se trouve dans les toilettes à côté du Donjon.
    // Pour le vaincre, vous devez soulever des objets et les relâcher lorsqu’ils se trouvent au-dessus de sa tête.


}
