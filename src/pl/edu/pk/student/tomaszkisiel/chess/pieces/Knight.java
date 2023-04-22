package pl.edu.pk.student.tomaszkisiel.chess.pieces;

import pl.edu.pk.student.tomaszkisiel.chess.utils.Coordinates;

public class Knight extends Piece {
    public List<Coordinates> getAllowedMoves() {
        List moves = new ArrayList<Coordinates>();

        List matix = List.of(
            new Coordinates(coords.getCoordX() - 1, coords.getCoordY() + 2),
            new Coordinates(coords.getCoordX() + 1, coords.getCoordY() + 2),
            new Coordinates(coords.getCoordX() + 2, coords.getCoordY() + 1),
            new Coordinates(coords.getCoordX() + 2, coords.getCoordY() - 1),
            new Coordinates(coords.getCoordX() + 1, coords.getCoordY() - 2),
            new Coordinates(coords.getCoordX() - 1, coords.getCoordY() - 2),
            new Coordinates(coords.getCoordX() - 2, coords.getCoordY() - 1),
            new Coordinates(coords.getCoordX() - 2, coords.getCoordY() + 1)
        );

        matrix.stream().forEach(coords -> {
            if (
                coords.getCoordX() > 0 && coords.getCoordX() < 7 && 
                coords.getCoordY() > 0 && coords.getCoordY() < 7 && 
                ( board.whoIsOnCoords(coords) == null || board.whoIsOnCoords(coords).getColor() != this.color )
            ) {
                moves.add(coords);
            }
        });

        return moves;
    }
}