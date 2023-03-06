package chess.piece;

import chess.Cell;
import chess.Player;

public abstract class Piece {
    public Player owner;
    int direction = 1;
    private boolean firstMove = true;
    private char unicode;
    public Piece(Player owner) {
        this.owner = owner;
        this.unicode = ' ';
        if(owner.getColor() == Player.Color.BLACK) { // Pawn is going up or down
            this.direction = -1;
        }
    }

    public void hasMooved() {
        firstMove = false;
    }
    public boolean isFirstMove() {
        return firstMove;
    }
    public Player getOwner() {
        return owner;
    }

    public void print() {
        System.out.printf("  %c", unicode);
    }
    public void setUnicode(char unicode) {
        this.unicode = unicode;
    }

    public boolean canMove(Cell[][] board, Cell startCell, Cell endCell) {
        // end cell must be empty or have an enemy piece
        if(!endCell.isEmpty() && endCell.getPiece().getOwner().getColor().equals(this.getOwner().getColor())) return false;
        // we only check if the end Cell is accessible by the Piece
        if(this.canMoveSpecific(board, startCell, endCell)) return true;
        System.out.println("This piece cannot move");
        return false; // By default, a piece cannot move, this is overridden in the subclasses
    }
    public boolean canMoveSpecific(Cell[][] board, Cell startCell, Cell endCell) {
        return false;
    }



}
