package pl.edu.pk.student.tomaszkisiel.chess.piece;

import pl.edu.pk.student.tomaszkisiel.chess.game.Board;
import pl.edu.pk.student.tomaszkisiel.chess.utils.Coordinates;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(Color color, Coordinates coords, Board board) {
        super(color, coords, board);
    }

    @Override
    public List<Coordinates> getAllowedMoves() {
        List<Coordinates> moves = new ArrayList<>();
        List<Coordinates> matrix = List.of(
                new Coordinates(coords.getCoordX() - 1, coords.getCoordY() + 2),
                new Coordinates(coords.getCoordX() + 1, coords.getCoordY() + 2),
                new Coordinates(coords.getCoordX() + 2, coords.getCoordY() + 1),
                new Coordinates(coords.getCoordX() + 2, coords.getCoordY() - 1),
                new Coordinates(coords.getCoordX() + 1, coords.getCoordY() - 2),
                new Coordinates(coords.getCoordX() - 1, coords.getCoordY() - 2),
                new Coordinates(coords.getCoordX() - 2, coords.getCoordY() - 1),
                new Coordinates(coords.getCoordX() - 2, coords.getCoordY() + 1)
        );

        matrix.forEach(coords -> {
            if (coords.getCoordX() >= 0 && coords.getCoordX() < 8 && coords.getCoordY() >= 0 && coords.getCoordY() < 8 && (board.whoIsOnCoords(coords) == null || board.isEnemyOnCoords(coords, this.color))) {
                moves.add(coords);
            }
        });

        return moves;
    }

    @Override
    public URL getImageUrl() {
        String name = String.format("%s-knight.png", isWhite() ? "white" : "black");
        return ClassLoader.getSystemResource(name);
    }
}
