package com.hippp.harrypotter.console;

import com.hippp.harrypotter.console.board.Board;
import com.hippp.harrypotter.console.level.One;
import com.hippp.harrypotter.console.level.Two;
import com.hippp.harrypotter.game.Game;
import com.hippp.harrypotter.game.actions.ActionAbstract;
import com.hippp.harrypotter.game.actions.ActionAttack;
import com.hippp.harrypotter.game.actions.ActionTrade;
import com.hippp.harrypotter.game.character.Wizard;
import com.hippp.harrypotter.game.potion.Potion;
import com.hippp.harrypotter.game.spell.normal.Spell;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class InputParser {
    private Scanner scanner;

    public InputParser() {
        this.scanner = new Scanner(System.in);
    }


    public void init(Game game) {
        Display.launchText();
        game.setName(ask("Please enter your name: "));
        game.newWizzard();
        Display.presentationText(game.getName());
        game.assignHouseToWizard();
        Display.houseAnnouncementText(game.getWizard().getHouse().getName());
        game.giveWizardWand();
        Display.wandBeforeFirstYearText();
        One one = new One();
        one.start(game, this);
        checkIfWizardIsDead(game);
        Two two = new Two();
        two.start(game, this);
        checkIfWizardIsDead(game);


    }

    public void checkIfWizardIsDead(Game game) {
        if (game.getWizard().getLife() <= 0) {
            Display.gameOver();
            game.quit();
        }
    }

    public ActionAbstract[] waitForMove(Game game, Board board) {
        String direction;
        if (game.getWizard().getHeldObject() != null) {
            Display.controllingObject(game.getWizard().getHeldObject().getName());
            direction = ask("Which direction do you want it to go ?", new String[]{"North", "South", "East", "West", "Drop"}, new String[]{"n", "s", "e", "w", "d"});
        } else {
            direction = ask("Which direction do you want to go?", new String[]{"North", "South", "East", "West", "Quit"}, new String[]{"n", "s", "e", "w", "q"});
        }
        //to lower case
        ActionAbstract[] actions = directionSwitch(direction, board, game);

        return actions;

    }

    private ActionAbstract[] directionSwitch(String direction, Board board, Game game) {
        direction = direction.toLowerCase();

        switch (direction) {
            case "n":
            case "north":
                return board.moveUp();
            case "s":
            case "south":
                return board.moveDown();
            case "e":
            case "east":
                return board.moveRight();
            case "w":
            case "west":
                return board.moveLeft();
            case "sp":
                return null;
            case "d":
                board.setHeldObjectPosition(null);
                game.getWizard().dropObject();
                return null;
            case "quit":
                game.quit();
                return null;
        }
        return null;
    }

    public void useSpell(Game game, Board board) {
        //to lower case
        Display.displaySpellList(game.getWizard().getSpells());
        if (game.getWizard().getSpells().size() == 0) {
            System.out.println("You don't have any spell");
            return;
        }
        String[] choices = game.getWizard().getSpells().keySet().toArray(new String[0]);
        String choice = ask("Which spell do you want to use?", choices);


    }


    public void parseAndDisplayStats(Wizard wizard) {
        int nbPotions = 0;
        int nbSpells = 0;
        int life = 100;
        if (wizard.getPotions() != null)
            nbPotions = wizard.getPotions().size();
        if (wizard.getSpells() != null)
            nbSpells = wizard.getSpells().size();
        if (wizard.getLife() != null)
            life = wizard.getLife();


        Display.displayStats(nbSpells, nbPotions, life);
    }

    private String ask(String question) {
        System.out.println(question);
        String choice = scanner.nextLine();
        return choice;
    }

    private String ask(String question, @NotNull String[] options) {
        System.out.println(question + "\n");
        Display.displayList(options);
        String choice = waitForInputWithValidation(options);

        return choice;
    }


    private String ask(String question, @NotNull String[] options, String[] shortcuts) {
        System.out.println(question + "\n");
        Display.displayList(options);
        // concat options and shortcuts
        ArrayList<String> allOptions = new ArrayList<>();
        allOptions.addAll(Arrays.asList(options));
        allOptions.addAll(Arrays.asList(shortcuts));


        String choice = waitForInputWithValidation(allOptions.toArray(new String[0]));
        return choice;
    }

    private String waitForInputWithValidation(String[] options) {
        String choice = null;
        do {
            if (choice != null) {
                System.out.println("Invalid option, please try again");
            }
            choice = scanner.nextLine();
        } while (!Arrays.asList(options).contains(choice));
        return choice;
    }

    public static String[] parseActions(ActionAbstract[] actions, Game game) {
        if (actions == null) return null;
        Map<String, String> responses;
        for (ActionAbstract action : actions) {
            if (action instanceof ActionTrade) {
                if (((ActionTrade) action).getGivenPotions() != null) {
                    for (Potion potion : ((ActionTrade) action).getGivenPotions()) {
                    }
                }
                if (((ActionTrade) action).getGivenSpells() != null) {
                    for (Spell spell : ((ActionTrade) action).getGivenSpells()) {
                        game.getWizard().addSpell(spell);
                        Display.spellAquired(spell.getName());
                    }
                }
            }
            if (action instanceof ActionAttack) {
                ((ActionAttack) action).execute(game.getWizard());
            }
        }

        return null;
    }


}
