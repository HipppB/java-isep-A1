package chess;

public class Player {
    public enum Color {
        WHITE, BLACK
    }
    private Color color;

    public Player(Color color) {
        this.color = color;
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
