package pl.edu.pk.student.tomaszkisiel.chess.utils;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(int x, int y) {
        this.setCoords(x, y);
    }

    public void setCoords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean isXYInRange(int minX, int maxX, int minY, int maxY) {
        return x >= minX && x <= maxX && y >= minY && y <= maxY;
    }

    public boolean isYInRange(int minY, int maxY) {
        return y >= minY && y <= maxY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates coords)) return false;
        return this.x == coords.x && this.y == coords.y;
    }

    @Override
    public int hashCode() {
        return this.x * 9 + this.y;
    }
}
