package com.hippp.harrypotter.console.level;

import com.hippp.harrypotter.console.Display;
import com.hippp.harrypotter.console.InputParser;
import com.hippp.harrypotter.console.board.Board;
import com.hippp.harrypotter.game.Game;
import com.hippp.harrypotter.game.actions.ActionTalk;
import com.hippp.harrypotter.game.character.NPC;
import com.hippp.harrypotter.game.character.enemy.AbstractEnemy;
import com.hippp.harrypotter.game.level.FirstYear;
import com.hippp.harrypotter.game.objects.AbstractObject;

import java.util.List;

public class One extends LevelAbstract {


    public One() {
        super.board = new Board(15, 15);
    }

    public void start(Game game, InputParser inputParser) {
        initPhase1(game);
        startFirstYear(game, inputParser);
    }

    private void startFirstYear(Game game, InputParser inputParser) {
        // Start first year
        game.startFirstYear();
        Display.firstYearStartText();
        int currentStep = 0;

        // get objects to display on board


        Display.printBoard(board);

        // Start first year loop
        while (game.getCurrentLevel().isRunning()) {


            InputParser.parseActions(inputParser.waitForMove(game, board), game);
            if (currentStep == 0 && firstObjective(board)) {
                initPhase2(game, board);
                currentStep++;
            }


            inputParser.parseAndDisplayStats(game.getWizard());
            Display.printBoard(board);
            ((FirstYear) game.getCurrentLevel()).checkStatus();

        }
        if (game.getCurrentLevel().isLost()) {
            Display.printBoard(board);
            Display.printLose();

        } else {
            Display.printBoard(board);
            Display.displayMessage("You defeated the Troll ! Good job ! Let's go to the next level"); // TODO: add win text and go to next level
        }


    }


    private boolean firstObjective(Board board) {
        // If player has not visited the four corners we return false
        return board.isVisited(0, 0)
                && board.isVisited(0, 14)
                && board.isVisited(14, 0)
                && board.isVisited(14, 14);

    }

    private void initPhase1(Game game) {
        board = new Board(15, 15);
        game.startFirstYear();
        List<NPC> npcs = game.getCurrentLevel().getAvailableNPCs();

        board.setInCase(10, 4, npcs.get(0));
        board.setInCase(12, 3, npcs.get(1));
        board.setInCase(4, 9, npcs.get(2));
        board.setInCase(2, 2, npcs.get(3));

        ActionTalk additionalActionTalk = new ActionTalk(
                new String[]{
                        "The Troll ?",
                        "I never saw him, but I heard he was a big one !",
                        "The rumor says he can be found by visiting the four corners of the school.",
                        "But I don't know if it's true, I never saw him myself !",
                        "I won't try to find him, I'm not brave enough !",
                }, null, false
        );
        npcs.get(0).addDialogue(additionalActionTalk);

        board.setPlayer(4, 7, game.getWizard()); // TODO set in 14 7

    }

    private void initPhase2(Game game, Board board) {
        // Summon Troll
        board.reset();
        List<AbstractObject> objects = game.getCurrentLevel().getAvailableObjects();

        List<AbstractEnemy> enemies = game.getCurrentLevel().getAvailableEnemies();
        board.setInCase(0, 2, objects.get(0));
        board.setInCase(2, 8, objects.get(1));
        board.setInCase(6, 7, objects.get(2));// TODO Set in 10 14

        board.setInCase(7, 7, enemies.get(0));

        board.setPlayer(5, 7, game.getWizard()); // TODO SEt in 0 0

        Display.trollSummonedText();
    }
}
