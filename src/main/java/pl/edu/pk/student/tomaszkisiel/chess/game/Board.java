package pl.edu.pk.student.tomaszkisiel.chess.game;

import pl.edu.pk.student.tomaszkisiel.chess.piece.*;
import pl.edu.pk.student.tomaszkisiel.chess.utils.Coordinates;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Map<Coordinates, Piece> pieces;
    private Color nextPlayer;

    public Board() {
        nextPlayer = Color.WHITE;
        pieces = new HashMap<>();

        Piece piece;
        for (int i = 0; i < 8; i++) {
            piece = new Pawn(Color.WHITE, new Coordinates(i, 6), this);
            pieces.put(piece.getCoords(), piece);

            piece = new Pawn(Color.BLACK, new Coordinates(i, 1), this);
            pieces.put(piece.getCoords(), piece);
        }

        piece = new Rook(Color.BLACK, new Coordinates(0,0), this);
        pieces.put(piece.getCoords(), piece);

        piece = new Rook(Color.BLACK, new Coordinates(7,0), this);
        pieces.put(piece.getCoords(), piece);

        piece = new Rook(Color.WHITE, new Coordinates(0,7), this);
        pieces.put(piece.getCoords(), piece);

        piece = new Rook(Color.WHITE, new Coordinates(7,7), this);
        pieces.put(piece.getCoords(), piece);

        piece = new Knight(Color.BLACK, new Coordinates(1,0), this);
        pieces.put(piece.getCoords(), piece);

        piece = new Knight(Color.BLACK, new Coordinates(6,0), this);
        pieces.put(piece.getCoords(), piece);

        piece = new Knight(Color.WHITE, new Coordinates(1,7), this);
        pieces.put(piece.getCoords(), piece);

        piece = new Knight(Color.WHITE, new Coordinates(6,7), this);
        pieces.put(piece.getCoords(), piece);

        piece = new Bishop(Color.BLACK, new Coordinates(2,0), this);
        pieces.put(piece.getCoords(), piece);

        piece = new Bishop(Color.BLACK, new Coordinates(5,0), this);
        pieces.put(piece.getCoords(), piece);

        piece = new Bishop(Color.WHITE, new Coordinates(2,7), this);
        pieces.put(piece.getCoords(), piece);

        piece = new Bishop(Color.WHITE, new Coordinates(5,7), this);
        pieces.put(piece.getCoords(), piece);

        piece = new Queen(Color.BLACK, new Coordinates(3,0), this);
        pieces.put(piece.getCoords(), piece);

        piece = new Queen(Color.WHITE, new Coordinates(3,7), this);
        pieces.put(piece.getCoords(), piece);

        piece = new King(Color.BLACK, new Coordinates(4,0), this);
        pieces.put(piece.getCoords(), piece);

        piece = new King(Color.WHITE, new Coordinates(4,7), this);
        pieces.put(piece.getCoords(), piece);

    }

    public Map<Coordinates, Piece> getPieces() {
        return pieces;
    }

    public Piece whoIsOnCoords(Coordinates coords) {
        return pieces.get(coords);
    }

    public boolean isEnemyOnCoords(Coordinates coords, Color myColor) {
        Piece piece = pieces.get(coords);
        if (piece == null) return false;
        return piece.getColor() != myColor;
    }

    public boolean isFriendOnCoords(Coordinates coords, Color myColor) {
        Piece piece = pieces.get(coords);
        if (piece == null) return false;
        return piece.getColor() == myColor;
    }

    public Color whoMovesNext() {
        return nextPlayer;
    }

    public void switchNextPlayer() {
        nextPlayer = nextPlayer == Color.WHITE ? Color.BLACK : Color.WHITE;
    }
}
