public class QueenPiece extends Piece{

    private String name = "Queen";
	
	public QueenPiece(boolean value){
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
