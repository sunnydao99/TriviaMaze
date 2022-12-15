package Model;

import java.io.Serializable;
import java.util.List;

/**
 * This class is created to generate the maze
 * @version 12.14.22 (latest update)
 * @author An Nguyee=n , Satinder Singh
 */
public class Maze implements Serializable {
    private static final int RECT_SIZE = 60;
    /**
     * This method is using 2D array to create the maze
     */
    private static final int[][] MAZE =
            {       {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 5, 1, 0, 4, 0, 0, 0, 0, 0, 4, 0, 1},
                    {1, 0, 1, 0, 0, 0, 0, 4, 1, 1, 1, 0, 1},
                    {1, 0, 4, 0, 1, 1, 0, 0, 0, 0, 4, 0, 1},
                    {1, 0, 1, 0, 0, 0, 4, 0, 1, 1, 1, 0, 1},
                    {1, 0, 1, 0, 1, 1, 1, 0, 4, 0, 0, 4, 1},
                    {1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1},
                    {1, 0, 1, 4, 1, 1, 1, 4, 1, 4, 0, 0, 1}, // 10x13
                    {1, 4, 0, 0, 0, 4, 0, 0, 0, 0, 1, 6, 1}, // at position 11,8
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            };

    /**
     * This method is used to return the size of rectangle
     * @return int the value of size
     */
    public static int myRectSize() {
        return RECT_SIZE;
    }
    /**
     * This method is created to get the maze
     * @return int the maze
     */
    public static int[][] getMAZE() {
        return MAZE;
    }


}
