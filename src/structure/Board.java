package src.structure;

import src.pieces.*;

/**
 * Object that holds the chessboard in a 8x8 array of spots for the initializtion of the chess board.
 *
 * @author Muskan Burman
 * @author Magdi Aref
 */

public class Board {

    /**
     * create a 2d array of spots
     */
    public Spot[][] spot;

    /**
     * Constructor
     *
     * Genrates an 8x8 array of spots and initializes each piece to its corresponding spot and the player color at the start of a match.
     */
    public Board() {

        //8x8 board
        spot = new Spot[8][8];

        //Blacks pieces
        spot[0][0] = new Spot(new RookPiece(), "b");
        spot[0][1] = new Spot(new KnightPiece(), "b");
        spot[0][2] = new Spot(new BishopPiece(), "b");
        spot[0][3] = new Spot(new QueenPiece(), "b");
        spot[0][4] = new Spot(new KingPiece(), "b");
        spot[0][5] = new Spot(new BishopPiece(), "b");
        spot[0][6] = new Spot(new KnightPiece(), "b");
        spot[0][7] = new Spot(new RookPiece(), "b");

        for(int i = 0; i < 8; i++) {
            spot[1][i] = new Spot(new PawnPiece(), "b");
        }

        //Whites pieces
        spot[7][0] = new Spot(new RookPiece(), "w");
        spot[7][1] = new Spot(new KnightPiece(), "w");
        spot[7][2] = new Spot(new BishopPiece(), "w");
        spot[7][3] = new Spot(new QueenPiece(), "w");
        spot[7][4] = new Spot(new KingPiece(), "w");
        spot[7][5] = new Spot(new BishopPiece(), "w");
        spot[7][6] = new Spot(new KnightPiece(), "w");
        spot[7][7] = new Spot(new RookPiece(), "w");

        for(int i = 0; i < 8; i++) {
            spot[6][i] = new Spot(new PawnPiece(), "w");
        }


    }

    /**
     * Prints the current form of the formatted chessboard 
     */
    public void display() {
        /**
         *Prints ## every other spot
         */
        boolean hash = false;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(spot[i][j] == null && hash)
                    System.out.print("## ");
                else if(spot[i][j] == null)
                    System.out.print("   ");
                else
                    System.out.print(spot[i][j] + " ");

                hash = !hash;
            }
            System.out.println(8 - i);
            hash = !hash;
        }
        System.out.println(" a  b  c  d  e  f  g  h\n");
    }
}
