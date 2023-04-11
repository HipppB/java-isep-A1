package com.hippp.harrypotter.console.level;

import com.hippp.harrypotter.console.Display;
import com.hippp.harrypotter.console.InputParser;
import com.hippp.harrypotter.console.board.Board;
import com.hippp.harrypotter.game.Game;
import com.hippp.harrypotter.game.level.SecondYear;
import com.hippp.harrypotter.game.objects.AbstractObject;

import java.util.List;

public class Two extends LevelAbstract {


    public Two() {
        super.board = new Board(15, 15);
    }

    public void start(Game game, InputParser inputParser) {
        startSecondYear(game, inputParser);
    }

    private void startSecondYear(Game game, InputParser inputParser) {
        // Start second year
        initPhase1(game);
        Display.secondYearStartText();
        int currentStep = 0;
        inputParser.parseAndDisplayStats(game.getWizard());
        Display.printBoard(board);
        while (game.getCurrentLevel().isRunning()) {
            InputParser.parseActions(inputParser.waitForMove(game, board), game);
            inputParser.parseAndDisplayStats(game.getWizard());
            Display.printBoard(board);

            System.out.println("Current step: " + currentStep);
            if (currentStep == 0) {
                if (checkBoardForGenerator(game, board)) currentStep++;
            } else if (currentStep == 1) {
                System.out.println("You have created the generator");
            }
        }


    }

    private void initPhase1(Game game) {
        game.startSecondYear();

        board = new Board(15, 15);
        board.setInCase(2, 2, game.getCurrentLevel().getAvailableNPCs().get(0));

        List<AbstractObject> objects = game.getCurrentLevel().getAvailableObjects();

        board.setInCase(2, 3, objects.get(0));
        board.setInCase(2, 4, objects.get(1));
        board.setInCase(2, 5, objects.get(2));
        board.setInCase(3, 3, objects.get(3));
        board.setInCase(4, 5, objects.get(4));
        board.setInCase(4, 4, objects.get(5));
        board.setInCase(4, 3, objects.get(6));
        board.setInCase(3, 6, objects.get(7));

        board.setInCase(7, 7, game.getCurrentLevel().getAvailableEnemies().get(0));
        board.setPlayer(4, 7, game.getWizard());

    }

    public boolean checkBoardForGenerator(Game game, Board board) {
        int[] middle = getMiddleIfRockInSquare(board);
        if (middle != null) {

            ((SecondYear) game.getCurrentLevel()).summonObject();
            board.getBoard()[middle[0]][middle[1]].setInCase(
                    game.getCurrentLevel().getAvailableObjects().get(
                            game.getCurrentLevel().getAvailableObjects().size() - 1
                    )
            );
            return true;
        }
        return false;
    }

    public int[] getMiddleIfRockInSquare(Board board) {
        for (int i = 0; i < board.getBoard().length - 2; i++) {
            for (int j = 0; j < board.getBoard()[i].length - 2; j++) {
                if (board.containsObject(i, j, "rock")) {
                    if ((board.containsObject(i + 1, j, "rock") &&
                            board.containsObject(i + 2, j, "rock") &&
                            board.containsObject(i + 2, j + 1, "rock") &&
                            board.containsObject(i + 2, j + 2, "rock") &&
                            board.containsObject(i + 1, j + 2, "rock") &&
                            board.containsObject(i, j + 2, "rock") &&
                            board.containsObject(i, j + 1, "rock"))) {
                        return new int[]{i + 1, j + 1};
                    }
                }
            }
        }

        return null;
    }
}
