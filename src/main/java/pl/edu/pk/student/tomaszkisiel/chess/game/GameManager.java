package pl.edu.pk.student.tomaszkisiel.chess.game;

import pl.edu.pk.student.tomaszkisiel.chess.piece.King;
import pl.edu.pk.student.tomaszkisiel.chess.piece.Piece;
import pl.edu.pk.student.tomaszkisiel.chess.utils.Coordinates;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class GameManager {
    private static GameManager instance = null;
    private final PieceRepository pieceRepository = PieceRepository.getInstance();

    private Color currentPlayer = Color.WHITE;

    private GameManager() {}

    public static GameManager getInstance() {
        if (instance == null) instance = new GameManager();
        return instance;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = currentPlayer == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    public List<Coordinates> getCheckedCoords() {
        List<Piece> enemies = pieceRepository.getAll();
        List<Coordinates> coordsChecked = new ArrayList<>();
        enemies.stream().filter(enemy -> enemy.isEnemy(currentPlayer)).forEach(enemy -> coordsChecked.addAll(enemy.getNextAllowedCoords()));
        return coordsChecked;
    }

    public boolean isKingChecked() {
        Optional<King> king = pieceRepository.getByTypeAndColor(King.class, currentPlayer);
        if (king.isEmpty()) return false;
        return getCheckedCoords().contains(king.get().getCoords());
    }
}
