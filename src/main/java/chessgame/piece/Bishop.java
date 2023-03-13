package chessgame.piece;

import chessgame.Cell;
import chessgame.Player;
import chessgame.Player.Color;

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

    public boolean canMoveSpecific(Cell[][] board, Cell startCell, Cell endCell) {
        // startCell and endCell must be on the same diagonal
        if (Math.abs(startCell.getX() - endCell.getX()) != Math.abs(startCell.getY() - endCell.getY())) return false;
        // No piece in between
        int z = startCell.getDistance(endCell);
        Cell start = startCell.getX() < endCell.getX() ? startCell : endCell ;
        Cell end = startCell.getX() < endCell.getX() ? endCell : startCell ;
        // Now x of start always smaller
        boolean isYIncreasing = start.getY() < end.getY();
        for (int i = 1; i < z; i++) {
            //System.out.println("checking if there is a piece at " + (start.getX() + i) + ' ' + (start.getY() + (isYIncreasing ? i : -i)));
            if (!board[start.getX() + i][start.getY() + (isYIncreasing ? i : -i)].isEmpty()) return false;
        }
        return true;
    }
}