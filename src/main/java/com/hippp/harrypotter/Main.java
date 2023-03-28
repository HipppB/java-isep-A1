package com.hippp.harrypotter;

import com.hippp.harrypotter.console.Display;
import com.hippp.harrypotter.console.InputParser;
import com.hippp.harrypotter.game.Game;


public class Main {
    Game game;
    InputParser inputParser;

    public Main(String[] args) {
        this.game = new Game();
        this.inputParser = new InputParser();
        inputParser.init(game);

    }


    public static void main(String[] args) {
        Main main = new Main(args);


    }


}
