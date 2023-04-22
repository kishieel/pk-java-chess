package pl.edu.pk.student.tomaszkisiel.chess.pieces;

package pl.edu.pk.student.tomaszkisiel.chess.utils.Coordinates;

public class Pawn extends Piece {
    public List<Coordinates> getAllowedMoves() {
        List moves = new ArrayList<Coordinates>();
        Coordinates nextCoords;

        if (isWhite() && coords.getCoordY < Board.MAX_Y) {
            nextCoords = new Coordinates(coords.getCoordX(), coords.getCoordY() + 1);            
            if (nextCoords.getCoordY() < Board.MAX_Y && board.whoIsOnCoords(nextCoords) == null) moves.add(nextCoords);

            nextCoords = new Coordinates(coords.getCoordX(), coords.getCoordY() + 2);
            if (isOnStartPostion && board.whoIsOnCoords(nextCoords) == null) moves.add(nextCoords);
        
            nextCoords = new Coordinates(coords.getCoordX() - 1, coords.getCoordY() + 1);
            if (
                nextCoords.getCoordX() > Board.MIN_X && nextCoords.getCoordY() < Board.MAX_Y && 
                board.whoIsOnCoords(nextCoords) instanceof Piece && board.whoIsOnCoords(nextCoords).getColor() != this.color
            ) moves.add(nextCoords);

            nextCoords = new Coordinates(coords.getCoordX() + 1, coords.getCoordY() + 1);
            if (
                nextCoords.getCoordX() < Board.MAX_X && nextCoords.getCoordY() < Board.MAX_Y && 
                board.whoIsOnCoords(nextCoords) instanceof Piece && board.whoIsOnCoords(nextCoords).getColor() != this.color
            ) moves.add(nextCoords);
        } else if (isBlack() && coords.getCoordY > Board.MIN_Y) {
            nextCoords = new Coordinates(coords.getCoordX(), coords.getCoordY() - 1);            
            if (nextCoords.getCoordY() > Board.MIN_Y && Board.whoIsOnCoords(nextCoords) == null) moves.add(nextCoords);

            nextCoords = new Coordinates(coords.getCoordX(), coords.getCoordY() - 2);
            if (isOnStartPostion && Board.whoIsOnCoords(nextCoords) == null) moves.add(nextCoords);

            nextCoords = new Coordinates(coords.getCoordX() - 1, coords.getCoordY() - 1);
            if (
                nextCoords.getCoordX() > Board.MIN_X && nextCoords.getCoordY() > Board.MIN_Y && 
                board.whoIsOnCoords(nextCoords) instanceof Piece && board.whoIsOnCoords(nextCoords).getColor() != this.color
            ) moves.add(nextCoords);

            nextCoords = new Coordinates(coords.getCoordX() + 1, coords.getCoordY() - 1);
            if (
                nextCoords.getCoordX() < Board.MAX_X && nextCoords.getCoordY() > Board.MIN_Y && 
                board.whoIsOnCoords(nextCoords) instanceof Piece && board.whoIsOnCoords(nextCoords).getColor() != this.color
            ) moves.add(nextCoords);
        }

        return moves;
    }

    private boolean isOnStartPostion() {
        return (isWhite() && coords.getCoordY() == 1) || (isBlack() && coords.getCoordY() == 6);
    }
}