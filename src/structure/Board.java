package src.structure;

import src.pieces.*;

/**
 * Object that holds the chessboard in a 8x8 array of Squares for the initializtion of the chess board.
 *
 * @author Muskan Burman
 * @author Magdi Aref
 */

public class Board {

    /**
     * create a 2d array of Squares
     */
    public Square[][] square;

    /**
     * Constructor
     *
     * Genrates an 8x8 array of squares and initializes each piece to its corresponding square and the player color at the start of a match.
     */
    public Board() {

        //8x8 board
        square = new Square[8][8];

        //Blacks pieces
        square[0][0] = new Square(new RookPiece(), "b");
        square[0][1] = new Square(new KnightPiece(), "b");
        square[0][2] = new Square(new BishopPiece(), "b");
        square[0][3] = new Square(new QueenPiece(), "b");
        square[0][4] = new Square(new KingPiece(), "b");
        square[0][5] = new Square(new BishopPiece(), "b");
        square[0][6] = new Square(new KnightPiece(), "b");
        square[0][7] = new Square(new RookPiece(), "b");

        for(int i = 0; i < 8; i++) {
            square[1][i] = new Square(new PawnPiece(), "b");
        }

        //Whites pieces
        square[7][0] = new Square(new RookPiece(), "w");
        square[7][1] = new Square(new KnightPiece(), "w");
        square[7][2] = new Square(new BishopPiece(), "w");
        square[7][3] = new Square(new QueenPiece(), "w");
        square[7][4] = new Square(new KingPiece(), "w");
        square[7][5] = new Square(new BishopPiece(), "w");
        square[7][6] = new Square(new KnightPiece(), "w");
        square[7][7] = new Square(new RookPiece(), "w");

        for(int i = 0; i < 8; i++) {
            square[6][i] = new Square(new PawnPiece(), "w");
        }


    }

    /**
     * Prints the current form of the formatted chessboard 
     */
    public void display() {
        /**
         *Prints ## every other square
         */
        boolean hash = false;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(square[i][j] == null && hash)
                    System.out.print("## ");
                else if(square[i][j] == null)
                    System.out.print("   ");
                else
                    System.out.print(square[i][j] + " ");

                hash = !hash;
            }
            System.out.println(8 - i);
            hash = !hash;
        }
        System.out.println(" a  b  c  d  e  f  g  h\n");
    }
}
