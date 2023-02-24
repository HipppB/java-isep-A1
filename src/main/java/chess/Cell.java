package chess;

import chess.piece.Piece;

public class Cell {
    private int x;
    private int y;
    private Piece piece;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void print() {
        if (piece == null) {
            System.out.print(" ");
        } else {
            piece.print();
        }
    }

    public int getDistance(Cell other) {
        return Math.max(Math.abs(this.x - other.x), Math.abs(this.y - other.y));
    }
    public boolean isEmpty() {
        return this.piece == null;
    }
    public boolean checkCoordinates(int testedX, int testedY) {
        if(this.x == testedX && this.y == testedY)
            return true;
        return false;
    }

}
