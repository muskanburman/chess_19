public class KnightPiece extends Piece {
    
    private String name = "Knight";
	
	public KnightPiece(boolean value){
		this.setWhite(value);
		
		if (value == true){
			this.name = "White" + this.name; 
		}else{
			this.name = "Black" + this.name; 
		}
	}
	
	public boolean isValidMove(int previousXpos, int previousYpos, int newXpos, int newYpos, boolean isNewPosEmpty) {

		int deltaX;
		int deltaY;
		
		deltaX = Math.abs(newXpos-previousXpos);
		deltaY = Math.abs(newYpos-previousYpos);
		
		if (deltaX == 2 && deltaY == 1){
			return true;
		} else if (deltaX == 1 && deltaY == 2) {
			return true;
		}
				
		return false;
	}

	public String drawPiece() {
		return this.name;
	}

	public void movePiece() {}
}

