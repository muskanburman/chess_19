public class BishopPiece extends Piece {
    
    private String name = "B";
	
	public BishopPiece(boolean value){
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
