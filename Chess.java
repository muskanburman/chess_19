import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//TODO: check
//TODO: check mate


public class Chess {
	
	public static Board gameBoard;
	public static String currentLoc = null;
	public static String newLoc = null;
	public static String thirdArgument = null;
	public static boolean askForDraw  = false;
	public static boolean whiteTurn = true;
	public static boolean illegalMoveCheck = false;
	
	public static boolean enPassant = false;
	public static int enPassantX;
	public static int enPassantY;
	public static int enPassantToEliminateX;
	public static int enPassantToEliminateY;
	public static int enPassantCapturer1X;
	public static int enPassantCapturer1Y;
	public static int enPassantCapturer2X;
	public static int enPassantCapturer2Y;
	
	

	public static void main(String[] args) {
		//initialize game
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = null;
		
		initializeGame();
		drawBoard();
		
		
		
		//game loop
		while (true){
			//read the player's move
		     input = null;
		    
		     try {

				boolean check = gameBoard.detectCheck(whiteTurn);
		    	 if (check == true) {
					System.out.println("Check");
					System.out.println();
		    	 }
		    	 
		    	 if (whiteTurn){		
						System.out.print("White's move: ");	
		    	 }else{
					 System.out.print("Black's move: ");
		    	 }

		    	 input = br.readLine();
		    	 
		    	 parseInput(input);
		  
				 if (illegalMoveCheck == false){
					drawBoard();
				}
		    	 
		    	 check = gameBoard.detectCheck(!whiteTurn);
		    	 if (check == true) {
		    		 if (!whiteTurn) {
						System.out.println("Checkmate");
		    			System.out.println("Black wins");
		    		 }else {
		    			System.out.println("Checkmate");
		    			System.out.println("White wins");
		    		 }
		    		 System.exit(1);
		    	 }
		    	 
		    
		    	 
		     } catch (IOException e) {
				 System.out.println("Illegal move, try again");
				 illegalMoveCheck = true;
		     }       
		}
		//exit
	}
	
	public static void initializeGame(){
		gameBoard = new Board();
	}
	
	
	public static void resignGame(){}
	
	public static void gameDraw(){}
	
	public static void drawBoard(){
		String[][] result = new String[8][8];
		
		//make the tiles white or black
		boolean white = true;
		for (int i = 0; i < 8; i++){
			for (int j = 0; j < 8; j++){
				if (white){
					result[i][j] = "   ";
					white = false;
				}else{
					result[i][j] = "## ";
					white = true;
				}
			}
			white = !(white);
		}	
		
		// draw the pieces in the right locations
		for (int y = 0; y < 8; y++){
			for (int x = 0; x < 8; x++){
				
				if ( gameBoard.board[x][y] != null){
					result[x][y] = gameBoard.board[x][y].drawPiece() + " ";
				}
			}
		}
		
		//print out the whole board  
		System.out.println();
		for (int y = 0; y < 8; y++){
			
			for (int x = 0; x < 8; x++){
				System.out.print(result[x][y]);
			}
			System.out.print(" " + (8-y));
			System.out.println();
		}
		System.out.println(" a  b  c  d  e  f  g  h");
		System.out.println();

	}
	
	
	
	public static int fileToCoordinate(String file){
		int result = (int) file.toLowerCase().charAt(0) - (int)('a');
		return result;
	}
	
	
	
	public static int rankToCoordinate(String file){
		int result = 7 - ((int) file.toLowerCase().charAt(1) - (int)('1'));
		return result;
	}

	
	public static boolean legalInput(int x1, int x2, int x3, int x4){
		if ((x1 >= 0 && x1 <= 7) && (x2 >= 0 && x2 <= 7) && (x3 >= 0 && x3 <= 7) && (x4 >= 0 && x4 <= 7)){
			return true;
		}
		
		return false;
		
	}
	
	
	public static void parseInput(String input){
		StringTokenizer st = null;
		int count = 0;
		input = input.toLowerCase();
		input = input.trim();
		
		String[] array = new String[100]; 
		 
		st = new StringTokenizer(input);
		 
		while (st.hasMoreTokens()) {
	        array[count] = st.nextToken();
	        count++;
	    }
		
		if (count > 0 && count < 4){
			if (count == 1){
				if (array[0].equals("resign")){
					if (whiteTurn){
						System.out.println("Black wins");
						System.exit(1);
					}else{
						System.out.println("White wins");
						System.exit(1);
					}
					
				}else if (array[0].equals("draw")){
					if (askForDraw == true){
						System.out.println("draw");
						System.exit(1);
					}else{
						System.out.println("Illegal move, try again");
						illegalMoveCheck = true;
					}
				}else{
					System.out.println("Illegal move, try again");
					illegalMoveCheck = true;
				}
			}else if (count == 2) {
				currentLoc = array[0];
				newLoc = array[1];
				if (currentLoc.length() == 2 && newLoc.length() == 2 ){
					executeMove();
				}else{
					System.out.println("Illegal move, try again");
					illegalMoveCheck = true;
				}
				
			}else if (count == 3){
				currentLoc = array[0];
				newLoc = array[1];
				if (currentLoc.length() == 2 && newLoc.length() == 2 ){
					
					if (array[2].equals("draw?")){
						executeMove();
						askForDraw = true;
					}else if (array[2].equals("q") || array[2].equals("r") || array[2].equals("b") || array[2].equals("n")) {
						thirdArgument = array[2];
						executeMove();
					}else{
						System.out.println("Illegal move, try again");
						illegalMoveCheck = true;
					}
				}else{
					System.out.println("Illegal move, try again");
					illegalMoveCheck = true;
				}
			}
		}else{
			System.out.println("Illegal move, try again");
			illegalMoveCheck = true;
		}
		
		currentLoc = null;
		newLoc = null;
		thirdArgument = null;
	}
	
	
	
	public static void executeMoveOld(){
		 
		int oldx = fileToCoordinate(currentLoc);
		int oldy =rankToCoordinate(currentLoc);
		 
		int newx = fileToCoordinate(newLoc);
		int newy =rankToCoordinate(newLoc);
		
		System.out.println(currentLoc +" "+ newLoc);
		 
		if (legalInput(oldx, oldy, newx, newy) == true){ //are the coordinates entered legal?
			
			if (gameBoard.board[oldx][oldy] != null){ // is there a piece in the chosen spot?
				
						
					if ((whiteTurn && gameBoard.board[oldx][oldy].isWhite()) || (!whiteTurn && !gameBoard.board[oldx][oldy].isWhite())){ //does the piece being moved belong to the player whos turn it is?
						if (gameBoard.board[newx][newy] == null){ // is the new location empty?
							
							if (gameBoard.testCastling(oldx, oldy, newx, newy)) {
								return;
							}
							
							if (gameBoard.board[oldx][oldy].isValidMove(oldx, oldy, newx, newy, true)  && gameBoard.isPathClear(oldx, oldy, newx, newx) ){ 
								
								gameBoard.board[newx][newy] = gameBoard.board[oldx][oldy];
								
								if ( gameBoard.board[newx][newy].drawPiece().equalsIgnoreCase("wp") || gameBoard.board[newx][newy].drawPiece().equalsIgnoreCase("bp")  ) {
									pawnPromotion(newx, newy);
								}
								
								gameBoard.board[oldx][oldy] = null;
								
								whiteTurn = !whiteTurn;
							}else{
								System.out.println("Illegal move, try again");
								illegalMoveCheck = true;
							}
							
						}else if ((whiteTurn && !gameBoard.board[newx][newy].isWhite()) || 
								(!whiteTurn && gameBoard.board[newx][newy].isWhite())){ // is it the correct colored piece in the old spot, and the opposite colored in the new spot?
							if (gameBoard.board[oldx][oldy].isValidMove(oldx, oldy, newx, newy, false)  && gameBoard.isPathClear(oldx, oldy, newx, newx)){ 
								
								gameBoard.board[newx][newy] = gameBoard.board[oldx][oldy];
								
								if ( gameBoard.board[newx][newy].drawPiece().equalsIgnoreCase("wp") || gameBoard.board[newx][newy].drawPiece().equalsIgnoreCase("bp")  ) {
									pawnPromotion(newx, newy);
								}
								
								gameBoard.board[oldx][oldy] = null;
								
								whiteTurn = !whiteTurn;
							}else{
									System.out.println("Illegal move, try again");
									illegalMoveCheck = true;
							}
							
						}else{
							 System.out.println("Illegal move, try again");
							 illegalMoveCheck = true;
						}
					
				}else{
					 System.out.println("Illegal move, try again");
					 illegalMoveCheck = true;
				}
			}else{
				System.out.println("Illegal move, try again");
				illegalMoveCheck = true;
			}
		}else{
			System.out.println("Illegal move, try again");
			illegalMoveCheck = true;
		}
		 
	}
	
	
	public static void pawnPromotion(int newx, int newy) {
		if (gameBoard.board[newx][newy].drawPiece().equalsIgnoreCase("wp") && newy == 0) {
			if (thirdArgument == null) {
				gameBoard.board[newx][newy] = new QueenPiece(true);
				
			}else if (thirdArgument.equals("q")) {
				gameBoard.board[newx][newy] = new QueenPiece(true);
				
			}else if (thirdArgument.equals("r")) {
				gameBoard.board[newx][newy] = new RookPiece(true);
				
			}else if (thirdArgument.equals("b")) {
				gameBoard.board[newx][newy] = new BishopPiece(true);
				
			}else if (thirdArgument.equals("n")) {
				gameBoard.board[newx][newy] = new KingPiece(true);
				
			}
		}else if (gameBoard.board[newx][newy].drawPiece().equalsIgnoreCase("bp") && newy == 7) {
			if (thirdArgument == null) {
				gameBoard.board[newx][newy] = new QueenPiece(false);
				
			}else if (thirdArgument.equals("q")) {
				gameBoard.board[newx][newy] = new QueenPiece(false);
				
			}else if (thirdArgument.equals("r")) {
				gameBoard.board[newx][newy] = new RookPiece(false);
				
			}else if (thirdArgument.equals("b")) {
				gameBoard.board[newx][newy] = new BishopPiece(false);
				
			}else if (thirdArgument.equals("n")) {
				gameBoard.board[newx][newy] = new KingPiece(false);
				
			}
		}
	}
	
	
	public static void executeMove(){
		 
		int oldx = fileToCoordinate(currentLoc);
		int oldy =rankToCoordinate(currentLoc);
		 
		int newx = fileToCoordinate(newLoc);
		int newy =rankToCoordinate(newLoc);
		
		
		if (legalInput(oldx, oldy, newx, newy) == false){ //are the coordinates entered legal?
			System.out.println("Illegal move, try again");
			illegalMoveCheck = true;
			return;
		}
		
		if (gameBoard.board[oldx][oldy] == null){ // is there a piece in the chosen spot?
			System.out.println("Illegal move, try again");
			illegalMoveCheck = true;
			return;
		}
		
		
		if (whiteTurn != gameBoard.board[oldx][oldy].isWhite()) {
			System.out.print("Illegal move, try again");
			illegalMoveCheck = true;
			return;
		}
		
		boolean isNewPosEmpty = true;
		if (gameBoard.board[newx][newy] != null) {
			if (gameBoard.board[newx][newy].isWhite() == whiteTurn) {
				System.out.println("Illegal move, try again");
				illegalMoveCheck = true;
				return;
			}
			isNewPosEmpty = false;
		}
		
		if (gameBoard.testCastling(oldx, oldy, newx, newy)) {
			//gameBoard.detectCheckMate();
			
			//no longer the first move of the piece
			gameBoard.board[newx][newy].firstTurn = false;
			
			whiteTurn = !whiteTurn;
			return;
		}
		
		if (enPassant == true) {
			enPassant = false;
			if (gameBoard.testEnPassant(oldx, oldy, newx, newy) == true) {
				//gameBoard.detectCheckMate();
				
				//no longer the first move of the piece
				gameBoard.board[newx][newy].firstTurn = false;
				
				whiteTurn = !whiteTurn;
				return;
			}
		} 
		
		if (gameBoard.isPathClear(oldx, oldy, newx, newy) == false) {
			System.out.println("Illegal move, try again");
			illegalMoveCheck = true;
			return;
		}
		 
		if (gameBoard.board[oldx][oldy].isValidMove(oldx, oldy, newx, newy, isNewPosEmpty) == false) {
			System.out.println("Illegal move, try again");
			illegalMoveCheck = true;
			return;
		}
		
		gameBoard.board[newx][newy] = gameBoard.board[oldx][oldy];//move the piece to the new location
		//no longer the first move of the piece
		gameBoard.board[newx][newy].firstTurn = false;
		
		if ( gameBoard.board[newx][newy].drawPiece().equalsIgnoreCase("wp") || gameBoard.board[newx][newy].drawPiece().equalsIgnoreCase("bp")  ) {
			pawnPromotion(newx, newy);
		}
		
		gameBoard.board[oldx][oldy] = null;
		
		//detect enpassant scenario for the next turn
		if (gameBoard.detectEnPassant(oldx, oldy, newx, newy)){
			System.out.println("en passant");
		}
		
		//gameBoard.detectCheckMate();
		whiteTurn = !whiteTurn;
		
		return;
		
	}
}