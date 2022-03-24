package src.structure;

import src.pieces.*;

/**
 * Represents the current state of each square on the 8x8 chess board
 *
 * @author Muskan Burman
 * @author Magdi Aref
 */
public class Spot {

    /**
     * Chess piece on the square
     */
    public Piece piece;
    /**
     * Prints player color of player who owns the piece 
     */
    public String player;

    /**
     * Constructor
     *
     * @param p Piece
     * @param pl Color of player
     */
    public Spot(Piece p, String pl) {
        this.piece = p;
        this.player = pl;
    }

    /**
     * Overridden toString method
     *
     * @return The player color and chess piece  ex. white's king: wK
     */
    @Override
    public String toString() {
        if(piece instanceof KingPiece) return player + 'K';
        if(piece instanceof QueenPiece) return player + 'Q';
        if(piece instanceof RookPiece) return player + 'R';
        if(piece instanceof KnightPiece) return player + 'N';
        if(piece instanceof BishopPiece) return player + 'B';
        if(piece instanceof PawnPiece) return player + 'P';
        else return "  ";
    }
}

