package pl.edu.pk.student.tomaszkisiel.chess.piece;


import pl.edu.pk.student.tomaszkisiel.chess.game.PieceRepository;
import pl.edu.pk.student.tomaszkisiel.chess.utils.Coordinates;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    public Pawn(Color color, Coordinates coords, PieceRepository repository) {
        super(color, coords, repository);
    }

    public List<Coordinates> getNextAllowedCoords() {
        List<Coordinates> moves = new ArrayList<>();
        Coordinates nextCoords;

        if (isBlack() && coords.getY() < 8) {
            nextCoords = new Coordinates(coords.getX(), coords.getY() + 1);
            if (nextCoords.getY() < 8 && repository.getByCoords(nextCoords).isEmpty()) moves.add(nextCoords);

            nextCoords = new Coordinates(coords.getX(), coords.getY() + 2);
            if (isOnStartPosition() && repository.getByCoords(nextCoords).isEmpty()) moves.add(nextCoords);

            nextCoords = new Coordinates(coords.getX() - 1, coords.getY() + 1);
            if (repository.isEnemyOnCoords(nextCoords, this.color)) {
                moves.add(nextCoords);
            }

            nextCoords = new Coordinates(coords.getX() + 1, coords.getY() + 1);
            if (repository.isEnemyOnCoords(nextCoords, this.color)) {
                moves.add(nextCoords);
            }
        } else if (isWhite() && coords.getY() > 0) {
            nextCoords = new Coordinates(coords.getX(), coords.getY() - 1);
            if (nextCoords.getY() > 0 && repository.getByCoords(nextCoords).isEmpty()) moves.add(nextCoords);

            nextCoords = new Coordinates(coords.getX(), coords.getY() - 2);
            if (isOnStartPosition() && repository.getByCoords(nextCoords).isEmpty()) moves.add(nextCoords);

            nextCoords = new Coordinates(coords.getX() - 1, coords.getY() - 1);
            if (repository.isEnemyOnCoords(nextCoords, color)) {
                moves.add(nextCoords);
            }

            nextCoords = new Coordinates(coords.getX() + 1, coords.getY() - 1);
            if (repository.isEnemyOnCoords(nextCoords, color)) {
                moves.add(nextCoords);
            }
        }

        return moves;
    }

    @Override
    public URL getImageUrl() {
        String name = String.format("%s-pawn.png", isWhite() ? "white" : "black");
        return ClassLoader.getSystemResource(name);
    }

    private boolean isOnStartPosition() {
        return (isBlack() && coords.getY() == 1) || (isWhite() && coords.getY() == 6);
    }
}