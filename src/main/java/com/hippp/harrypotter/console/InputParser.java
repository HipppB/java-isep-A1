package com.hippp.harrypotter.console;

import com.hippp.harrypotter.console.board.Board;
import com.hippp.harrypotter.console.level.One;
import com.hippp.harrypotter.game.Game;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Scanner;

public class InputParser {
    private Display display;
    private Scanner scanner;

    public InputParser() {
        this.display = new Display();
        this.scanner = new Scanner(System.in);
    }


    public void init(Game game) {
        display.launchText();
        game.setName(ask("Please enter your name: "));
        game.newWizzard();
        display.presentationText(game.getName());
        game.assignHouseToWizard();
        display.houseAnnouncementText(game.getWizard().getHouse().getName());
        game.giveWizardWand();
        display.wandBeforeFirstYearText();
        One one = new One();
        one.start(game, this);


    }


    public void waitForMove(Game game, Board board) {
        String direction = ask("Which direction do you want to go?", new String[]{"North", "South", "East", "West", "Quit"});
        switch (direction) {
            case "North":
                board.moveUp();
                break;
            case "South":
                board.moveDown();
                break;
            case "East":
                board.moveRight();
                break;
            case "West":
                board.moveLeft();
                break;
            case "Quit":
                game.quit();
                break;
        }

    }


    private String ask(String question) {
        System.out.println(question);
        String choice = scanner.nextLine();
        return choice;
    }

    private String ask(String question, @NotNull String[] options) {

        System.out.println(question + "\n");
        for (String option : options) {
            System.out.println(option);
        }
        String choice = null;
        do {
            if (choice != null) {
                System.out.println("Invalid choice, please try again");
            }
            choice = scanner.nextLine();
        } while (!Arrays.asList(options).contains(choice));
        return choice;
    }


}
