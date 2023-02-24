package chess.piece;
import chess.Cell;
import chess.Player;
import chess.Player.Color;
import chess.piece.Piece;

public class Pawn extends Piece {
    private int direction = 1;
    private boolean firstMove = true;
    public Pawn(Player owner) {
        super(owner);
        this.setUnicode(getUnicode());
        if(owner.getColor() == Color.BLACK) { // Pawn is going up or down
            this.direction = -1;
        }
    }


    private char getUnicode() {
        if (this.getOwner().getColor() == Color.WHITE) {
            return '♙';
        } else {
            return '♟';
        }
    }
    @Override
    public boolean canMove(Cell[][] board, Cell startCell, Cell endCell) {
        // endCell can be at (x, y + 1), (x + 1, y + 1) or (x - 1, y + 1) if an opponent is on the cell, (x, y + 2) if it is the first move

        boolean endCellContainsEnemi = endCell.getPiece().getOwner().getColor().equals(this.getOwner().getColor());
        boolean case1 = endCell.checkCoordinates(startCell.getX(), startCell.getY() + (1 * direction));
        boolean case2 =  endCell.checkCoordinates(startCell.getX() + 1, startCell.getY() + (1 * direction));
        boolean case3 =  endCell.checkCoordinates(startCell.getX() - 1, startCell.getY() + (1 * direction));
        boolean case4 = firstMove && endCell.checkCoordinates(startCell.getX(), startCell.getY() + (2 * direction));
        if(case1 || case2 || case3 || case4) {
            return true;
        }
        return false;
    }
}
