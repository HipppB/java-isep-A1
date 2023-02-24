package chess.piece;

import chess.Player;
import chess.Player.Color;
import chess.piece.Piece;

public class King extends Piece {
    public King(Player owner) {
        super(owner);
        this.setUnicode(getUnicode());
    }


    private char getUnicode() {
        if (this.getOwner().getColor() == Color.WHITE) {
            return '♔';
        } else {
            return '♚';
        }
    }
}
