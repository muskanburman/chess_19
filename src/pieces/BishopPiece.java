package src.pieces;

import src.structure.*;

/**
 * This is the class for the Bishop piece, and it extends the class Piece
 *
 * @author Muskan Burman
 * @author Magdi Aref
 */
public class BishopPiece extends Piece{

    public BishopPiece() {
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


        //moving up and left diagonally
        if(numSpacesX == numSpacesY) {
            if (currentPos.getX() > newPos.getX() && currentPos.getY() > newPos.getY()) {
                for (int i = currentPos.getY() - 1, j = currentPos.getX() - 1; i > newPos.getY() && j > newPos.getX(); i--, j--) {
                    if (board.square[i][j] != null)
                        return false;
                }
                return true;
            }
        }

        //moving up and right diagonally
        if(numSpacesX == numSpacesY) {
            if (currentPos.getX() < newPos.getX() && currentPos.getY() > newPos.getY()) {
                for (int i = currentPos.getY() - 1, j = currentPos.getX() + 1; i > newPos.getY() && j < newPos.getX(); i--, j++) {
                    if (board.square[i][j] != null)
                        return false;
                }
                return true;
            }
        }

        //moving bottom and left diagonally
        if(numSpacesX == numSpacesY) {
            if (currentPos.getX() > newPos.getX() && currentPos.getY() < newPos.getY()) {
                for (int i = currentPos.getY() + 1, j = currentPos.getX() - 1; i < newPos.getY() && j > newPos.getX(); i++, j--) {
                    if (board.square[i][j] != null)
                        return false;
                }
                return true;
            }
        }

        //moving bottom and right diagonally
        if(numSpacesX == numSpacesY) {
            if (currentPos.getX() < newPos.getX() && currentPos.getY() < newPos.getY()) {
                for (int i = currentPos.getY() + 1, j = currentPos.getX() + 1; i < newPos.getY() && j < newPos.getX(); i++, j++) {
                    if (board.square[i][j] != null)
                        return false;
                }
                return true;
            }
        }
        	

        return false;
    }
}
