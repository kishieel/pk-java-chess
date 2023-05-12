package pl.edu.pk.student.tomaszkisiel.chess.piece;

import pl.edu.pk.student.tomaszkisiel.chess.game.PieceRepository;
import pl.edu.pk.student.tomaszkisiel.chess.utils.Coordinates;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(Color color, Coordinates coords, PieceRepository repository) {
        super(color, coords, repository);
    }

    @Override
    public List<Coordinates> getNextAllowedCoords() {
        List<int[]> matrix = List.of(
                new int[]{coords.getX() - 1, coords.getY() - 1, -1, -1},
                new int[]{coords.getX() + 1, coords.getY() - 1, +1, -1},
                new int[]{coords.getX() - 1, coords.getY() + 1, -1, +1},
                new int[]{coords.getX() + 1, coords.getY() + 1, +1, +1}
        );

        return getNextAllowedCoords(matrix);
    }

    @Override
    public URL getImageUrl() {
        String name = String.format("%s-bishop.png", isWhite() ? "white" : "black");
        return ClassLoader.getSystemResource(name);
    }
}
