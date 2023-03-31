package com.hippp.harrypotter.console;

import com.hippp.harrypotter.console.board.Board;
import com.hippp.harrypotter.console.board.Cell;
import com.hippp.harrypotter.game.actions.ActionTalk;
import com.hippp.harrypotter.game.actions.ActionTrade;
import com.hippp.harrypotter.game.character.NPC;
import com.hippp.harrypotter.game.character.enemy.Enemy;
import com.hippp.harrypotter.game.spell.normal.Spell;

import java.util.HashMap;

public class Display {
    public Display() {
    }

    public static void displayMessage(String message) {
        System.out.println(message);
    }

    public static void dialog(String[] text) {
        for (String line : text) {
            System.out.println(line + "\n");
            pressEnterToContinue();
        }
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.flush();
    }

    public static void pressEnterToContinue() {
        System.out.println("[Press Enter...]");
        try {
            System.in.read();
            clear();
        } catch (Exception e) {
        }
    }

    public static void launchText() {
        System.out.println("Welcome to Harry Potter - Console Edition\n\n");
    }

    public static void presentationText(String name) {
        dialog(new String[]{
                "Welcome " + name + " to the world of Harry Potter!",
                "You are now a student at Hogwarts School of Witchcraft and Wizardry",
                "You will be sorted into one of the four houses: Gryffindor, Hufflepuff, Ravenclaw or Slytherin",
                "You will also be given a wand, which will be used to cast spells",
                "But first, you need an house. Let's go see  the sorting hat to find out which house you belong to!"
        });
    }

    public static void houseAnnouncementText(String house) {
        dialog(new String[]{
                "You have been sorted into " + house + "!",
                "You will now be given a wand, which will be used to cast spells", "Let's go see the wand maker!"
        });
    }

    public static void wandBeforeFirstYearText() {
        dialog(new String[]{
                "You have been given a wand!",
                "You are now ready to start your first year at Hogwarts!",
                "Good luck!"
        });
    }

    public static void firstYearStartText() {
        dialog(new String[]{
                "The Philopher's Stone",
                "You just arrived in the School, you soon realized many things are not normal here.",
                "While talking with your friends you heard about a stone, the Philosopher's stone.",
                "You decided to go to the library to find out more about it.",
                "You found a book about the stone, but it was written in a language you don't understand.",
                "You decided to ask the librarian for help.",
                "The librarian told you that you need to find a book that can translate the language of the book you found.",
                "You decided to go to the library to find out more about it.",
                "You found a book explaining how to translate the language of the book you found, congratulations you now have a new spell  !",
        });
    }

    public static void trollSummonedText() {
        dialog(new String[]{
                "Wow... What is happening ?",
                "You were just teleported to the library...",
                "[Noise]",
                "What is that noise ?",
                "[Noise]",
                "Oh no ! What is that",
                "It's a troll !",
                "You need to fight it !",
        });
    }

    public static void printBoard(Board board) {
        Cell[][] cells = board.getBoard();
        System.out.println(" - - - - - - - -- - - - - - - -");
        for (int i = 0; i < 15; i++) {
            System.out.print("|");
            for (int j = 0; j < 15; j++) {
                if (cells[i][j].isEmpty()) {
                    System.out.print("  ");
                } else {
                    if (cells[i][j].getObject() != null) {
                        System.out.print("O ");
                    } else if (cells[i][j].getCharacter() != null) {
                        if (cells[i][j].getCharacter() instanceof Enemy) {
                            System.out.print("E ");
                        } else if (cells[i][j].getCharacter() instanceof NPC) {
                            System.out.print("N ");
                        } else {
                            System.out.print("\uD83E\uDDCD");
                        }
                    }

                }

            }
            System.out.println("|");
        }
        System.out.println(" - - - - - - - -- - - - - - - -");
    }


    public static void displayList(String[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.println(i + " - " + list[i]);
        }
    }


    public static void trade(ActionTrade actionTrade) {
        System.out.println();
        ActionTalk dialog = actionTrade.getDialog();
        if (dialog != null) {
            dialog(dialog.getDialog());
        }

    }

    public static void displayStats(int nbSpell, int nbPotion, int nbLife) {
        // TODO
        System.out.printf("Known spell(s) : %d [SP]; Potion in inventory : %d [P]; Life : %d%n", nbSpell, nbPotion, nbLife);

    }

    public static void displaySpellList(HashMap<String, Spell> spellList) {
        System.out.println("Known spells : ");
        for (String spellName : spellList.keySet()) {
            System.out.println(spellName);
        }
        if (spellList.isEmpty()) {
            System.out.println("You don't know any spell yet, go learn some !");
        }

    }

    public static void spellAquired(String spellName) {
        System.out.println("You have aquired the spell " + spellName);
    }

    public static void controllingObject(String objectName) {
        System.out.println("You are holding " + objectName + " you can control it");
    }


}
