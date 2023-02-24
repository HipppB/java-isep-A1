package chess.piece;
import chess.Player;
import chess.Player.Color;

public class Queen extends Piece {
    public Queen(Player owner) {
        super(owner);
        this.setUnicode(getUnicode());

    }


    private char getUnicode() {
        if(this.getOwner().getColor() == Color.WHITE) {
            return 0x2655;
        } else {
            return 0x265B;
        }
    }
}
