package src.pieces;

import src.structure.*;

/**
 * This is the class for the Pawn piece, and it extends the class Piece
 *
 * @author Muskan Burman
 * @author Magdi Aref
 */
public class PawnPiece extends Piece{

    //Is true if the last move was 2 spaces, false otherwise
    public boolean doubleJump;

    //the piece the pawn will be promoted to 
    public PawnPiece() {
        isFirstMove = true;
        doubleJump = false;
    }

    @Override
    public boolean isValidMove(Point currentPos, Point newPos, Board board) {

        Square currentPosSquare = board.square[currentPos.getY()][currentPos.getX()];
        Square newPosSquare = board.square[newPos.getY()][newPos.getX()];


        //Number of spaces moved in X and Y direction
        int numSpacesX = Math.abs(currentPos.getX() - newPos.getX());
        int numSpacesY;

        //Check to make sure that the piece is not taking place of a piece of its own kind
        if(newPosSquare != null && currentPosSquare.player.equals(newPosSquare.player)) {
            enpassant = '0';
            return false;
        }
        
        //Moving up the white's piece
        if(currentPosSquare.player.equals("w"))
            numSpacesY = currentPos.getY() - newPos.getY();

        //Moving down the Black's piece
        else
            numSpacesY = newPos.getY() - currentPos.getY();

        //Check to if space is empty/avalaible to move up/down
        if(numSpacesX == 0 && newPosSquare == null) {

            //Moving up/down 1 space
            if(numSpacesY == 1) {
                isFirstMove = false;

                //Check for promotion
                if(currentPosSquare.player.equals("w") && newPos.getY() == 0 ||
                   currentPosSquare.player.equals("b") && newPos.getY() == 8)
                    board.square[currentPos.getY()][currentPos.getX()] = new Square(parsePromotion(), currentPosSquare.player);

                enpassant = '0';
                return true;
            }

            //Moving up/down 2 spaces, check if first move
            if(numSpacesY == 2 && isFirstMove == true) {

                //Check if path is clear
                int midY = (currentPos.getY() + newPos.getY()) / 2;
                if(board.square[midY][currentPos.getX()] == null) {
                    isFirstMove = false;
                    doubleJump = true;
                    enpassant = '0';
                    return true;
                }

            }
        }


        //Diagonal capture
        if(numSpacesX == 1 && numSpacesY == 1) {

            //Check to make sure trying to capture opponents piece and not landing on their own
            if(newPosSquare != null && !currentPosSquare.player.equals(newPosSquare.player)) {

                //Check for promotion
                if(currentPosSquare.player.equals("w") && newPos.getY() == 0 ||
                   currentPosSquare.player.equals("b") && newPos.getY() == 7)
                    board.square[currentPos.getY()][currentPos.getX()] = new Square(parsePromotion(), currentPosSquare.player);

                enpassant = '0';
                return true;
            }

            //En passant
            Square adjacentSquare = board.square[currentPos.getY()][newPos.getX()];
            if(adjacentSquare != null &&
               !adjacentSquare.player.equals(currentPosSquare.player) &&
               adjacentSquare.piece instanceof PawnPiece &&
                    ((PawnPiece) adjacentSquare.piece).doubleJump) {

                enpassant = '1';
                return true;
            }
        }


        enpassant = '0';
        return false;
    }



    /**
     * Checks the promotion field and returns the appropriate piece
     *
     * @return The piece to promote the Pawn to (Rook, Knight, Bishop, or Queen)
     */
    public Piece parsePromotion() {

        switch(promotion) {
            case 'R': return new RookPiece();
            case 'N': return new KnightPiece();
            case 'B': return new BishopPiece();
            default: return new QueenPiece();
        }
    }
    

}