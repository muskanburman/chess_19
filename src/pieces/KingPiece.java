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

        Square currentPosSquare = board.square[currentPos.getY()][currentPos.getX()];
        Square newPosSquare = board.square[newPos.getY()][newPos.getX()];

        //Number of spaces moved in X and Y direction
        int numSpacesX = Math.abs(currentPos.getX() - newPos.getX());
        int numSpacesY = Math.abs(currentPos.getY() - newPos.getY());

        //Check to make sure that the piece is not taking place of a piece of its own kind
        if(newPosSquare != null && currentPosSquare.player.equals(newPosSquare.player))
            return false;

        //Move to adjacent square
        if((numSpacesX == 1 && numSpacesY == 0) ||
           (numSpacesX == 0 && numSpacesY == 1) ||
           (numSpacesX == 1 && numSpacesY == 1)) {
            isFirstMove = false;
            castling = '0';
            return true;
        }


        //Castling
        if(numSpacesX == 2 && numSpacesY == 0 && isFirstMove) {

            Square leftRookSquare = board.square[currentPos.getY()][0];
            Square rightRookSquare = board.square[currentPos.getY()][7];

            //Left
            if(currentPos.getX() > newPos.getX()) {
                if(leftRookSquare != null &&
                   leftRookSquare.piece instanceof RookPiece &&
                   leftRookSquare.piece.isFirstMove &&
                   board.square[currentPos.getY()][currentPos.getX()].player.equals(leftRookSquare.player)) {

                    //Path should be clear
                    for(int i = currentPos.getX() - 1; i > 0; i--)
                        if(board.square[currentPos.getY()][i] != null)
                            return false;


                    castling = 'l';
                    return true;
                }
            }

            //Right
            else {
                if(rightRookSquare != null &&
                   rightRookSquare.piece instanceof RookPiece &&
                   rightRookSquare.piece.isFirstMove &&
                   board.square[currentPos.getY()][currentPos.getX()].player.equals(rightRookSquare.player)) {

                    //Path should be clear
                    for(int i = currentPos.getX() + 1; i < 7; i++)
                        if(board.square[currentPos.getY()][i] != null)
                            return false;


                    castling = 'r';
                    return true;
                }
            }

        }


        return false;
  }
}
