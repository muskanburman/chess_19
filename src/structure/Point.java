package src.structure;

/**
 * Holds the x and y coordinate of a specific square on the chess board
 *
 * @author Muskan Burman
 * @author Magdi Aref
 */
public class Point {
    int x, y;

    /**
     * Constructor
     *
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     *int x getter
     *
     * @return the x-coordinate
     */
    public int getX() {
        return x;
    }

    /**
     *int y getter
     *
     * @return the y-coordinate
     */
    public int getY() {
        return y;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "(" + x + " , " + y + ")";
    }
}
