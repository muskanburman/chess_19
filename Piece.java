public abstract class Piece {
    
    private boolean white = true; // the piece can either belong to white (true) or black (false) player

    public boolean firstTurn = true; // if white is true

    /*
    * This method allocates the white piece to the player with white pieces and the black piece to the player with black pieces. 
    * value: true if white, false if black. 
    */
    public void setWhite(boolean value) { 
        white = value;
    }

    /*
    * This method allocates the white piece to the player with white pieces and the black piece to the player with black pieces. 
    */
    public boolean isWhite() { 
        return this.white;
    }

    /*
    * This method checks if the move made by the piece is a valid/legitimate move for that piece. 
    * This method is abstract because it later gets called on by each piece separately.
    * previousXpos: the previous X coordinate of the piece
    * previousYpos: the previous Y coordinate of the piece
    * newXpos: the new X coordinate of the piece
    * newYpos: the new Y coordinate of the piece
    * isNewPosEmpty: true is the new position on the board is unoccupied, false otherwise
    */
    public abstract boolean isValidMove(int previousXpos, int previousYpos, int newXpos, int newYpos, boolean isNewPosEmpty); 
    
    /*
    * This method draws the piece. //TODO: check this
    */
	public abstract String drawPiece();

    /*
    * This method moves the piece. //TODO: check this
    */
	public abstract void movePiece();
    
}