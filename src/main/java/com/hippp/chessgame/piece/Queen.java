package com.hippp.chessgame.piece;
import com.hippp.chessgame.Cell;
import com.hippp.chessgame.Player;

public class Queen extends Piece {

    Piece bishop;
    Piece rook;
    public Queen(Player owner) {
        super(owner);
        this.setUnicode(getUnicode());
        bishop = new Bishop(owner);
        rook = new Rook(owner);

    }


    private char getUnicode() {
        if(this.getOwner().getColor() == Player.Color.WHITE) {
            return 0x2655;
        } else {
            return 0x265B;
        }
    }

    public boolean canMoveSpecific(Cell[][] board, Cell startCell, Cell endCell) {
        // All cases of Bishop and Rook
        return bishop.canMoveSpecific(board, startCell, endCell) || rook.canMoveSpecific(board, startCell, endCell);
    }
}
