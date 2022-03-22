public class Board { //TODO: check this 
	
	Piece[][] board; //TODO: ?
	
	
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
		board[0][1] = new PawnPiece(false);
		board[1][1] = new PawnPiece(false);
		board[2][1] = new PawnPiece(false);
		board[3][1] = new PawnPiece(false);
		board[4][1] = new PawnPiece(false);
		board[5][1] = new PawnPiece(false);
		board[6][1] = new PawnPiece(false);
		board[7][1] = new PawnPiece(false);
		
		board[0][0] = new RookPiece(false);
		board[1][0] = new KnightPiece(false);
		board[2][0] = new BishopPiece(false);
		board[3][0] = new QueenPiece(false);
		board[4][0] = new KingPiece(false);
		board[5][0] = new BishopPiece(false);
		board[6][0] = new KnightPiece(false);
		board[7][0] = new RookPiece(false);
		
		//Black Piece Initialization  
		board[0][6] = new PawnPiece(true);
		board[1][6] = new PawnPiece(true);
		board[2][6] = new PawnPiece(true);
		board[3][6] = new PawnPiece(true);
		board[4][6] = new PawnPiece(true);
		board[5][6] = new PawnPiece(true);
		board[6][6] = new PawnPiece(true);
		board[7][6] = new PawnPiece(true);
		
		board[0][7] = new RookPiece(true);
		board[1][7] = new KnightPiece(true);
		board[2][7] = new BishopPiece(true);
		board[3][7] = new QueenPiece(true);
		board[4][7] = new KingPiece(true);
		board[5][7] = new BishopPiece(true);
		board[6][7] = new KnightPiece(true);
		board[7][7] = new RookPiece(true);
		
		
	}

}