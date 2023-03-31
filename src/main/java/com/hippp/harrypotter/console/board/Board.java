package com.hippp.harrypotter.console.board;

import com.hippp.harrypotter.console.Display;
import com.hippp.harrypotter.game.actions.ActionAbstract;
import com.hippp.harrypotter.game.character.Character;
import com.hippp.harrypotter.game.character.Wizard;
import com.hippp.harrypotter.game.objects.AbstractObject;
import lombok.Getter;
import lombok.Setter;

public class Board {

    @Getter
    private int[] playerPosition;

    @Getter
    @Setter
    private int[] heldObjectPosition;

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
                this.board[i][j] = new Cell(new int[]{i, j});
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

    private int getDistance(int[] position1, int[] position2) {
        return Math.abs(position1[0] - position2[0]) + Math.abs(position1[1] - position2[1]);
    }

    private ActionAbstract[] move(int dx, int dy) {
        if (this.heldObjectPosition == null && this.player.getHeldObject() != null)
            this.heldObjectPosition = this.player.getHeldObject().getPosition();
        int[] position = this.player.getHeldObject() == null ? this.playerPosition : this.heldObjectPosition;
        System.out.println("Moving from " + position[0] + " " + position[1]); // TODO remove this line
        if (position[0] + dx < 0
                || position[0] + dx > BOARD_HEIGHT - 1
                || position[1] + dy < 0
                || position[1] + dy > BOARD_WIDTH - 1
        ) return null;
        if (this.board[position[0] + dx][position[1] + dy].isEmpty()) {
            if (this.player.getHeldObject() != null && this.getDistance(new int[]{position[0] + dx, position[1] + dy}, this.playerPosition) > 3) {
                Display.displayMessage("You tried to move the object too far away from you. It fell on the ground.");
                Display.displayMessage("You were suprized by the noise and lost your concentration. You lost 10 points of health.");
                this.heldObjectPosition = null;
                this.player.dropObjectError();
                return null;
            }
            this.board[position[0]][position[1]].setInCase((Wizard) null);
            this.board[position[0]][position[1]].setInCase((AbstractObject) null);
            position[0] += dx;
            position[1] += dy;
            if (this.player.getHeldObject() == null) {
                this.board[position[0]][position[1]].setInCase(this.player);
            } else {
                this.board[position[0]][position[1]].setInCase(this.player.getHeldObject());
            }
            System.out.println("Moved to " + position[0] + " " + position[1]); // TODO remove this line
        } else {
            ActionAbstract[] possibleActions = this.board[position[0] + dx][position[1] + dy].interactWith(this.player);
            if (possibleActions != null) {
                return possibleActions;
            }
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

    public void reset() {
        this.board = new Cell[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                this.board[i][j] = new Cell(new int[]{i, j});
            }
        }
    }
}
