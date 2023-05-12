package pl.edu.pk.student.tomaszkisiel.chess.ui;

import pl.edu.pk.student.tomaszkisiel.chess.game.GameManager;
import pl.edu.pk.student.tomaszkisiel.chess.game.PieceRepository;
import pl.edu.pk.student.tomaszkisiel.chess.piece.King;
import pl.edu.pk.student.tomaszkisiel.chess.piece.Piece;
import pl.edu.pk.student.tomaszkisiel.chess.utils.Coordinates;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Chessboard implements MouseListener {
    private final JFrame window;
    private final PieceRepository pieceRepository = PieceRepository.getInstance();
    private final GameManager gameManager = GameManager.getInstance();
    private final Map<Coordinates, JPanel> panels;
    private JPanel panelHighlighted;

    public Chessboard() {
        this.panels = new HashMap<>();
        this.window = new JFrame("Chess");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new GridLayout(8, 8));
        this.drawChessboard();
        this.redrawPieces();
        window.pack();
        window.setVisible(true);
    }

    private void drawChessboard() {
        for (int i = 0; i < 64; i++) {
            JPanel panel = new JPanel();
            panel.addMouseListener(this);
            panel.setPreferredSize(new Dimension(64, 64));
            Color color = (i + (i / 8)) % 2 == 0 ? Color.WHITE : Color.BLACK;
            panel.setBackground(color);
            Coordinates coords = new Coordinates(i % 8, (i / 8));
            panels.put(coords, panel);
            window.add(panel);
        }
    }

    private void redrawChessboard() {
        for (int i = 0; i < 64; i++) {
            Coordinates coords = new Coordinates(i % 8, (i / 8));
            JPanel panel = panels.get(coords);
            Color color = (i + (i / 8)) % 2 == 0 ? Color.WHITE : Color.BLACK;
            panel.setBackground(color);
        }
        if (gameManager.isKingChecked()) {
            Optional<King> king = pieceRepository.getByTypeAndColor(King.class, gameManager.getCurrentPlayer());
            if (king.isEmpty() || !king.get().getColor().equals(gameManager.getCurrentPlayer())) return;
            panels.get(king.get().getCoords()).setBackground(Color.RED);
        }
        window.revalidate();
    }

    public void redrawPieces() {
        pieceRepository.getAll().forEach((piece) -> {
            try {
                URL url = piece.getImageUrl();
                Image image = ImageIO.read(url).getScaledInstance(56, 56, Image.SCALE_AREA_AVERAGING);
                JLabel label = new JLabel(new ImageIcon(image));
                JPanel panel = panels.get(piece.getCoords());
                if (panel != null) panel.add(label);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
        window.revalidate();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        this.redrawChessboard();
        if (panelHighlighted == null) {
            onPanelHighlight(mouseEvent);
            window.revalidate();
            return;
        }

        JPanel panel = (JPanel) mouseEvent.getComponent();
        Optional<Piece> piece = pieceRepository.getByCoords(new Coordinates(panel.getX() / 64, panel.getY() / 64));
        if (piece.isPresent() && piece.get().getColor().equals(gameManager.getCurrentPlayer())) {
            onPanelHighlight(mouseEvent);
            window.revalidate();
            return;
        }

        onPanelSelect(mouseEvent);
        window.revalidate();
    }

    private void onPanelHighlight(MouseEvent mouseEvent) {
        panelHighlighted = (JPanel) mouseEvent.getComponent();
        Optional<Piece> piece = pieceRepository.getByCoords(new Coordinates(
                panelHighlighted.getX() / 64,
                panelHighlighted.getY() / 64
        ));
        if (piece.isEmpty() || piece.get().isEnemy(gameManager.getCurrentPlayer())) {
            panelHighlighted = null;
            return;
        }
        panelHighlighted.setBackground(Color.BLUE);

        if (gameManager.isKingChecked() && !piece.get().getClass().equals(King.class)) return;

        List<Coordinates> nextAllowedCoords = piece.get().getNextAllowedCoords();

        if (piece.get().getClass().equals(King.class)) {
            nextAllowedCoords.removeAll(gameManager.getCheckedCoords());
        }

        nextAllowedCoords.forEach((Coordinates coords) -> {
            panels.get(coords).setBackground(Color.GREEN);
        });
    }

    private void onPanelSelect(MouseEvent mouseEvent) {
        JPanel panel = (JPanel) mouseEvent.getComponent();
        Coordinates nextMove = new Coordinates(panel.getX() / 64, panel.getY() / 64);
        Optional<Piece> piece = pieceRepository.getByCoords(new Coordinates(
                panelHighlighted.getX() / 64,
                panelHighlighted.getY() / 64
        ));
        if (piece.isEmpty()) return;
        if (gameManager.isKingChecked() && !piece.get().getClass().equals(King.class)) return;
        List<Coordinates> allowedMoves = piece.get().getNextAllowedCoords();
        if (piece.get().getClass().equals(King.class)) {
            allowedMoves.removeAll(gameManager.getCheckedCoords());
        }
        if (!allowedMoves.contains(nextMove)) {
            panelHighlighted = null;
            return;
        }
        panelHighlighted.removeAll();
        panel.removeAll();

        Optional<Piece> enemy = pieceRepository.getByCoords(nextMove);
        enemy.ifPresent(pieceRepository::remove);

        piece.get().setCoords(nextMove);

        gameManager.switchPlayer();
        panelHighlighted = null;

        if (gameManager.isKingChecked()) {
            Optional<King> king = pieceRepository.getByTypeAndColor(King.class, gameManager.getCurrentPlayer());
            if (king.isEmpty()) return;
            JPanel panelDangered = panels.get(king.get().getCoords());
            panelDangered.setBackground(Color.RED);

            List<Coordinates> nextAllowedCoords = king.get().getNextAllowedCoords();
            nextAllowedCoords.removeAll(gameManager.getCheckedCoords());
            if (nextAllowedCoords.size() == 0) {
                gameManager.switchPlayer();
                System.out.println("Winner: " + (gameManager.getCurrentPlayer() == Color.WHITE ? "WHITE" : "BLACK"));
                System.exit(0);
            }
        }

        this.redrawChessboard();
        this.redrawPieces();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {}

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {}

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {}

    @Override
    public void mouseExited(MouseEvent mouseEvent) {}

}
