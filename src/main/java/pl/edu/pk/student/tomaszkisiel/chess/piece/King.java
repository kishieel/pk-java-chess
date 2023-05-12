package pl.edu.pk.student.tomaszkisiel.chess.piece;

import pl.edu.pk.student.tomaszkisiel.chess.game.GameManager;
import pl.edu.pk.student.tomaszkisiel.chess.game.PieceRepository;
import pl.edu.pk.student.tomaszkisiel.chess.utils.Coordinates;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public King(Color color, Coordinates coords, PieceRepository repository) {
        super(color, coords, repository);
    }

    @Override
    public List<Coordinates> getNextAllowedCoords() {
        List<Coordinates> nextAllowedCoords = new ArrayList<>();
        List<Coordinates> matrix = List.of(
                new Coordinates(coords.getX() - 1, coords.getY()),
                new Coordinates(coords.getX() - 1, coords.getY() + 1),
                new Coordinates(coords.getX(), coords.getY() + 1),
                new Coordinates(coords.getX() + 1, coords.getY() + 1),
                new Coordinates(coords.getX() + 1, coords.getY()),
                new Coordinates(coords.getX() + 1, coords.getY() - 1),
                new Coordinates(coords.getX(), coords.getY() - 1),
                new Coordinates(coords.getX() - 1, coords.getY() - 1)
        );

        matrix.forEach(coords -> {
            if (coords.isXYInRange(0, 7, 0, 7) && repository.isEnemyOrNobodyOnCoords(coords, this.color)) {
                nextAllowedCoords.add(coords);
            }
        });

        return nextAllowedCoords;
    }

    @Override
    public URL getImageUrl() {
        String name = String.format("%s-king.png", isWhite() ? "white" : "black");
        return ClassLoader.getSystemResource(name);
    }
}
