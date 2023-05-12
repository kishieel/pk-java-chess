package pl.edu.pk.student.tomaszkisiel.chess.piece;

import pl.edu.pk.student.tomaszkisiel.chess.game.PieceRepository;
import pl.edu.pk.student.tomaszkisiel.chess.utils.Coordinates;

import java.awt.*;
import java.net.URL;
import java.util.List;

public class Rook extends Piece {
    public Rook(Color color, Coordinates coords, PieceRepository repository) {
        super(color, coords, repository);
    }

    @Override
    public List<Coordinates> getNextAllowedCoords() {
        List<int[]> matrix = List.of(
                new int[]{coords.getX() - 1, coords.getY(), -1, 0},
                new int[]{coords.getX() + 1, coords.getY(), +1, 0},
                new int[]{coords.getX(), coords.getY() - 1, 0, -1},
                new int[]{coords.getX(), coords.getY() + 1, 0, +1}
        );

        return getNextAllowedCoords(matrix);
    }

    @Override
    public URL getImageUrl() {
        String name = String.format("%s-rook.png", isWhite() ? "white" : "black");
        return ClassLoader.getSystemResource(name);
    }
}
