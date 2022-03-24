package src.pieces;

import src.structure.*;

/**
 * This is the class for the Knight piece, and it extends the class Piece
 *
 * @author Muskan Burman
 * @author Magdi Aref
 */
public class KnightPiece extends Piece {

    public KnightPiece() {
        isFirstMove = true;
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

        //Move 1 Left or right , move 2 up or down 
        if(numSpacesX == 1 && numSpacesY == 2)
            return true;

        //Move 2 Left or right, move 1 up or down 
        if(numSpacesX == 2 && numSpacesY == 1)
            return true;


        return false;
    }
}
