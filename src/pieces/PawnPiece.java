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

        Spot currentPosSpot = board.spot[currentPos.getY()][currentPos.getX()];
        Spot newPosSpot = board.spot[newPos.getY()][newPos.getX()];


        //Number of spaces moved in X and Y direction
        int numSpacesX = Math.abs(currentPos.getX() - newPos.getX());
        int numSpacesY;

        //Check to make sure that the piece is not taking place of a piece of its own kind
        if(newPosSpot != null && currentPosSpot.player.equals(newPosSpot.player)) {
            enpassant = '0';
            return false;
        }
        
        //Moving up the white's piece
        if(currentPosSpot.player.equals("w"))
            numSpacesY = currentPos.getY() - newPos.getY();

        //Moving down the Black's piece
        else
            numSpacesY = newPos.getY() - currentPos.getY();

        //Check to if space is empty/avaliable to move up/down
        if(numSpacesX == 0 && newPosSpot == null) {

            //Moving up/down 1 space
            if(numSpacesY == 1) {
                isFirstMove = false;

                //Check for promotion
                if(currentPosSpot.player.equals("w") && newPos.getY() == 0 ||
                   currentPosSpot.player.equals("b") && newPos.getY() == 8)
                    board.spot[currentPos.getY()][currentPos.getX()] = new Spot(parsePromotion(), currentPosSpot.player);

                enpassant = '0';
                return true;
            }

            //Moving up/down 2 spaces, check to see if its the first move
            if(numSpacesY == 2 && isFirstMove == true) {

                //Path should be clear
                int midY = (currentPos.getY() + newPos.getY()) / 2;
                if(board.spot[midY][currentPos.getX()] == null) {
                    isFirstMove = false;
                    doubleJump = true;
                    enpassant = '0';
                    return true;
                }

            }
        }


        //capture diagonally
        if(numSpacesX == 1 && numSpacesY == 1) {

            //Check to make sure that the piece is not taking place of a piece of its own kind and is capturing only opponent's piece
            if(newPosSpot != null && !currentPosSpot.player.equals(newPosSpot.player)) {

                //Check for promotion
                if(currentPosSpot.player.equals("w") && newPos.getY() == 0 ||
                   currentPosSpot.player.equals("b") && newPos.getY() == 7)
                    board.spot[currentPos.getY()][currentPos.getX()] = new Spot(parsePromotion(), currentPosSpot.player);

                enpassant = '0';
                return true;
            }

            //En passant
            Spot adjacentspot = board.spot[currentPos.getY()][newPos.getX()];
            if(adjacentspot != null &&
               !adjacentspot.player.equals(currentPosSpot.player) &&
               adjacentspot.piece instanceof PawnPiece &&
                    ((PawnPiece) adjacentspot.piece).doubleJump) {

                enpassant = '1';
                return true;
            }
        }


        enpassant = '0';
        return false;
    }



    /**
     * This method checks which piece the pawn will be promoted to according to input, and returns that piece
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