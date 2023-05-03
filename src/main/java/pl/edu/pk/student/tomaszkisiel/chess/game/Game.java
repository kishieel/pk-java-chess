package pl.edu.pk.student.tomaszkisiel.chess.game;

import pl.edu.pk.student.tomaszkisiel.chess.ui.Chessboard;

public class Game {
    public Game() {
        Board board = new Board();
        Chessboard chessboard = new Chessboard(board);
    }
}
