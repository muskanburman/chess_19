package src.pieces;

import src.structure.*;

/**
 * This class is the basis/parent class for all the other pieces' classes.
 *
 * @author Muskan Burman
 * @author Magdi Aref
 */
public abstract class Piece {
    /**
     * Is true if the first move has been made by a piece and false otherwise.
     */
    public boolean isFirstMove;
    /**
     * For pawn promotion 
     */
    public char promotion;
    /**
     * For en passant 
     */
    public char enpassant;
    /**
     * For left and right castling 
     */
    public char castling;

    /**
     * This method checks if the move made by a piece is a valid/legitimate move for that piece. 
     * This method is abstract because it later gets called on by each piece separately, as needed.
     *
     * @param currentPos  the current position of the piece
     * @param newPos  the new position of the piece
     * @param board  the chess board
     * @return true if the move made by the piece from the current position to the new position is legal, false otherwise
     */
    public abstract boolean isValidMove(Point currentPos, Point newPos, Board board);
}
