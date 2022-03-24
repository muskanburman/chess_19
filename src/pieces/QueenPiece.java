package src.pieces;

import src.structure.*;

/**
 * This is the class for the Queen piece, and it extends the class Piece
 *
 * @author Muskan Burman
 * @author Magdi Aref
 */
public class QueenPiece extends Piece {

    public QueenPiece() {
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

		/*
		Vertical and Horizontal Movement
         */

		//Up
		if(numSpacesX == 0 && numSpacesY > 0){
			if(currentPos.getY() > newPos.getY()) {
				for (int i = currentPos.getY() - 1; i > newPos.getY(); i--) {
					if (board.spot[i][currentPos.getX()] != null)
						return false;
				}

				return true;
			}
		}

		//Down
		if(numSpacesX == 0 && numSpacesY > 0){
			if(currentPos.getY() < newPos.getY()) {
				for (int i = currentPos.getY() + 1; i < newPos.getY(); i++) {
					if (board.spot[i][currentPos.getX()] != null)
						return false;
				}

				return true;
			}
		}

		//Left
		if(numSpacesX > 0 && numSpacesY == 0){
			if(currentPos.getX() > newPos.getX()) {
				for (int i = currentPos.getX() - 1; i > newPos.getX(); i--) {
					if (board.spot[currentPos.getY()][i] != null)
						return false;
				}

				return true;
			}
		}

		//Right
		if(numSpacesX > 0 && numSpacesY == 0){
			if(currentPos.getX() < newPos.getX()) {
				for (int i = currentPos.getX() + 1; i < newPos.getX(); i++) {
					if (board.spot[currentPos.getY()][i] != null)
						return false;
				}

				return true;
			}
		}

		/*
		Diagonal Movement
		 */

		//Up and left 
		if(numSpacesX == numSpacesY) {
			if (currentPos.getX() > newPos.getX() && currentPos.getY() > newPos.getY()) {
				for (int i = currentPos.getY() - 1, j = currentPos.getX() - 1; i > newPos.getY() && j > newPos.getX(); i--, j--) {
					if (board.spot[i][j] != null)
						return false;
				}
				return true;
			}
		}

		//Up and right 
		if(numSpacesX == numSpacesY) {
			if (currentPos.getX() < newPos.getX() && currentPos.getY() > newPos.getY()) {
				for (int i = currentPos.getY() - 1, j = currentPos.getX() + 1; i > newPos.getY() && j < newPos.getX(); i--, j++) {
					if (board.spot[i][j] != null)
						return false;
				}
				return true;
			}
		}

		//Bottom and left 
		if(numSpacesX == numSpacesY) {
			if (currentPos.getX() > newPos.getX() && currentPos.getY() < newPos.getY()) {
				for (int i = currentPos.getY() + 1, j = currentPos.getX() - 1; i < newPos.getY() && j > newPos.getX(); i++, j--) {
					if (board.spot[i][j] != null)
						return false;
				}
				return true;
			}
		}

		//Bottom and right 
		if(numSpacesX == numSpacesY) {
			if (currentPos.getX() < newPos.getX() && currentPos.getY() < newPos.getY()) {
				for (int i = currentPos.getY() + 1, j = currentPos.getX() + 1; i < newPos.getY() && j < newPos.getX(); i++, j++) {
					if (board.spot[i][j] != null)
						return false;
				}
				return true;
			}
		}

        
        return false;
    }
}