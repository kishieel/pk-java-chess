package pl.edu.pk.student.tomaszkisiel.chess.piece;


import pl.edu.pk.student.tomaszkisiel.chess.game.PieceRepository;
import pl.edu.pk.student.tomaszkisiel.chess.utils.Coordinates;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Piece {
    protected Color color;
    protected Coordinates coords;
    protected PieceRepository repository;

    public Piece(Color color, Coordinates coords, PieceRepository repository) {
        this.color = color;
        this.coords = coords;
        this.repository = repository;
    }

    public boolean isWhite() {
        return this.color == Color.WHITE;
    }

    public boolean isBlack() {
        return this.color == Color.BLACK;
    }

    public boolean isEnemy(Color color) {
        return this.color != color;
    }

    public boolean isFriend(Color color) {
        return this.color == color;
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

    public abstract List<Coordinates> getNextAllowedCoords();

    protected List<Coordinates> getNextAllowedCoords(List<int[]> matrix) {
        List<Coordinates> nextAllowedCoords = new ArrayList<>();

        matrix.forEach((arr) -> {
            Coordinates nextCoords;
            Optional<Piece> piece;
            for (int x = arr[0], y = arr[1]; x >= 0 && x <= 7 && y >= 0 && y <= 7; x += arr[2], y += arr[3]) {
                nextCoords = new Coordinates(x, y);
                piece = repository.getByCoords(nextCoords);
                if (piece.isEmpty()) {
                    nextAllowedCoords.add(nextCoords);
                    continue;
                }
                if (piece.get().isEnemy(color)) {
                    nextAllowedCoords.add(nextCoords);
                    break;
                }
                if (piece.get().isFriend(color)) {
                    break;
                }
            }
        });

        return nextAllowedCoords;
    }

    public abstract URL getImageUrl();
}