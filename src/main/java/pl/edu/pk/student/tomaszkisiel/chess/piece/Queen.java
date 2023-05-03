package pl.edu.pk.student.tomaszkisiel.chess.piece;

import pl.edu.pk.student.tomaszkisiel.chess.game.Board;
import pl.edu.pk.student.tomaszkisiel.chess.utils.Coordinates;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    public Queen(Color color, Coordinates coords, Board board) {
        super(color, coords, board);
    }

    @Override
    public List<Coordinates> getAllowedMoves() {

        List<Coordinates> moves = new ArrayList<>();

        List<int[]> matrix = List.of(
                new int[]{coords.getCoordX() - 1, coords.getCoordY() - 1, -1, -1},
                new int[]{coords.getCoordX() + 1, coords.getCoordY() - 1, +1, -1},
                new int[]{coords.getCoordX() - 1, coords.getCoordY() + 1, -1, +1},
                new int[]{coords.getCoordX() + 1, coords.getCoordY() + 1, +1, +1},
                new int[]{coords.getCoordX() - 1, coords.getCoordY(), -1, 0},
                new int[]{coords.getCoordX() + 1, coords.getCoordY(), +1, 0},
                new int[]{coords.getCoordX(), coords.getCoordY() - 1, 0, -1},
                new int[]{coords.getCoordX(), coords.getCoordY() + 1, 0, +1}
        );

        matrix.forEach((arr) -> {
            Coordinates nextCoords;
            for (int x = arr[0], y = arr[1]; x >= 0 && x <= 7 && y >= 0 && y <= 7; x += arr[2], y += arr[3]) {
                nextCoords = new Coordinates(x, y);
                if (board.whoIsOnCoords(nextCoords) == null) {
                    moves.add(nextCoords);
                    continue;
                }
                if (board.whoIsOnCoords(nextCoords).getColor() != this.color) {
                    moves.add(nextCoords);
                    break;
                }
                if (board.whoIsOnCoords(nextCoords).getColor() == this.color) {
                    break;
                }
            }
        });

        return moves;
    }

    @Override
    public URL getImageUrl() {
        String name = String.format("%s-queen.png", isWhite() ? "white" : "black");
        return ClassLoader.getSystemResource(name);
    }
}
