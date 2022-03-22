public class BishopPiece extends Piece {
    
    private String name = "Bishop";
	
	public BishopPiece(boolean value){
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
		
		if (deltaX == deltaY) {
			//System.out.println("canMove:true");
			return true;
		}
		
		//System.out.println("canMove:false");
		
		return false;
	}

	public String drawPiece() {
		return this.name;
	}

	public void movePiece() {}
}
