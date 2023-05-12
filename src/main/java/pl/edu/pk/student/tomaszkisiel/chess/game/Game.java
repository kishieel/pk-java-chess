package pl.edu.pk.student.tomaszkisiel.chess.game;

import pl.edu.pk.student.tomaszkisiel.chess.piece.*;
import pl.edu.pk.student.tomaszkisiel.chess.ui.Chessboard;
import pl.edu.pk.student.tomaszkisiel.chess.utils.Coordinates;

import java.awt.*;

public class Game {
    private final PieceRepository pieceRepository = PieceRepository.getInstance();

    public Game() {
        spawnPieces();
        new Chessboard();
    }

    private void spawnPieces() {
        for (int i = 0; i < 8; i++) {
            pieceRepository.add(new Pawn(Color.WHITE, new Coordinates(i, 6), pieceRepository));
            pieceRepository.add(new Pawn(Color.BLACK, new Coordinates(i, 1), pieceRepository));
        }

        pieceRepository.add(new Rook(Color.BLACK, new Coordinates(0, 0), pieceRepository));
        pieceRepository.add(new Rook(Color.BLACK, new Coordinates(7, 0), pieceRepository));
        pieceRepository.add(new Rook(Color.WHITE, new Coordinates(0, 7), pieceRepository));
        pieceRepository.add(new Rook(Color.WHITE, new Coordinates(7, 7), pieceRepository));
        pieceRepository.add(new Knight(Color.BLACK, new Coordinates(1, 0), pieceRepository));
        pieceRepository.add(new Knight(Color.BLACK, new Coordinates(6, 0), pieceRepository));
        pieceRepository.add(new Knight(Color.WHITE, new Coordinates(1, 7), pieceRepository));
        pieceRepository.add(new Knight(Color.WHITE, new Coordinates(6, 7), pieceRepository));
        pieceRepository.add(new Bishop(Color.BLACK, new Coordinates(2, 0), pieceRepository));
        pieceRepository.add(new Bishop(Color.BLACK, new Coordinates(5, 0), pieceRepository));
        pieceRepository.add(new Bishop(Color.WHITE, new Coordinates(2, 7), pieceRepository));
        pieceRepository.add(new Bishop(Color.WHITE, new Coordinates(5, 7), pieceRepository));
        pieceRepository.add(new Queen(Color.BLACK, new Coordinates(3, 0), pieceRepository));
        pieceRepository.add(new Queen(Color.WHITE, new Coordinates(3, 7), pieceRepository));
        pieceRepository.add(new King(Color.BLACK, new Coordinates(4, 0), pieceRepository));
        pieceRepository.add(new King(Color.WHITE, new Coordinates(4, 7), pieceRepository));
    }
}
