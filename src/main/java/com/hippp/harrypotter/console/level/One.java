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

public class One {
    private int currentStep;

    private Board board;
    private InputParser inputParser;

    public void start(Game game, InputParser inputParser) {
        this.board = new Board(15, 15);
        this.inputParser = inputParser;
        initPhase1(game);

        startFirstYear(game, inputParser);
    }

    private void startFirstYear(Game game, InputParser inputParser) {
        // Start first year
        game.startFirstYear();
        //display.firstYearStartText();
        this.currentStep = 0;

        FirstYear firstYear = ((FirstYear) game.getCurrentLevel());
        // get objects to display on board


        Display.printBoard(board);

        // Start first year loop
        while (((FirstYear) game.getCurrentLevel()).isRunning()) {


            InputParser.parseActions(inputParser.waitForMove(game, board), game);
            if (this.currentStep == 0 && firstObjective(game, board)) {
                initPhase2(game, board);
                this.currentStep++;
            }
//            if (this.currentStep == 1 && secondObjective(game, board)) {
//                initPhase2(game, board);
//                this.currentStep++;
//            }

            inputParser.parseAndDisplayStats(game.getWizard());
            Display.printBoard(board);
            ((FirstYear) game.getCurrentLevel()).checkStatus();

        }


    }


    private boolean firstObjective(Game game, Board board) {
        // If player has not visited the four corners we return false
        if ((!board.isVisited(0, 0)
                || !board.isVisited(0, 14)
                || !board.isVisited(14, 0)
                || !board.isVisited(14, 14))
                && false //TODO remove this line
        ) {
            return false;
        }
        return true;

    }

    private boolean secondObjective(Game game, Board board) {
        // If player has not visited the four corners we return false
        return false;

    }

    private void initPhase1(Game game) {
        // Start first year
        board = new Board(15, 15);
        game.startFirstYear();
        NPC[] npcs = ((FirstYear) game.getCurrentLevel()).getAvailableNPCs();

        board.setInCase(10, 4, npcs[0]);
        board.setInCase(12, 3, npcs[1]);
        board.setInCase(4, 9, npcs[2]);
        board.setInCase(2, 2, npcs[3]);

        ActionTalk additionalActionTalk = new ActionTalk(
                new String[]{
                        "The Troll ?",
                        "I never saw him, but I heard he was a big one !",
                        "The rumor says he can be found by visiting the four corners of the school.",
                        "But I don't know if it's true, I never saw him myself !",
                        "I won't try to find him, I'm not brave enough !",
                }, null, false
        );
        npcs[0].addDialogue(additionalActionTalk);

        board.setPlayer(4, 7, game.getWizard()); //TODO: change 4 to 14

    }

    private void initPhase2(Game game, Board board) {
        // Summon Troll
        board.reset();
        AbstractObject[] objects = ((FirstYear) game.getCurrentLevel()).getAvailableObjects();

        AbstractEnemy[] enemies = ((FirstYear) game.getCurrentLevel()).getAvailableEnemies();
        board.setInCase(0, 2, objects[0]);
        board.setInCase(2, 8, objects[0]);
        board.setInCase(10, 14, objects[0]);

        board.setInCase(7, 7, enemies[0]);

        board.setPlayer(0, 0, game.getWizard());

        Display.trollSummonedText();
    }
}
