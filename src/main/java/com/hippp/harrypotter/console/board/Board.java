package com.hippp.harrypotter.console.board;

import com.hippp.harrypotter.game.actions.ActionAbstract;
import com.hippp.harrypotter.game.character.Character;
import com.hippp.harrypotter.game.character.Wizard;
import com.hippp.harrypotter.game.objects.AbstractObject;
import lombok.Getter;

public class Board {

    @Getter
    private int[] playerPosition;

    private Wizard player;

    int BOARD_WIDTH;
    int BOARD_HEIGHT;
    @Getter
    private Cell[][] board;

    public Board(int width, int height) {
        this.BOARD_WIDTH = width;
        this.BOARD_HEIGHT = height;

        this.board = new Cell[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                this.board[i][j] = new Cell();
            }
        }
    }

    public void setInCase(int x, int y, AbstractObject object) {
        this.board[x][y].setInCase(object);
    }

    public void setInCase(int x, int y, Character character) {
        this.board[x][y].setInCase(character);
    }

    public void setPlayer(int x, int y, Wizard player) {
        if (this.player != null) {
            this.board[this.playerPosition[0]][this.playerPosition[1]].setInCase((Wizard) null);
        }
        this.player = player;
        this.playerPosition = new int[]{x, y};
        this.board[x][y].setInCase(player);
    }

    private ActionAbstract[] move(int dx, int dy) {
        if (this.playerPosition[0] + dx < 0
                || this.playerPosition[0] + dx > BOARD_HEIGHT - 1
                || this.playerPosition[1] + dy < 0
                || this.playerPosition[1] + dy > BOARD_WIDTH - 1
        ) return null;

        if (this.board[this.playerPosition[0] + dx][this.playerPosition[1] + dy].isEmpty()) {
            this.board[this.playerPosition[0]][this.playerPosition[1]].setInCase((Wizard) null);
            this.playerPosition[0] += dx;
            this.playerPosition[1] += dy;
            this.board[this.playerPosition[0]][this.playerPosition[1]].setInCase(this.player);
        } else {
            this.board[this.playerPosition[0] + dx][this.playerPosition[1] + dy].interactWith();
        }
        return null;

    }

    public ActionAbstract[] moveUp() {
        return this.move(-1, 0);
    }

    public ActionAbstract[] moveDown() {
        return this.move(1, 0);
    }

    public ActionAbstract[] moveLeft() {
        return this.move(0, -1);
    }

    public ActionAbstract[] moveRight() {
        return this.move(0, 1);
    }

    public boolean isVisited(int x, int y) {
        return this.board[x][y].isVisited();
    }

}
