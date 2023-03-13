package chessgame.piece;
import chessgame.Cell;
import chessgame.Player;
import chessgame.Player.Color;

public class Pawn extends Piece {

    public Pawn(Player owner) {
        super(owner);
        this.setUnicode(getUnicode());

    }


    private char getUnicode() {
        if (this.getOwner().getColor() == Color.WHITE) {
            return '♙';
        } else {
            return '♟';
        }
    }
    public boolean canMoveSpecific(Cell[][] board, Cell startCell, Cell endCell) {
        // endCell can be at (x, y + 1), (x + 1, y + 1) or (x - 1, y + 1) if an opponent is on the cell, (x, y + 2) if it is the first move

        boolean endCellContainsEnemy = !endCell.isEmpty() && !endCell.getPiece().getOwner().getColor().equals(this.getOwner().getColor());
        boolean case1 = endCell.isEmpty() && endCell.checkCoordinates(startCell.getX(), startCell.getY() + this.direction);
        boolean case2 =  endCellContainsEnemy && endCell.checkCoordinates(startCell.getX() + 1, startCell.getY() + this.direction);
        boolean case3 =  endCellContainsEnemy && endCell.checkCoordinates(startCell.getX() - 1, startCell.getY() + this.direction);
        boolean case4 = endCell.isEmpty() && this.isFirstMove() && endCell.checkCoordinates(startCell.getX(), startCell.getY() + (2 * this.direction)) && board[startCell.getX()][startCell.getY() + this.direction].isEmpty();

        return case1 || case2 || case3 || case4;
    }


}
