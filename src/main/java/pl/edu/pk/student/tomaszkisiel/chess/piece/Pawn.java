package pl.edu.pk.student.tomaszkisiel.chess.piece;


import pl.edu.pk.student.tomaszkisiel.chess.game.Board;
import pl.edu.pk.student.tomaszkisiel.chess.utils.Coordinates;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(Color color, Coordinates coords, Board board) {
        super(color, coords, board);
    }

    public List<Coordinates> getAllowedMoves() {
        List<Coordinates> moves = new ArrayList<>();
        Coordinates nextCoords;

        if (isBlack() && coords.getCoordY() < 8) {
            nextCoords = new Coordinates(coords.getCoordX(), coords.getCoordY() + 1);
            if (nextCoords.getCoordY() < 8 && board.whoIsOnCoords(nextCoords) == null) moves.add(nextCoords);

            nextCoords = new Coordinates(coords.getCoordX(), coords.getCoordY() + 2);
            if (isOnStartPosition() && board.whoIsOnCoords(nextCoords) == null) moves.add(nextCoords);

            nextCoords = new Coordinates(coords.getCoordX() - 1, coords.getCoordY() + 1);
            if (nextCoords.getCoordX() > 0 && nextCoords.getCoordY() < 8 && board.isEnemyOnCoords(nextCoords,
                                                                                                  this.color))
                moves.add(nextCoords);

            nextCoords = new Coordinates(coords.getCoordX() + 1, coords.getCoordY() + 1);
            if (nextCoords.getCoordX() < 8 && nextCoords.getCoordY() < 8 && board.isEnemyOnCoords(nextCoords,
                                                                                                  this.color))
                moves.add(nextCoords);
        } else if (isWhite() && coords.getCoordY() > 0) {
            nextCoords = new Coordinates(coords.getCoordX(), coords.getCoordY() - 1);
            if (nextCoords.getCoordY() > 0 && board.whoIsOnCoords(nextCoords) == null) moves.add(nextCoords);

            nextCoords = new Coordinates(coords.getCoordX(), coords.getCoordY() - 2);
            if (isOnStartPosition() && board.whoIsOnCoords(nextCoords) == null) moves.add(nextCoords);

            nextCoords = new Coordinates(coords.getCoordX() - 1, coords.getCoordY() - 1);
            if (nextCoords.getCoordX() > 0 && nextCoords.getCoordY() > 0 && board.isEnemyOnCoords(nextCoords,
                                                                                                  this.color))
                moves.add(nextCoords);

            nextCoords = new Coordinates(coords.getCoordX() + 1, coords.getCoordY() - 1);
            if (nextCoords.getCoordX() < 8 && nextCoords.getCoordY() > 0 && board.isEnemyOnCoords(nextCoords,
                                                                                                  this.color))
                moves.add(nextCoords);
        }

        return moves;
    }

    @Override
    public URL getImageUrl() {
        String name = String.format("%s-pawn.png", isWhite() ? "white" : "black");
        return ClassLoader.getSystemResource(name);
    }

    private boolean isOnStartPosition() {
        return (isBlack() && coords.getCoordY() == 1) || (isWhite() && coords.getCoordY() == 6);
    }
}