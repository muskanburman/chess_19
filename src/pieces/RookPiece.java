package src.pieces;

import src.structure.*;

/**
 * This is the class for the Rook piece, and it extends the class Piece
 *
 * @author Muskan Burman
 * @author Magdi Aref
 */
public class RookPiece extends Piece {

    public RookPiece() {
        isFirstMove = true;
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

        //Check to make sure it is not moving both left/right and up/down in the same move
        if(numSpacesX > 0 && numSpacesY > 0)
            return false;

        
        //Up
        if(numSpacesX == 0 && numSpacesY > 0){
            if(currentPos.getY() > newPos.getY()) {
                for (int i = currentPos.getY() - 1; i > newPos.getY(); i--) {
                    if (board.square[i][currentPos.getX()] != null)
                        return false;
                }
                
                isFirstMove = false;

                return true;
            }
        }


        //Down
        if(numSpacesX == 0 && numSpacesY > 0){
            if(currentPos.getY() < newPos.getY()) {
                for (int i = currentPos.getY() + 1; i < newPos.getY(); i++) {
                    if (board.square[i][currentPos.getX()] != null)
                        return false;
                }

                isFirstMove = false;
                
                return true;
            }
        }

        //Left
        if(numSpacesX > 0 && numSpacesY == 0){
            if(currentPos.getX() > newPos.getX()) {
                for (int i = currentPos.getX() - 1; i > newPos.getX(); i--) {
                    if (board.square[currentPos.getY()][i] != null)
                        return false;
                }
                
                isFirstMove = false;

                return true;
            }
        }

        //Right
        if(numSpacesX > 0 && numSpacesY == 0){
            if(currentPos.getX() < newPos.getX()) {
                for (int i = currentPos.getX() + 1; i < newPos.getX(); i++) {
                    if (board.square[currentPos.getY()][i] != null)
                        return false;
                }

                isFirstMove = false;
                
                return true;
            }
        }

        return false;
    }
    
}
