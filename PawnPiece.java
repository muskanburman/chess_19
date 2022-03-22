public class PawnPiece extends Piece {

    private String name = "P";
	
	public PawnPiece(boolean value){
		this.setWhite(value);
		
		if (value == true){
			this.name = "w" + this.name; 
		}else{
			this.name = "b" + this.name; 
		}
	}
	
	public boolean isValidMove(int previousXpos, int previousYpos, int newXpos, int newYpos, boolean isNewPosEmpty) {

		int deltaX;
		int deltaY;
		boolean allowed = false;
		
		if (this.isWhite() == true){
			deltaY = -(newYpos-previousYpos);
		}else{
			deltaY = newYpos - previousYpos;
		}
		
		deltaX = Math.abs(newXpos-previousXpos);

		if (deltaY == 1 && deltaX == 0 && isNewPosEmpty == true){
			allowed = true;
		} else if (deltaX == 1 && deltaY == 1 && isNewPosEmpty == false){  //pawn capture
			allowed  = true;
		} else if (this.firstTurn == true && deltaY == 2 && deltaX == 0 && isNewPosEmpty == true){
			allowed = true;
		
        }
        
		return allowed;
	}

	public String drawPiece() {
		return this.name;
	}

	public void movePiece() {}

}

