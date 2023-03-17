package com.hippp.chessgame.piece;

import com.hippp.chessgame.Cell;
import com.hippp.chessgame.Player;
import com.hippp.chessgame.Player.Color;
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

    public boolean canMoveSpecific(Cell[][] board, Cell startCell, Cell endCell) {

        int z = startCell.getDistance(endCell);
        // end cell must have x or y coordinate equal to start cell
        if(!(startCell.getX() == endCell.getX() || startCell.getY() == endCell.getY())) return false;

        int startY = Math.min(startCell.getY(), endCell.getY());
        int startX = Math.min(startCell.getX(), endCell.getX());
        // No piece in between

        if(startCell.getX() == endCell.getX()) {
            for (int i = 1; i < z; i++) {
                System.out.println(board[startX][startY + i].getX() + " " + board[startX][startY + i].getY());
                if (!board[startX][startY + i].isEmpty()) return false;
            }
        } else {
            for (int i = 1; i < z; i++) {
                if (!board[startX + i][startY].isEmpty()) return false;
            }
        }



        return true;
    }

}
