package View;

import Model.Maze;
import Model.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;


public class GameView extends JFrame implements Serializable {

    private final int E_WALL = 1;
    private final int E_FAIL = 2;
    private final int E_PASS = 3;
    private final int E_DOOR = 4;
    private final int E_DEST = 5;
    private final int E_PATH = 8;
    private int myPreX, myPreY = 1;
    private int myCurrentX = 1, myCurrentY = 1;

    private JPanel myPanelHolder;
    private JButton mySaveButton;
    private RoomMCView myViewMC;
    private RoomSAView myViewSA;
    private RoomTFView myViewTF;
    private String myCate = "";
    private int myId, myGameDifficulty;
    int[][] myMaze = Model.Maze.getMAZE();
    private int myRectSize = Maze.myRectSize();
    private String fileName = "StoredData.mze";
    private static final long serialVersionUID = 1234567890L;

    public GameView(int x) throws FileNotFoundException {
        if(x == 3){
            myGameDifficulty = x;
            myMaze[5][1] = 4;
            myMaze[5][3] = 4;
            myMaze[3][8] = 4;
            myMaze[7][11] = 4;
            myMaze[8][8] = 4;
            myMaze[2][4] = 1;
        }if(x == 2){
            myGameDifficulty = x;
            //myMaze[5][1] = 4;
            myMaze[5][3] = 4;
            myMaze[3][8] = 4;
            //myMaze[7][11] = 4;
            myMaze[8][8] = 4;
            myMaze[2][4] = 1;
        }

        prepareGUI();
        myViewMC = new RoomMCView();
        myViewSA = new RoomSAView();
        myViewTF = new RoomTFView();
//        myPreX = 1;
//        myPreY = 1;
//        myCurrentX = 1;
//        myCurrentY = 1;
//        myCate = "";
//        myId = 0;

        //Saving the state
//        try{
//            FileOutputStream file = new FileOutputStream(fileName);
//            ObjectOutputStream out = new ObjectOutputStream(file);
//
//            out.writeObject(myMaze);
//            out.writeObject(myCurrentX);
//            out.writeObject(myCurrentY);
//
//            out.close();
//            file.close();
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    public GameView(int mySavedDifficulty, int[][] mySavedMaze, int mySavedX, int mySavedY) throws FileNotFoundException {
        this.myMaze = mySavedMaze;
        this.myCurrentX = mySavedX;
        this.myCurrentY = mySavedY;
        new GameView(mySavedDifficulty);
    }

    private void prepareGUI() {

        this.setTitle("Welcome to Trivia Maze");
        myPanelHolder = new JPanel();
        myPanelHolder.setBackground(Color.cyan);
        mySaveButton = new JButton("Save Game");
        mySaveButton.setLocation(800,400);
        myPanelHolder.add(mySaveButton);
        this.add(myPanelHolder);
//        JButton mySavedGame = new JButton("Saved Game");
        mySaveButton.addActionListener(e -> {
            try{
                FileOutputStream file = new FileOutputStream(fileName);
                ObjectOutputStream out = new ObjectOutputStream(file);

                out.writeObject(myMaze);
                out.writeObject(myCurrentX);
                out.writeObject(myCurrentY);
                out.writeObject(myGameDifficulty);

                out.close();
                file.close();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
//        this.setSize(640, 480);
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


        for (int row = 0; row < myMaze.length; row++) {
            for (int col = 0; col < myMaze[0].length; col++) {
                Color color;
                switch (myMaze[row][col]) {
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
                g.fillRect(myRectSize * col, myRectSize * row, myRectSize, myRectSize);
                g.setColor(Color.BLACK);
                g.drawRect(myRectSize * col, myRectSize * row, myRectSize, myRectSize);

                if (myCurrentX == col && myCurrentY == row) {
                    g.setColor(Color.PINK);
                    g.fillOval(myRectSize * col, myRectSize * row, myRectSize, myRectSize);
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
            if (myViewMC.getMyCheckAns() == false) {
                myMaze[myCurrentY][myCurrentX] = E_FAIL;
                myCurrentX = myPreX;
                myCurrentY = myPreY;
            }
            else {
                myMaze[myCurrentY][myCurrentX] = E_PASS;
            }

            reDraw = true;
            myCate = "";
        } else if (myCate.equals("TF")) {
            if (myViewTF.myCheckAns == false) {
                myMaze[myCurrentY][myCurrentX] = E_FAIL;
                myCurrentX = myPreX;
                myCurrentY = myPreY;
            }
            else {
                myMaze[myCurrentY][myCurrentX] = E_PASS;
            }

            reDraw = true;
            myCate = "";
        } else if (myCate.equals("SA")) {
            if (myViewSA.myCheckAns == false) {
                myMaze[myCurrentY][myCurrentX] = E_FAIL;
                myCurrentX = myPreX;
                myCurrentY = myPreY;
            }
            else {
                myMaze[myCurrentY][myCurrentX] = E_PASS;
            }

            reDraw = true;
            myCate = "";
        }

        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            if (myCurrentY > 0) {
                if(myMaze[myCurrentY - 1][myCurrentX] > E_FAIL) {
                    myPreX = myCurrentX;
                    myPreY = myCurrentY;
                    myCurrentY -= 1;
                    reDraw = true;
                }
            }
        } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            if (myCurrentY < myMaze.length - 1) {
                if(myMaze[myCurrentY + 1][myCurrentX] > E_FAIL) {
                    myPreX = myCurrentX;
                    myPreY = myCurrentY;
                    myCurrentY += 1;
                    reDraw = true;
                }
            }
        } else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            if (myCurrentX > 0) {
                if(myMaze[myCurrentY][myCurrentX - 1] > E_FAIL) {
                    myPreX = myCurrentX;
                    myPreY = myCurrentY;
                    myCurrentX -= 1;
                    reDraw = true;
                }
            }
        } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (myCurrentX < myMaze[0].length - 1) {
                if(myMaze[myCurrentY][myCurrentX + 1] > E_FAIL) {
                    myPreX = myCurrentX;
                    myPreY = myCurrentY;
                    myCurrentX += 1;
                    reDraw = true;
                }
            }
        }

        if (reDraw) {
            repaint();
            if(myMaze[myCurrentY][myCurrentX] == E_DOOR) {
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
