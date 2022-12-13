package Model;

import View.LevelsView;

public class LvHardView extends LevelsView{
    private final int[][] MAZE_HARD =
            {       {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 5, 1, 0, 4, 0, 0, 0, 0, 0, 4, 0, 1},
                    {1, 0, 1, 0, 1, 0, 0, 4, 1, 1, 1, 0, 1},
                    {1, 0, 4, 0, 1, 1, 0, 0, 4, 0, 4, 0, 1},
                    {1, 0, 1, 0, 0, 0, 4, 0, 1, 1, 1, 0, 1},
                    {1, 4, 1, 4, 1, 1, 1, 0, 4, 0, 0, 4, 1},
                    {1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1},
                    {1, 0, 1, 4, 1, 1, 1, 4, 1, 4, 0, 4, 1}, // 10x13
                    {1, 4, 0, 0, 0, 4, 0, 0, 0, 0, 1, 6, 1}, // at position 11,8
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            };

    public LvHardView(){

        setMaze(MAZE_HARD);
    }
}
