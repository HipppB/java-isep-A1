package chessgame.piece;

import chessgame.Cell;
import chessgame.Player;
import chessgame.Player.Color;

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
    public boolean canMoveSpecific(Cell[][] board, Cell startCell, Cell endCell) {
        // end cell can be at (x+-1; y+-1), (x+-1; y), (x; y+-1)
        boolean case1 = endCell.checkCoordinates(startCell.getX() + 1, startCell.getY() + 1);
        boolean case2 = endCell.checkCoordinates(startCell.getX() + 1, startCell.getY());
        boolean case3 = endCell.checkCoordinates(startCell.getX(), startCell.getY() + 1);
        boolean case4 = endCell.checkCoordinates(startCell.getX() - 1, startCell.getY() - 1);
        boolean case5 = endCell.checkCoordinates(startCell.getX() - 1, startCell.getY());
        boolean case6 = endCell.checkCoordinates(startCell.getX(), startCell.getY() - 1);
        boolean case7 = endCell.checkCoordinates(startCell.getX() + 1, startCell.getY() - 1);
        boolean case8 = endCell.checkCoordinates(startCell.getX() - 1, startCell.getY() + 1);

        return case1 || case2 || case3 || case4 || case5 || case6 || case7 || case8;
    }

    }
