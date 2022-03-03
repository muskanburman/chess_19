public class Board {
	
	Piece[][] board;
	
    // Initializes the board and the total amount of  pieces 
	public Board(){
		board = new Piece[8][8];
		
		initializeBoard();
		
	}
	
	public void initializeBoard(){
		//initialize the board
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				board[i][j] = null;
			}
		}
		
}}