package com.hippp.harrypotter.console.level;

import com.hippp.harrypotter.console.InputParser;
import com.hippp.harrypotter.console.board.Board;
import com.hippp.harrypotter.game.Game;

public class Two extends LevelAbstract {
    private int currentStep;

    public void start(Game game, InputParser inputParser) {
        initPhase1(game);
        startSecondYear(game, inputParser);
    }

    private void startSecondYear(Game game, InputParser inputParser) {
        // Start second year
        initPhase1(game);
        game.startSecondYear();
        //display.secondYearStartText();
        this.currentStep = 0;
    }

    private void initPhase1(Game game) {
        board = new Board(15, 15);
        game.startSecondYear();
    }

}
