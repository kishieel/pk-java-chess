package pl.edu.pk.student.tomaszkisiel.chess.game;

import pl.edu.pk.student.tomaszkisiel.chess.piece.Piece;
import pl.edu.pk.student.tomaszkisiel.chess.utils.Coordinates;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PieceRepository {
    private static PieceRepository instance = null;
    private final List<Piece> pieces = new ArrayList<>();

    public static PieceRepository getInstance() {
        if (instance == null) instance = new PieceRepository();
        return instance;
    }

    public void add(Piece piece) {
        pieces.add(piece);
    }

    public void remove(Piece piece) {
        pieces.remove(piece);
    }

    public List<Piece> getAll() {
        return pieces;
    }

    public <T extends Piece> Optional<T> getByTypeAndColor(Class<T> type, Color color) {
        Optional<Piece> piece = pieces.stream()
                .filter(p -> type.equals(p.getClass()) && p.getColor().equals(color))
                .findFirst();

        if (piece.isEmpty()) return Optional.empty();
        return Optional.of(type.cast(piece.get()));
    }

    public Optional<Piece> getByCoords(Coordinates coords) {
        return pieces.stream()
                .filter((piece) -> piece.getCoords().equals(coords))
                .findFirst();
    }


    public boolean isEnemyOrNobodyOnCoords(Coordinates coordinates, Color myColor) {
        Optional<Piece> piece = getByCoords(coordinates);
        return piece.isEmpty() || !piece.get().getColor().equals(myColor);
    }

    public boolean isEnemyOnCoords(Coordinates coordinates, Color myColor) {
        Optional<Piece> piece = getByCoords(coordinates);
        return piece.isPresent() && !piece.get().getColor().equals(myColor);
    }

    public boolean isFriendOnCoords(Coordinates coordinates, Color myColor) {
        Optional<Piece> piece = getByCoords(coordinates);
        return piece.isPresent() && piece.get().getColor().equals(myColor);
    }
}
