public class Board {
	
	Piece[][] board;
	
	
	// Initializes the board and the correct amount of black and white pieces in their Satrting Location
	 	
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
		
	
		//initializes pieces and place them on the board.
		//White Piece Initialization
		board[0][1] = new Pawn(false);
		board[1][1] = new Pawn(false);
		board[2][1] = new Pawn(false);
		board[3][1] = new Pawn(false);
		board[4][1] = new Pawn(false);
		board[5][1] = new Pawn(false);
		board[6][1] = new Pawn(false);
		board[7][1] = new Pawn(false);
		
		board[0][0] = new Rook(false);
		board[1][0] = new Knight(false);
		board[2][0] = new Bishop(false);
		board[3][0] = new Queen(false);
		board[4][0] = new King(false);
		board[5][0] = new Bishop(false);
		board[6][0] = new Knight(false);
		board[7][0] = new Rook(false);
		
		//Black Piece Initialization  
		board[0][6] = new Pawn(true);
		board[1][6] = new Pawn(true);
		board[2][6] = new Pawn(true);
		board[3][6] = new Pawn(true);
		board[4][6] = new Pawn(true);
		board[5][6] = new Pawn(true);
		board[6][6] = new Pawn(true);
		board[7][6] = new Pawn(true);
		
		board[0][7] = new Rook(true);
		board[1][7] = new Knight(true);
		board[2][7] = new Bishop(true);
		board[3][7] = new Queen(true);
		board[4][7] = new King(true);
		board[5][7] = new Bishop(true);
		board[6][7] = new Knight(true);
		board[7][7] = new Rook(true);
		
		
	}

}