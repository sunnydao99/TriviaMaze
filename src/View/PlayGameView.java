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

    private final int RECT_SIZE = 35;
    private final int E_WALL = 1;
    private final int E_FAIL = 2;
    private final int E_PASS = 3;
    private final int E_DOOR = 4;
    private final int E_DEST = 5;
    private final int E_PATH = 8;


    private final int[][] maze =
            {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
             {1, 8, 1, 8, 1, 8, 1, 8, 8, 8, 4, 8, 1},
             {1, 8, 1, 8, 8, 8, 1, 8, 1, 1, 1, 8, 1},
             {1, 8, 4, 8, 1, 1, 1, 8, 8, 8, 8, 8, 1},
             {1, 8, 1, 8, 8, 8, 4, 8, 1, 1, 1, 8, 1},
             {1, 8, 1, 8, 1, 1, 1, 8, 1, 8, 8, 4, 1},
             {1, 8, 1, 8, 1, 8, 8, 8, 1, 1, 1, 8, 1},
             {1, 8, 1, 4, 1, 1, 1, 4, 1, 8, 1, 8, 1}, // 10x13
             {1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1, 5, 1}, // at position 11,8
             {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            };

   /* {0, 0, 0, 0},
    {0, 0, 0, 0},
    {0, 0, 0, 0},
    {0, 0, 0, 9}*/
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

    private int preX, preY;
    private int currentX, currentY;

    private RoomMCView viewMC;
    private RoomSAView viewSA;
    private RoomTFView viewTF;
    private String cate;
    private int id;

    public PlayGameView() {
        /*setTitle("Simple Maze");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/

        prepareGUI();
        viewMC = new RoomMCView();
        viewSA = new RoomSAView();
        viewTF = new RoomTFView();
        preX = 1;
        preY = 1;
        currentX = 1;
        currentY = 1;
        cate = "";
        id = 0;
    }

    private void prepareGUI() {

        this.setTitle("Simple Maze");
        this.setSize(640, 480);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }

    public void paint(Graphics g) {
        super.paint(g);

        g.translate(50, 50);

        //draw the maze
        int check = 0;


        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                Color color;
                switch (maze[row][col]) {
                    case E_WALL:
                        color = Color.GRAY;
                        break;
                    case E_DOOR:
                        color = Color.BLUE;
                        break;
                    case E_PASS:
                        color = Color.GREEN;
                        break;
                    case E_FAIL:
                        color = Color.BLACK;
                        break;
                    case E_DEST:
                        color = Color.RED;
                        break;
                    default:
                        color = Color.WHITE;
                }


                g.setColor(color);
                g.fillRect(RECT_SIZE * col, RECT_SIZE * row, RECT_SIZE, RECT_SIZE);
                g.setColor(Color.BLACK);
                g.drawRect(RECT_SIZE * col, RECT_SIZE * row, RECT_SIZE, RECT_SIZE);

                if (currentX == col && currentY == row) {
                    g.setColor(Color.PINK);
                    g.fillOval(RECT_SIZE * col, RECT_SIZE * row, RECT_SIZE, RECT_SIZE);
                }

                /*g.fillOval(60*col,85*row,30, 40);

                g.drawRect(60* col, 85*row, 30, 40);*/
            }
        }

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
    protected void processKeyEvent(KeyEvent ke) {
        boolean reDraw = false;
        if (ke.getID() != KeyEvent.KEY_PRESSED) {
            return;
        }

        if (cate.equals("MC")) {
            if (viewMC.checkAns == false) {
                maze[currentY][currentX] = E_FAIL;
                currentX = preX;
                currentY = preY;
            }
            else {
                maze[currentY][currentX] = E_PASS;
            }

            reDraw = true;
            cate = "";
        } else if (cate.equals("TF")) {
            if (viewTF.checkAns == false) {
                maze[currentY][currentX] = E_FAIL;
                currentX = preX;
                currentY = preY;
            }
            else {
                maze[currentY][currentX] = E_PASS;
            }

            reDraw = true;
            cate = "";
        } else if (cate.equals("SA")) {
            if (viewSA.checkAns == false) {
                maze[currentY][currentX] = E_FAIL;
                currentX = preX;
                currentY = preY;
            }
            else {
                maze[currentY][currentX] = E_PASS;
            }

            reDraw = true;
            cate = "";
        }

        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            if (currentY > 0) {
                if(maze[currentY - 1][currentX] > E_FAIL) {
                    preX = currentX;
                    preY = currentY;
                    currentY -= 1;
                    reDraw = true;
                }
            }
        } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            if (currentY < maze.length - 1) {
                if(maze[currentY + 1][currentX] > E_FAIL) {
                    preX = currentX;
                    preY = currentY;
                    currentY += 1;
                    reDraw = true;
                }
            }
        } else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            if (currentX > 0) {
                if(maze[currentY][currentX - 1] > E_FAIL) {
                    preX = currentX;
                    preY = currentY;
                    currentX -= 1;
                    reDraw = true;
                }
            }
        } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (currentX < maze[0].length - 1) {
                if(maze[currentY][currentX + 1] > E_FAIL) {
                    preX = currentX;
                    preY = currentY;
                    currentX += 1;
                    reDraw = true;
                }
            }
        }

        if (reDraw) {
            repaint();
            if(maze[currentY][currentX] == E_DOOR) {
                displayQuestion();
            }
        }
    }

    public void displayQuestion() {
        Room.randomIDCategory();
        id = Room.getLastID();
        cate = Room.getLastCategory();

        if (cate.equals("MC")) {
            viewMC = new RoomMCView(cate, id);
            viewMC.roomShow();
        } else if (cate.equals("TF")) {
            viewTF = new RoomTFView(cate, id);
            viewTF.roomShow();
        } else {
            viewSA = new RoomSAView(cate, id);
            viewSA.roomShow();
        }
    }
}
