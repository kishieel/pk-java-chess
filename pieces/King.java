package pl.edu.pk.student.tomaszkisiel.chess.pieces;

package pl.edu.pk.student.tomaszkisiel.chess.utils.Coordinates;

public class King extends Piece {
    private boolean wasMoved = false;

    public List<Coordinates> getAllowedMoves() {
        List moves = new ArrayList<Coordinates>();

        List matix = List.of(
            new Coordinates(coords.getCoordX() - 1, coords.getCoordY()),
            new Coordinates(coords.getCoordX() - 1, coords.getCoordY() + 1),
            new Coordinates(coords.getCoordX(), coords.getCoordY() + 1),
            new Coordinates(coords.getCoordX() + 1, coords.getCoordY() + 1),
            new Coordinates(coords.getCoordX() + 1, coords.getCoordY()),
            new Coordinates(coords.getCoordX() + 1, coords.getCoordY() - 1),
            new Coordinates(coords.getCoordX(), coords.getCoordY() - 1),
            new Coordinates(coords.getCoordX() - 1, coords.getCoordY() - 1),
        );

        matrix.stream().forEach(coords -> {
            if (
                coords.getCoordX() > 0 && coords.getCoordX() < 7 && 
                coords.getCoordY() > 0 && coords.getCoordY() < 7 && 
                ( Board.whoIsOnCoords(coords) == null || Board.whoIsOnCoords(coords).color != this.color )
            ) {
                moves.add(coords);
            }
        });

        // todo: castling
        // todo: king can not move to coords under-attack

        return moves;
    }

    public void markAsMoved() {
        this.wasMoved = true;
    }
}