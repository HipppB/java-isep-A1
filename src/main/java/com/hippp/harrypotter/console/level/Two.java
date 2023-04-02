package com.hippp.harrypotter.console.level;

import com.hippp.harrypotter.console.Display;
import com.hippp.harrypotter.console.InputParser;
import com.hippp.harrypotter.console.board.Board;
import com.hippp.harrypotter.game.Game;
import com.hippp.harrypotter.game.objects.AbstractObject;

import java.util.List;

public class Two extends LevelAbstract {


    public void start(Game game, InputParser inputParser) {
        initPhase1(game);
        startSecondYear(game, inputParser);
    }

    private void startSecondYear(Game game, InputParser inputParser) {
        // Start second year
        initPhase1(game);
        Display.secondYearStartText();
        int currentStep = 0;

        while (game.getCurrentLevel().isRunning()) {
            InputParser.parseActions(inputParser.waitForMove(game, board), game);
            if (currentStep == 0) {
                if (checkBoardForGenerator(game, board)) currentStep++;
            } else if (currentStep == 1) {

            }
        }


    }

    private void initPhase1(Game game) {
        board = new Board(15, 15);
        game.startSecondYear();
        board.getBoard()[2][2].setInCase(game.getCurrentLevel().getAvailableNPCs().get(0));

        List<AbstractObject> objects = game.getCurrentLevel().getAvailableObjects();

        board.getBoard()[2][3].setInCase(objects.get(0));
        board.getBoard()[2][14].setInCase(objects.get(1));
        board.getBoard()[12][5].setInCase(objects.get(2));
        board.getBoard()[2][6].setInCase(objects.get(3));
        board.getBoard()[5][7].setInCase(objects.get(4));
        board.getBoard()[13][9].setInCase(objects.get(5));
        board.getBoard()[2][9].setInCase(objects.get(6));
        board.getBoard()[14][14].setInCase(objects.get(7));

        board.getBoard()[7][7].setInCase(game.getCurrentLevel().getAvailableEnemies().get(0));


    }

    public boolean checkBoardForGenerator(Game game, Board board) {
        int[] middle = getMiddleIfRockInSquare(board);
        if (middle != null) {
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
        boolean isSquare = true;
        int[] firstRock = null;
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                if (board.containsObject(i, j, "rock")) {
                    firstRock = new int[]{i, j};
                    if (!(board.containsObject(i + 1, j, "rock") &&
                            board.containsObject(i + 2, j, "rock") &&
                            board.containsObject(i + 2, j + 1, "rock") &&
                            board.containsObject(i + 2, j + 2, "rock") &&
                            board.containsObject(i + 1, j + 2, "rock") &&
                            board.containsObject(i, j + 2, "rock") &&
                            board.containsObject(i, j + 1, "rock"))) {
                        isSquare = false;
                    }
                    break;
                }
            }
        }
        if (isSquare) {
            return new int[]{firstRock[0] + 1, firstRock[1] + 1};
        }
        return null;
    }
}
