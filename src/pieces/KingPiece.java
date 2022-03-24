package src.pieces;

import src.structure.*;

/**
 * This is the class for the King piece, and it extends the class Piece
 *
 * @author Muskan Burman
 * @author Magdi Aref
 */
public class KingPiece extends Piece {

    public KingPiece() {
        isFirstMove = true;
        castling = '0';
    }

    @Override
    public boolean isValidMove(Point currentPos, Point newPos, Board board) {

        Spot currentPosSpot = board.spot[currentPos.getY()][currentPos.getX()];
        Spot newPosSpot = board.spot[newPos.getY()][newPos.getX()];

        //Number of spaces moved in X and Y direction
        int numSpacesX = Math.abs(currentPos.getX() - newPos.getX());
        int numSpacesY = Math.abs(currentPos.getY() - newPos.getY());

        //Check to make sure that the piece is not taking place of a piece of its own kind
        if(newPosSpot != null && currentPosSpot.player.equals(newPosSpot.player))
            return false;

        //Move to adjacent spot
        if((numSpacesX == 1 && numSpacesY == 0) ||
           (numSpacesX == 0 && numSpacesY == 1) ||
           (numSpacesX == 1 && numSpacesY == 1)) {
            isFirstMove = false;
            castling = '0';
            return true;
        }


        //Castling
        if(numSpacesX == 2 && numSpacesY == 0 && isFirstMove) {

            Spot leftRookSpot = board.spot[currentPos.getY()][0];
            Spot rightRookSpot = board.spot[currentPos.getY()][7];

            //Left
            if(currentPos.getX() > newPos.getX()) {
                if(leftRookSpot != null &&
                   leftRookSpot.piece instanceof RookPiece &&
                   leftRookSpot.piece.isFirstMove &&
                   board.spot[currentPos.getY()][currentPos.getX()].player.equals(leftRookSpot.player)) {

                    //Path should be clear
                    for(int i = currentPos.getX() - 1; i > 0; i--)
                        if(board.spot[currentPos.getY()][i] != null)
                            return false;


                    castling = 'l';
                    return true;
                }
            }

            //Right
            else {
                if(rightRookSpot != null &&
                   rightRookSpot.piece instanceof RookPiece &&
                   rightRookSpot.piece.isFirstMove &&
                   board.spot[currentPos.getY()][currentPos.getX()].player.equals(rightRookSpot.player)) {

                    //Path should be clear
                    for(int i = currentPos.getX() + 1; i < 7; i++)
                        if(board.spot[currentPos.getY()][i] != null)
                            return false;


                    castling = 'r';
                    return true;
                }
            }

        }


        return false;
  }
}
