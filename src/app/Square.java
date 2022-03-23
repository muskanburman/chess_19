package src.app;

import src.pieces.*;

/**
 * Represents the current state of each square on the 8x8 chess board
 *
 * @author Muskan Burman
 * @author Magdi Aref
 */
public class Square {

    /**
     * Chess piece on the square
     */
    public Piece piece;
    /**
     * The player (white or black) who owns the piece on the square
     */
    public String player;

    /**
     * Constructor
     *
     * @param p Piece
     * @param pl Player color
     */
    public Square(Piece p, String pl) {
        this.piece = p;
        this.player = pl;
    }

    /**
     * Overridden toString method
     *
     * @return The formatted color and chess piece (IE white's king: wK)
     */
    @Override
    public String toString() {
        if(piece instanceof KingPiece) return player + 'K';
        if(piece instanceof QueenPiece) return player + 'Q';
        if(piece instanceof RookPiece) return player + 'R';
        if(piece instanceof KnightPiece) return player + 'N';
        if(piece instanceof BishopPiece) return player + 'B';
        if(piece instanceof PawnPiece) return player + 'p';
        else return "  ";
    }
}

