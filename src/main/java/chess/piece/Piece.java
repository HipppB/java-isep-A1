package chess.piece;

import chess.Cell;
import chess.Player;

public abstract class Piece {
    private Player owner;

    private char unicode;
    public Piece(Player owner) {
        this.owner = owner;
        this.unicode = ' ';
    }

    public Player getOwner() {
        return owner;
    }

    public void print() {
        System.out.printf(" %c", unicode);
    }
    public void setUnicode(char unicode) {
        this.unicode = unicode;
    }

    public boolean canMove(Cell[][] board, Cell startCell, Cell endCell) {
        // we only check if the end Cell is accessible by the Piece
        return false; // By default, a piece cannot move, this is overridden in the subclasses
    }


}
