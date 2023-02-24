package chess.piece;

import chess.Player;
import chess.Player.Color;
public class Rook extends Piece {
    public Rook(Player owner) {
        super(owner);
        this.setUnicode(getUnicode());
    }


    private char getUnicode() {
        if(this.getOwner().getColor() == Color.WHITE) {
            return 0x2656;
        } else {
            return 0x265C;
        }
    }

}
