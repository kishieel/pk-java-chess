package pl.edu.pk.student.tomaszkisiel.chess.pieces;

import pl.edu.pk.student.tomaszkisiel.chess.utils.Color;
import pl.edu.pk.student.tomaszkisiel.chess.utils.Coordinates;

public class Piece {
    protected Color color;
    protected Coordinates coords;
    protected Board board;

    public Piece(Color color, Coordinates coords, Board board) {
        this.color = color;
        this.coords = coords;
        this.board = board;
    }

    public boolean isWhite() {
        return this.color == Color.WHITE;
    }

    public boolean isBlack() {
        return this.color == Color.BLACK;
    }

    public Color getColor() {
        return this.color;
    }

    public void setCoords(Coordinates coords) {
        this.coords = coords;
    }

    public Coordinates getCoords() {
        return this.coords;
    }

    public abstract List<Coordinates> getAllowedMoves();
}