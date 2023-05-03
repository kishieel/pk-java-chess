package pl.edu.pk.student.tomaszkisiel.chess.piece;


import pl.edu.pk.student.tomaszkisiel.chess.game.Board;
import pl.edu.pk.student.tomaszkisiel.chess.utils.Coordinates;

import java.awt.*;
import java.net.URL;
import java.util.List;

public abstract class Piece {
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

    public abstract URL getImageUrl();
}