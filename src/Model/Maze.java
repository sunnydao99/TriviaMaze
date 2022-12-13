package Model;

import java.io.Serializable;
import java.util.List;

public class Maze implements Serializable {
    private static final int RECT_SIZE = 35;

    public static int myRectSize() {
        return RECT_SIZE;
    }


    public static int[][] getMAZE() {
        return MAZE;
    }

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

}
