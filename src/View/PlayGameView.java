package View;

import Model.Room;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class PlayGameView extends JFrame {
    /*Value 0: not visited node
            1: wall (blocked)
            2: visited node
            9: target node
    */


    private int[][] maze =
            {       {0, 0, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 0},
                    {0, 0, 0, 9}


            };

    /*{1,1,1,1,1,1,1,1,1,1,1,1,1},
    {1,0,1,0,1,0,1,0,0,0,0,0,1},
    {1,0,1,0,0,0,1,0,1,1,1,0,1},
    {1,0,0,0,1,1,1,0,0,0,0,0,1},
    {1,0,1,0,0,0,0,0,1,1,1,0,1},
    {1,0,1,0,1,1,1,0,1,0,0,0,1},
    {1,0,1,0,1,0,0,0,1,1,1,0,1},
    {1,0,1,0,1,1,1,0,1,0,1,0,1}, //10x13
    {1,0,0,0,0,0,0,0,0,0,1,9,1}, // at position 11,8
    {1,1,1,1,1,1,1,1,1,1,1,1,1}*/

    private final List<Integer> path = new ArrayList<Integer>();
    private int currentX;
    private int currentY;
    private int pathIndex;
    private RoomMCView viewMC;
    private RoomSAView viewSA;
    private RoomTFView viewTF;
    private String cate;
    private int id;

    public PlayGameView(){
        /*setTitle("Simple Maze");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/

        prepareGUI();
        Room.randomIDCategory();
        viewMC = new RoomMCView();
        viewSA = new RoomSAView();
        viewTF = new RoomTFView();
    }

    private void prepareGUI(){

        this.setTitle("Simple Maze");
        this.setSize(640, 480);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    public void paint(Graphics g){
        super.paint(g);

        g.translate(50, 50);
        //draw the maze
        int check = 0;



        for(int row = 0; row < maze.length; row++){
            for(int col = 0; col < maze[0].length; col++){
                Color color;
                switch (maze[row][col]){
                    case 1: color = Color.GRAY; break;
                    case 2: color = Color.BLUE; break;
                    case 9: color = Color.RED; break;
                    default: color = Color.WHITE;
                }
                if(currentX == col && currentY == row){
                    color = Color.PINK;
                }

                g.setColor(color);
                g.fillRect(70*col, 70*row, 70, 70);
                g.setColor(Color.BLACK);
                g.drawRect(70* col, 70*row, 70, 70);
            }
        }

        cate = Room.myCategoryList.get(currentY*4 + currentX);
        id = Room.myIdList.get(currentY*4 + currentX);
        //goToDoor("MC", 1);

        goToDoor(cate, id );


        // draw the path list
        /*for(int p = 0; p < path.size(); p +=2){
            int pathX = path.get(p);
            int pathY = path.get(p+1);
            g.setColor(Color.GREEN);
            g.fillRect(pathX*30, pathY*30, 30 , 30 );
        }

        //draw the ball on path
        int pathX = path.get(pathIndex);
        int pathY = path.get(pathIndex + 1);
        g.setColor(Color.RED);
        g.fillOval(pathX * 30, pathY *30, 30, 30);*/

    }

    @Override
    protected void processKeyEvent(KeyEvent ke){
        if(ke.getID() != KeyEvent.KEY_PRESSED){
            return;
        }

        if(cate.equals("MC")) {
            if (viewMC.checkAns == false) {
                maze[currentY][currentX] = 1;
            }
        }
        else if(cate.equals("TF")){
            if(viewTF.checkAns == false){
                maze[currentY][currentX] = 1;
            }
        }
        else {
            if(viewSA.checkAns == false){
                maze[currentY][currentX] = 1;
            }
        }

        if( ke.getKeyCode() == KeyEvent.VK_UP){
            if(currentY > 0)
                currentY -= 1;
        }
        else if(ke.getKeyCode() == KeyEvent.VK_DOWN){
            if(currentY < maze.length - 1)
            currentY += 1;
        }
        else if(ke.getKeyCode() == KeyEvent.VK_LEFT){
            if(currentX > 0)
            currentX -= 1;
        }
        else if(ke.getKeyCode() == KeyEvent.VK_RIGHT){
            if(currentX < maze[0].length -1)
            currentX += 1;
        }


        repaint();
    }

    public void goToDoor(String theCate, int theId){
        if(theCate.equals("MC")){
             viewMC = new RoomMCView(theCate, theId);
             viewMC.roomShow();
        }else if(theCate.equals("TF")){
            viewTF = new RoomTFView(theCate, theId);
            viewTF.roomShow();
        }
        else {
            viewSA = new RoomSAView(theCate, theId);
            viewSA.roomShow();
        }

    }

}
