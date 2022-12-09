package Model;

import java.io.Serializable;
import java.util.List;

public class Maze implements Serializable {
    private static final long serialVersionUID = 1234567890L;

    //if path was found, this method will return true
    // and the path list will be filled

    public static boolean moving(int[][] maze, int x, int y, List<Integer> path){

        if(maze[y][x] == 9){
            path.add(x);
            path.add(y);
            return true;
        }

        //


        //when the current position (x and y) is
        // not - visited node (0), then let's
        // mark it as visited (2)
        /*if(maze[y][x] == 0){
            maze[y][x] = 2;

            //now, let's visit all neighbor nodes recursively.
            //if path was found, let's fill the path list
            // with current position
            int dx = -1;
            int dy = 0;

            if(searchPath(maze, x + dx, y + dy, path)){
                path.add(x);
                path.add(y);
                return true;
            }
            dx = 1;
            dy = 0;
            if(searchPath(maze, x + dx, y + dy, path)){
                path.add(x);
                path.add(y);
                return true;
            }

            dx = 0;
            dy = -1;
            if(searchPath(maze, x + dx, y + dy, path)){
                path.add(x);
                path.add(y);
                return true;
            }

            dx = 0;
            dy = 1;
            if(searchPath(maze, x + dx, y + dy, path)){
                path.add(x);
                path.add(y);
                return true;
            }*/

        //}
        return false;
    }
}
