package View;

import Model.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class LvMediumView extends JFrame {

    private final int RECT_SIZE = 60;
    private final int E_WALL = 1;
    private final int E_FAIL = 2;
    private final int E_PASS = 3;
    private final int E_DOOR = 4;
    private final int E_DEST = 5;
    private final int E_PATH = 8;

    private final int[][] MAZEMEDIUM =
            {       {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 8, 1, 8, 4, 8, 8, 8, 8, 8, 4, 8, 1},
                    {1, 8, 1, 8, 8, 8, 8, 4, 1, 1, 1, 8, 1},
                    {1, 8, 4, 8, 1, 1, 8, 8, 8, 8, 4, 8, 1},
                    {1, 4, 1, 4, 8, 8, 4, 8, 1, 1, 1, 8, 1},
                    {1, 8, 1, 8, 1, 1, 1, 8, 1, 8, 8, 4, 1},
                    {1, 8, 1, 8, 1, 8, 8, 8, 1, 8, 1, 8, 1},
                    {1, 8, 1, 4, 1, 1, 1, 4, 1, 4, 8, 4, 1}, // 10x13
                    {1, 4, 8, 8, 8, 4, 8, 8, 8, 8, 1, 5, 1}, // at position 11,8
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            };



    private int myPreX, myPreY;
    private int myCurrentX, myCurrentY;

    private RoomMCView myViewMC;
    private RoomSAView myViewSA;
    private RoomTFView myViewTF;
    private String myCate;
    private int myId;

    public LvMediumView() {
        /*setTitle("Simple Maze");
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/

        prepareGUI();
        myViewMC = new RoomMCView();
        myViewSA = new RoomSAView();
        myViewTF = new RoomTFView();
        myPreX = 1;
        myPreY = 1;
        myCurrentX = 1;
        myCurrentY = 1;
        myCate = "";
        myId = 0;
    }

    private void prepareGUI() {

        this.setTitle("Welcome to Medium");
        //this.setSize(640, 480);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }

    public void paint(Graphics g) {
        super.paint(g);

        g.translate(50, 50);

        //draw the maze
        int check = 0;


        for (int row = 0; row < MAZEMEDIUM.length; row++) {
            for (int col = 0; col < MAZEMEDIUM[0].length; col++) {
                Color color;
                switch (MAZEMEDIUM[row][col]) {
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

                if (myCurrentX == col && myCurrentY == row) {
                    g.setColor(Color.PINK);
                    g.fillOval(RECT_SIZE * col, RECT_SIZE * row, RECT_SIZE, RECT_SIZE);
                }


            }
        }


    }

    @Override
    protected void processKeyEvent(KeyEvent ke) {
        boolean reDraw = false;
        if (ke.getID() != KeyEvent.KEY_PRESSED) {
            return;
        }

        if (myCate.equals("MC")) {
            if (myViewMC.checkAns == false) {
                MAZEMEDIUM[myCurrentY][myCurrentX] = E_FAIL;
                myCurrentX = myPreX;
                myCurrentY = myPreY;
            }
            else {
                MAZEMEDIUM[myCurrentY][myCurrentX] = E_PASS;
            }

            reDraw = true;
            myCate = "";
        } else if (myCate.equals("TF")) {
            if (myViewTF.myCheckAns == false) {
                MAZEMEDIUM[myCurrentY][myCurrentX] = E_FAIL;
                myCurrentX = myPreX;
                myCurrentY = myPreY;
            }
            else {
                MAZEMEDIUM[myCurrentY][myCurrentX] = E_PASS;
            }

            reDraw = true;
            myCate = "";
        } else if (myCate.equals("SA")) {
            if (myViewSA.myCheckAns == false) {
                MAZEMEDIUM[myCurrentY][myCurrentX] = E_FAIL;
                myCurrentX = myPreX;
                myCurrentY = myPreY;
            }
            else {
                MAZEMEDIUM[myCurrentY][myCurrentX] = E_PASS;
            }

            reDraw = true;
            myCate = "";
        }

        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            if (myCurrentY > 0) {
                if(MAZEMEDIUM[myCurrentY - 1][myCurrentX] > E_FAIL) {
                    myPreX = myCurrentX;
                    myPreY = myCurrentY;
                    myCurrentY -= 1;
                    reDraw = true;
                }
            }
        } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            if (myCurrentY < MAZEMEDIUM.length - 1) {
                if(MAZEMEDIUM[myCurrentY + 1][myCurrentX] > E_FAIL) {
                    myPreX = myCurrentX;
                    myPreY = myCurrentY;
                    myCurrentY += 1;
                    reDraw = true;
                }
            }
        } else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            if (myCurrentX > 0) {
                if(MAZEMEDIUM[myCurrentY][myCurrentX - 1] > E_FAIL) {
                    myPreX = myCurrentX;
                    myPreY = myCurrentY;
                    myCurrentX -= 1;
                    reDraw = true;
                }
            }
        } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (myCurrentX < MAZEMEDIUM[0].length - 1) {
                if(MAZEMEDIUM[myCurrentY][myCurrentX + 1] > E_FAIL) {
                    myPreX = myCurrentX;
                    myPreY = myCurrentY;
                    myCurrentX += 1;
                    reDraw = true;
                }
            }
        }

        if (reDraw) {
            repaint();
            if(MAZEMEDIUM[myCurrentY][myCurrentX] == E_DOOR) {
                displayQuestion();
            }
        }
    }

    public void displayQuestion() {
        Room.randomIDCategory();
        myId = Room.getLastID();
        myCate = Room.getLastCategory();

        if (myCate.equals("MC")) {
            myViewMC = new RoomMCView(myCate, myId);
            myViewMC.roomShow();
        } else if (myCate.equals("TF")) {
            myViewTF = new RoomTFView(myCate, myId);
            myViewTF.roomShow();
        } else {
            myViewSA = new RoomSAView(myCate, myId);
            myViewSA.roomShow();
        }
    }
}

