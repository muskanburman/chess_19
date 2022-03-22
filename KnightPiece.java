public class KnightPiece extends Piece {
    
    private String name = "N";
	
	public KnightPiece(boolean value){
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

