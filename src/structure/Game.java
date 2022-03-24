package src.structure;

import src.pieces.*;
import java.util.Scanner;

/**
 * Sets the structure and logic of the chess game and determines 
 * the current players turn, parsing inputs, moving pieces,
 * and whether a winner is decalered.
 *
 * @author Muskan Burman
 * @author Magdi Aref
 */
public class Game {

    /**
     * Keeps track of the current players turn
     */
    public enum Turn { WHITE, BLACK };

    /**
     * Current turn (white or black)
     */
    Turn turn;
    /**
     * The chess board
     */
    Board board;

    /**
     * Constructor
     *
     * Creates a new Board object, then calls the start method
     */
    public Game() {
        board = new Board();
        start(board);
    }

    /**
     * Chess game begins, code loops through use imputs until a winner is decided.
     *
     * @param board The chess board
     */
    public void start(Board board) {
        Scanner reader = new Scanner(System.in);
        String input;
        turn = Turn.WHITE;
        boolean winnerDeclared = false;
        boolean lastMoveLegal = true;
        boolean drawOffered = false;

        while(!winnerDeclared) {

            //Print board only if a move was executed
            if(lastMoveLegal)
                board.display();

            //White's turn
            if(turn == Turn.WHITE)
                System.out.print("White's move: ");

            //Black's turn
            else
                System.out.print("Black's move: ");

            input = reader.nextLine();

            //Players agree on a draw
            if(input.equals("draw") && drawOffered)
                break;

            //If the movement is valid, next players turn
            if(movePiece(input, turn)) {
                if(turn == Turn.WHITE) {
                    turn = Turn.BLACK;
                    clearEnpassant("b");
                }
                else {
                    turn = Turn.WHITE;
                    clearEnpassant("w");
                }

                lastMoveLegal = true;

                //valid movement made by the player and player offered a draw
                if(input.length() == 11 && input.substring(6, 11).equals("draw?"))
                    drawOffered = true;
                else
                    drawOffered = false;

                //Player resigned
                if(input.equals("resign"))
                    winnerDeclared = true;
                
                //Check if player is in check
                if(detectCheck()) {
                    System.out.println("\nCheck");


                    //Check if player is in checkmate, if true they lose
                    boolean checkMate = false;
                    if(turn == turn.WHITE)
                        checkMate = detectMate("w");
                    else
                        checkMate = detectMate("b");

                    if(checkMate) {
                        if(turn == turn.WHITE)
                            turn = turn.BLACK;
                        else
                            turn = turn.WHITE;
                        winnerDeclared = true;
                        System.out.println("Checkmate");
                        //System.out.println();
                        //board.display();

                    }

                }





            //Invalid move
            } else {
                lastMoveLegal = false;
                System.out.println("\nIllegal move, try again");
            }

            System.out.println();
        }

        //Print Winner
        if(winnerDeclared && turn == Turn.WHITE)
            System.out.println("White wins");
        else if(winnerDeclared && turn == Turn.BLACK)
            System.out.println("Black wins");
        else
            System.out.println("\nDraw");
    }


    /**
     * Determines if players are making a valid move depending on what the players pieces are.
     *
     * @param s The user's input in FileRank from start to end 
     * @param turn The current players turn
     * @return True if the move was valid for the chess piece or false otherwise
     */
    public boolean movePiece(String s, Turn turn) {

        //Player resigned
        if(s.equals("resign"))
            return true;

        //Invalid input
        if(s.length() < 5)
            return false;

        Point start = getPosition(s.substring(0,2));
        Point end = getPosition(s.substring(3,5));

        if(start == null || end == null)
            return false;

        //Check to see that square is not blank
        if(board.square[start.getY()][start.getX()] == null)
            return false;

        Square startSquare = board.square[start.getY()][start.getX()];

        //Check that the approprate player is moving their own piece
        if((startSquare.player.equals("b") && turn == Turn.WHITE) ||
           (startSquare.player.equals("w") && turn == Turn.BLACK)) {

            return false;
        }

        //Check input included a promotion character
        if(s.length() >= 7) {
            startSquare.piece.promotion = s.charAt(6);
        }

        //Check to make sure the specific piece is allowed to move in that direction
        if(!startSquare.piece.isValidMove(start, end, board))
            return false;

        //En passant executed by player
        if(startSquare.piece.enpassant == '1') {
            board.square[start.getY()][end.getX()] = null;
        }

        //Castling executed by player
        if(startSquare.piece.castling == 'l') {
            board.square[end.getY()][end.getX() + 1] = board.square[end.getY()][0];
            board.square[end.getY()][0] = null;
        }
        if(startSquare.piece.castling == 'r') {
            board.square[end.getY()][end.getX() - 1] = board.square[end.getY()][7];
            board.square[end.getY()][7] = null;
        }


        //Move the piece
        board.square[end.getY()][end.getX()] = board.square[start.getY()][start.getX()];
        board.square[start.getY()][start.getX()] = null;

        return true;

    }

    /**
     * Determines if a player is in check
     *
     * @return True if a player is in check, false otherwise
     */
    public boolean detectCheck() {

        //Marks the location of each player's king and marks the piece to compare
        Point whitesKing = null, blacksKing = null, test;

        //Search for each king
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Square s = board.square[i][j];
                if(s == null) {
                    //Skip null spot
                }
                else if((s.piece instanceof KingPiece) && s.player.equals("w"))
                    whitesKing = new Point(j , i);
                else if((s.piece instanceof  KingPiece) && s.player.equals("b"))
                    blacksKing = new Point(j , i);
                
            }
           
        }

        //Compares each piece to see if it can attack the opponents king
        int x = 0;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Square s = board.square[i][j];
                if(s == null) {
                    //Skip the null spot
                } else if(s.player.equals("w")) {
                    test = new Point(j , i);
                    if(s.piece.isValidMove(test, blacksKing, board)) {
                        //System.out.println("White's " + s.piece.getClass().getSimpleName() + " can attack black's king");
                        //board.display();
                        return true;    //Black in check

                    }
                } else if(s.player.equals("b")) {
                    test = new Point(j , i);
                    if(s.piece.isValidMove(test, whitesKing, board)) {
                        //System.out.println("Black's " + s.piece.getClass().getSimpleName() + " can attack white's king");
                        //board.display();
                        return true;    //White in check

                    }
                
                }
            }
        }

        return false;
    }

    /**
     * Determines if a player is in Checkmate
     *
     * @param p The player to check for checkmate (w or b)
     * @return True if player is in checkmate, false otherwise
     */
    public boolean detectMate(String p) {

        //stores the firstMove variable
        boolean firstMove;

        //Loop through the board and find all white pieces
        for(int a = 0; a < 8; a++) {
            for(int b = 0; b < 8; b++) {
                if(board.square[a][b] != null &&
                   board.square[a][b].player.equals(p)) {
                    Point start = new Point(b , a);

                    //Check every piece on the board to see if a move is possible.
                    for(int c = 0; c < 8; c++) {
                        for(int d = 0; d < 8; d++) {
                            Point end = new Point(d, c);

                            //stores firstMove variable
                            firstMove = board.square[a][b].piece.firstMove;

                            //check if it can move to given piece
                            if(board.square[a][b].piece.isValidMove(start, end, board)) {

                                //Move the piece
                                Square startSquare = board.square[a][b];
                                Square endSquare = board.square[end.getY()][end.getX()];
                                board.square[end.getY()][end.getX()] = board.square[a][b];
                                board.square[a][b] = null;

                                //Finds a situation where the player may move a piece to not be in check anymore
                                if(!detectCheck()) {
                                    board.square[end.getY()][end.getX()] = endSquare;
                                    board.square[a][b] = startSquare;
                                    return false;
                                }

                                //Put the board back
                                board.square[end.getY()][end.getX()] = endSquare;
                                board.square[a][b] = startSquare;

                                //Reset the firstMove variable
                                board.square[a][b].piece.firstMove = firstMove;

                            }
                        }
                    }
                }
            }
        }


        return true;
    }

    /**
     * Parses an input into a x and y coordinate 
     *
     * @param s FileRank 
     * @return A new Point object with the x and y coordinates belonging to that location on a chessboard
     */
    public Point getPosition(String s) {

        char c1, c2;
        int x, y;

        c1 = s.charAt(0);
        c2 = s.charAt(1);

        //x coordinate
        switch(c1) {
            case 'a': x = 0; break;
            case 'b': x = 1; break;
            case 'c': x = 2; break;
            case 'd': x = 3; break;
            case 'e': x = 4; break;
            case 'f': x = 5; break;
            case 'g': x = 6; break;
            case 'h': x = 7; break;
            default: return null;
        }

        //y coordinate
        y = Character.getNumericValue(c2);
        y = 8 - y; //Maps input to index of board 

        //Check bounds;
        if(y < 0 || y > 7)
            return null;

        return new Point(x, y);
    }

    /**
     * Loops through every pawn and clears their enpassant value
     *
     * @param player Player to compare (white or black)
     */
    public void clearEnpassant(String player) {

        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Square s = board.square[i][j];
                if(s == null) {

                }
                else if(s.piece instanceof PawnPiece && s.player.equals(player))
                    ((PawnPiece) board.square[i][j].piece).doubleJump = false;
            }
        }
    }
}

