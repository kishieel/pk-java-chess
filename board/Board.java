package pl.edu.pk.student.tomaszkisiel.chess.board;

public class Board {
    public static int MIN_X = 1;
    public static int MIN_Y = 1;
    public static int MAX_X = 7;
    public static int MAX_Y = 7;

    private Map<Coordinates, Piece> pieces;

    public class Board() {
        this.init();
    }

    private void init() {
        Piece piece;

        // pawns
        for (int i = Board.MIN_X; i < Board.MAX_X; i++) {
            piece = new Pawn(Color.WHITE, new Coordinates(i, 1), this);
            pieces.put(piece.getCoords(), piece);
            
            piece = new Pawn(Color.BLACK, new Coordinates(i, 6), this);
            pieces.put(piece.getCoords(), piece);
        }

        // rooks
        piece = new Rook(Color.WHITE, new Coordinates(0,0), this);
        pieces.put(piece.getCoords(), piece);

        piece = new Rook(Color.WHITE, new Coordinates(7,0), this);
        pieces.put(piece.getCoords(), piece);

        piece = new Rook(Color.BLACK, new Coordinates(0,7), this);
        pieces.put(piece.getCoords(), piece);

        piece = new Rook(Color.BLACK, new Coordinates(7,7), this);
        pieces.put(piece.getCoords(), piece);

        // knights
        piece = new Knight(Color.WHITE, new Coordinates(1,0), this);
        pieces.put(piece.getCoords(), piece);

        piece = new Knight(Color.WHITE, new Coordinates(6,0), this);
        pieces.put(piece.getCoords(), piece);

        piece = new Knight(Color.BLACK, new Coordinates(1,7), this);
        pieces.put(piece.getCoords(), piece);

        piece = new Knight(Color.BLACK, new Coordinates(6,7), this);
        pieces.put(piece.getCoords(), piece);

        // bishops
        piece = new Bishop(Color.WHITE, new Coordinates(2,0), this);
        pieces.put(piece.getCoords(), piece);

        piece = new Bishop(Color.WHITE, new Coordinates(5,0), this);
        pieces.put(piece.getCoords(), piece);

        piece = new Bishop(Color.BLACK, new Coordinates(2,7), this);
        pieces.put(piece.getCoords(), piece);

        piece = new Bishop(Color.BLACK, new Coordinates(5,7), this);
        pieces.put(piece.getCoords(), piece);

        // queens
        piece = new Queen(Color.WHITE, new Coordinates(3,0), this);
        pieces.put(piece.getCoords(), piece);

        piece = new Queen(Color.BLACK, new Coordinates(3,7), this);
        pieces.put(piece.getCoords(), piece);

        // queens
        piece = new King(Color.WHITE, new Coordinates(4,0), this);
        pieces.put(piece.getCoords(), piece);

        piece = new King(Color.BLACK, new Coordinates(4,7), this);
        pieces.put(piece.getCoords(), piece);
    }

    public void play() {

    }

    public Color whoIsOnCoords(Coordinates coords) {
        Piece piece = pieces.get(coords);
        if (piece != null) piece.getColor();
        return null;
    }
}
