package Model;

import java.io.Serializable;
import java.util.List;

public class Maze implements Serializable {
    private static final int RECT_SIZE = 60;

    public static int myRectSize() {
        return RECT_SIZE;
    }


    public static int[][] getMAZE() {
        return MAZE;
    }

    private static final int[][] MAZE =
            {       {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 8, 1, 8, 4, 8, 8, 8, 8, 8, 4, 8, 1},
                    {1, 8, 1, 8, 8, 8, 8, 4, 1, 1, 1, 8, 1},
                    {1, 8, 4, 8, 1, 1, 8, 8, 8, 8, 4, 8, 1},
                    {1, 8, 1, 8, 8, 8, 4, 8, 1, 1, 1, 8, 1},
                    {1, 8, 1, 8, 1, 1, 1, 8, 4, 8, 8, 4, 1},
                    {1, 8, 1, 8, 1, 8, 8, 8, 1, 8, 1, 8, 1},
                    {1, 8, 1, 4, 1, 1, 1, 4, 1, 4, 8, 8, 1}, // 10x13
                    {1, 4, 8, 8, 8, 4, 8, 8, 8, 8, 1, 5, 1}, // at position 11,8
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            };

}
