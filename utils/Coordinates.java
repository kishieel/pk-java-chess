package pl.edu.pk.student.tomaszkisiel.chess.utils;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setCoordX(int x) {
        this.x = x;
    }

    public void setCoordY(int y) {
        this.y = y;
    }

    public void getCoordX() {
        return this.x;
    }

    public void getCoordY() {
        return this.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates)) return false;
        Coordinates coords = (Coordinates) o;
        return this.x == coords.x && this.y == coords.y;
    }

    @Override
    public int hashCode() {
        return this.x * ( Board.MAX_X + 1 ) + this.y;
    }
}
