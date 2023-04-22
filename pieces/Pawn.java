package pl.edu.pk.student.tomaszkisiel.chess.pieces;

package pl.edu.pk.student.tomaszkisiel.chess.utils.Coordinates;

public class Pawn extends Piece {
    public List<Coordinates> getAllowedMoves() {
        List moves = new ArrayList<Coordinates>();

        if (isWhite() && coords.getCoordY < Board.MAX_Y) {
            moves.add(coords.getCoordY() + 1);
            if (isOnStartPostion) moves.add(coords.getCoordY() + 2);
        } else if (isBlack() && coords.getCoordY > Board.MIN_Y) {
            moves.add(coords.getCoordY() - 1);
            if (isOnStartPostion) moves.add(coords.getCoordY() - 2);
        }

        return moves;
    }

    private boolean isOnStartPostion() {
        return (isWhite() && coords.getCoordY() == 1) || (isBlack() && coords.getCoordY() == 6);
    }
}