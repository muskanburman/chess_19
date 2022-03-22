public class QueenPiece extends Piece{

    private String name = "Q";
	
	public QueenPiece(boolean value){
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
		
		if (deltaX == deltaY){
			return true;
		} else if (deltaX == 0 && deltaY != 0) {
			return true;
		}else if (deltaX != 0 && deltaY == 0){
			return true;
		}
		
		return false;
	}

	public String drawPiece() {
		return this.name;
	}

	public void movePiece() {}

}
