package chess.piece;

import chess.Cell;
import chess.Player;
import chess.Player.Color;
import chess.piece.Piece;

public class Bishop extends Piece {
    public Bishop(Player owner) {
        super(owner);
        this.setUnicode(getUnicode());
    }


    private char getUnicode() {
        if (this.getOwner().getColor() == Color.WHITE) {
            return '♗';
        } else {
            return '♝';
        }
    }

    @Override
    public boolean canMove(Cell start, Cell end) {

        return false;
    }
}
