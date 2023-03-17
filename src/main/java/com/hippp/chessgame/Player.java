package com.hippp.chessgame;

public class Player {
    public enum Color {
        WHITE, BLACK
    }

    private Cell kingCell;
    private Color color;

    public Player(Color color) {
        this.color = color;
    }

    public Cell getKingCell() {
        return kingCell;
    }

    public void setKingCell(Cell kingCell) {
        this.kingCell = kingCell;
    }
    public Color getColor() {
        return color;
    }
    public String toSimpleString() {
        if(color == Color.WHITE)
            return "W";
        else
            return "B";
    }
}
