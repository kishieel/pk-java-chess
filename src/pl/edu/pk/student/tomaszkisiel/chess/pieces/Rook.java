package pl.edu.pk.student.tomaszkisiel.chess.pieces;

public class Rook extends Piece {
    public List<Coordinates> getAllowedMoves() {
        List moves = new ArrayList<Coordinates>();
        Coordinates nextCoords;

        List matrix = List.of(
            [coords.getCoordX() - 1, coords.getCoordY(), -1, 0],
            [coords.getCoordX() + 1, coords.getCoordY(), +1, 0],
            [coords.getCoordX(), coords.getCoordY() - 1, 0, -1],
            [coords.getCoordX(), coords.getCoordY() + 1, 0, +1],
        );

        matrix.stream().forEach(arr -> {
            for (int x = arr[0], y = arr[1]; x >= 0 && x <= 7 && y >= 0 && y <= 7; x += arr[2], y += arr[3]) {
                nextCoords = new Coordinates(x, coords.getCoordY());
                if (board.whoIsOnCoords(nextCoords) == null) {
                    moves.add(nextCoords);
                    continue;
                }
                if (board.whoIsOnCoords(nextCoords).getColor() != this.color) {
                    moves.add(nextCoords);
                    break;
                }
                if (board.whoIsOnCoords(nextCoords).getColor() == this.color) {
                    break;
                }
            }
        })

        return moves;
    }
}