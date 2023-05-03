package pl.edu.pk.student.tomaszkisiel.chess.ui;

import pl.edu.pk.student.tomaszkisiel.chess.game.Board;
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

public class Chessboard implements MouseListener {
    private final JFrame window;
    private final Map<Coordinates, JPanel> panels;
    private final Board board;
    private JPanel panelHighlighted;

    public Chessboard(Board board) {
        this.panels = new HashMap<>();
        this.board = board;
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
        window.revalidate();
    }

    public void redrawPieces() {
        board.getPieces().forEach((Coordinates coords, Piece piece) -> {
            try {
                URL url = piece.getImageUrl();
                Image image = ImageIO.read(url).getScaledInstance(56, 56, Image.SCALE_AREA_AVERAGING);
                JLabel label = new JLabel(new ImageIcon(image));
                JPanel panel = panels.get(coords);
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
        Piece piece = board.whoIsOnCoords(new Coordinates(panel.getX() / 64, panel.getY() / 64));
        if (piece != null && piece.getColor() == board.whoMovesNext()) {
            onPanelHighlight(mouseEvent);
            window.revalidate();
            return;
        }

        onPanelSelect(mouseEvent);
        window.revalidate();
    }

    private void onPanelHighlight(MouseEvent mouseEvent) {
        panelHighlighted = (JPanel) mouseEvent.getComponent();
        Piece piece = board.whoIsOnCoords(new Coordinates(panelHighlighted.getX() / 64, panelHighlighted.getY() / 64));
        if (piece == null || board.whoMovesNext() != piece.getColor()) {
            panelHighlighted = null;
            return;
        }
        panelHighlighted.setBackground(Color.BLUE);
        piece.getAllowedMoves().forEach((Coordinates coords) -> {
            panels.get(coords).setBackground(Color.GREEN);
        });
    }

    private void onPanelSelect(MouseEvent mouseEvent) {
        JPanel panel = (JPanel) mouseEvent.getComponent();
        Coordinates nextMove = new Coordinates(panel.getX() / 64, panel.getY() / 64);
        Piece piece = board.whoIsOnCoords(new Coordinates(panelHighlighted.getX() / 64, panelHighlighted.getY() / 64));
        List<Coordinates> allowedMoves = piece.getAllowedMoves();
        if (!allowedMoves.contains(nextMove)) {
            panelHighlighted = null;
            return;
        }
        panelHighlighted.removeAll();
        panel.removeAll();
        board.getPieces().remove(piece.getCoords());
        piece.setCoords(nextMove);
        Piece enemy = board.getPieces().get(nextMove);
        board.getPieces().remove(nextMove);
        board.getPieces().put(nextMove, piece);
        if (enemy instanceof King && enemy.getColor() != board.whoMovesNext()) {
            String winner = board.whoMovesNext() == Color.WHITE ? "white" : "black";
            String message = String.format("Checkmate - %s won!", winner);
            System.out.println(message);
            System.exit(0);
        }
        board.switchNextPlayer();
        panelHighlighted = null;
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
