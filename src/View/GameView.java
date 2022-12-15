package View;

import Model.Maze;
import Model.Room;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;

/**
 * @author: An Nguyen, Satinder
 * @version: 11/20/2022, updated 12/14/2022
 *
 */

/**
 * GameView class create maze and create action for Arrow keyboards
 */
public class GameView extends JFrame implements Serializable {

    private final int E_PATH = 0;
    private final int E_WALL = 1;
    private final int E_FAIL = 2;
    private final int E_PASS = 3;
    private final int E_DOOR = 4;
    private final int E_BEGIN = 5;
    private final int E_DEST = 6;

    private int myPreX, myPreY = 1;
    private int myCurrentX = 1, myCurrentY = 1;

    private RoomMCView myViewMC;
    private RoomSAView myViewSA;
    private RoomTFView myViewTF;
    private String myCate = "";
    private int myId;
    int[][] myMaze = new int[][]{
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 5, 1, 0, 4, 0, 0, 0, 0, 0, 4, 0, 1},
            {1, 0, 1, 0, 0, 0, 0, 4, 1, 1, 1, 0, 1},
            {1, 0, 4, 0, 1, 1, 0, 0, 0, 0, 4, 0, 1},
            {1, 0, 1, 0, 0, 0, 4, 0, 1, 1, 1, 0, 1},
            {1, 0, 1, 0, 1, 1, 1, 0, 4, 0, 0, 4, 1},
            {1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1},
            {1, 0, 1, 4, 1, 1, 1, 4, 1, 4, 0, 0, 1},
            {1, 4, 0, 0, 0, 4, 0, 0, 0, 0, 1, 6, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };
    private int myRectSize = Maze.myRectSize();
    private String fileName = "Assets/StoredData.txt";
    private static final long serialVersionUID = 1234567890L;

    /**
     * GameView(int): constructor pass index x to initialize level of maze
     * @param x: level
     * @throws FileNotFoundException
     */
    public GameView(int x) throws FileNotFoundException {
        if(x == 3){
            myMaze = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 5, 1, 0, 4, 0, 0, 0, 0, 0, 4, 0, 1},
                    {1, 0, 1, 0, 1, 0, 0, 4, 1, 1, 1, 0, 1},
                    {1, 0, 4, 0, 1, 1, 0, 0, 0, 0, 4, 0, 1},
                    {1, 4, 1, 4, 0, 0, 4, 0, 1, 1, 1, 0, 1},
                    {1, 0, 1, 0, 1, 1, 1, 0, 4, 0, 0, 4, 1},
                    {1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1},
                    {1, 0, 1, 4, 1, 1, 1, 4, 1, 4, 0, 4, 1},
                    {1, 4, 0, 0, 0, 4, 0, 0, 0, 0, 1, 6, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            };
            this.setTitle("Welcome to Trivia Maze for Expert");

        }if(x == 2){

            myMaze = new int[][]{
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                    {1, 5, 1, 0, 4, 0, 0, 0, 0, 0, 4, 0, 1},
                    {1, 0, 1, 0, 0, 0, 0, 4, 1, 1, 1, 0, 1},
                    {1, 0, 4, 0, 1, 1, 0, 0, 0, 0, 4, 0, 1},
                    {1, 4, 1, 4, 0, 0, 4, 0, 1, 1, 1, 0, 1},
                    {1, 0, 1, 0, 1, 1, 1, 0, 4, 0, 0, 4, 1},
                    {1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1},
                    {1, 0, 1, 4, 1, 1, 1, 4, 1, 4, 0, 0, 1},
                    {1, 4, 0, 0, 0, 4, 0, 0, 0, 0, 1, 6, 1},
                    {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
            };
            this.setTitle("Welcome to Trivia Maze for Medium");
        }
        if(x == 1){
            this.setTitle("Welcome to Trivia Maze for Beginner");
        }

        prepareGUI();
        myViewMC = new RoomMCView();
        myViewSA = new RoomSAView();
        myViewTF = new RoomTFView();

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(GameView.super.getOwner(),
                        "Would you like to save your game?", "Save Game?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    try {
                        FileOutputStream file = new FileOutputStream(fileName);
                        ObjectOutputStream out = new ObjectOutputStream(file);

                        out.writeObject(myMaze);
                        out.writeObject(myCurrentX);
                        out.writeObject(myCurrentY);

                        out.close();
                        file.close();
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

    }

    /**
     * GameView(int[][], int, int): Constructor pass three parameters such as
     * maze, position x, and position y
     * @param mySavedMaze: maze
     * @param mySavedX: saved position x
     * @param mySavedY: saved position y
     * @throws FileNotFoundException
     */
    public GameView(int[][] mySavedMaze, int mySavedX, int mySavedY) throws FileNotFoundException {
        this.myMaze = mySavedMaze;
        this.myCurrentX = mySavedX;
        this.myCurrentY = mySavedY;
        prepareGUI();
        myViewMC = new RoomMCView();
        myViewSA = new RoomSAView();
        myViewTF = new RoomTFView();
    }

    /**
     * prepareGUI(): set attribute of main frame
     */
    private void prepareGUI() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }


    /**
     * paint(): to draw maze by using Graphic
     * @param g
     */
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

    /**
     * processKeyEvent(KeyEvent): overrider processKeyEvent to move keyboard and display Question
     * @param ke
     */
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
                if(canGo(myMaze[myCurrentY - 1][myCurrentX])) {
                    myPreX = myCurrentX;
                    myPreY = myCurrentY;
                    myCurrentY -= 1;
                    reDraw = true;
                }
            }
        } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            if (myCurrentY < myMaze.length - 1) {
                if(canGo(myMaze[myCurrentY + 1][myCurrentX])) {
                    myPreX = myCurrentX;
                    myPreY = myCurrentY;
                    myCurrentY += 1;
                    reDraw = true;
                }
            }
        } else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            if (myCurrentX > 0) {
                if(canGo(myMaze[myCurrentY][myCurrentX - 1])) {
                    myPreX = myCurrentX;
                    myPreY = myCurrentY;
                    myCurrentX -= 1;
                    reDraw = true;
                }
            }
        } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (myCurrentX < myMaze[0].length - 1) {
                if(canGo(myMaze[myCurrentY][myCurrentX + 1])) {
                    myPreX = myCurrentX;
                    myPreY = myCurrentY;
                    myCurrentX += 1;
                    reDraw = true;
                }
            }
        }

        if (reDraw) {
            repaint();
            if(searchPath()==false){
                actionGameOver();
            }

            if(myMaze[myCurrentY][myCurrentX] == E_DOOR) {
                displayQuestion();

            }
            else if(myMaze[myCurrentY][myCurrentX] == E_DEST){
                actionWinner();
            }

        }
    }

    /**
     * actionGameOver(): display sound and icon when game over
     */
    private void actionGameOver() {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("Assets/gameover.wav"));
            final Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.setFramePosition(0);
            clip.start();
            String text = "Game Over!";
            ImageIcon icon = new ImageIcon("Assets/gameover.png");
            JOptionPane.showMessageDialog(null, text, "Check^^", JOptionPane.INFORMATION_MESSAGE, icon);

        }
        catch (LineUnavailableException ln){
            System.out.println(ln);
        }
        catch (IOException e){
            System.out.println(e);
        }
        catch (UnsupportedAudioFileException un){
            System.out.println(un);
        }

    }

    /**
     * actionWinner(): display sound and icon when players win
     */
    private void actionWinner() {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("Assets/winner.wav"));
            final Clip clip = AudioSystem.getClip();
            clip.open(ais);
            clip.setFramePosition(0);
            clip.start();
            String text = "Congratulations! You're win";
            ImageIcon icon = new ImageIcon("Assets/championEdit1.jpeg");
            JOptionPane.showMessageDialog(null, text, "Check^^", JOptionPane.INFORMATION_MESSAGE, icon);
        }
        catch (LineUnavailableException ln){
            System.out.println(ln);
        }
        catch (IOException e){
            System.out.println(e);
        }
        catch (UnsupportedAudioFileException un){
            System.out.println(un);
        }

    }

    /**
     * canGo(int): if position have path, player can move so return true;
     * otherwise, return false
     * @param position
     * @return
     */
    public boolean canGo(int position) {
        if(position > E_FAIL || position == E_PATH)
            return true;
        else
            return false;
    }

    /**
     * searchPath(): search path for player and return true if still have path, and otherwise return false.
     * Copy current maze into temporary maze ,
     * and then check all directions North, South, East, West.
     * If player can go, increase position by 1. If not, position will be filled by WALL
     *
     * @return: boolean
     */
    public boolean searchPath() {
        int nDir, row, col;
        boolean done = false;
        int[][] temp = new int[myMaze.length][myMaze[0].length];
        for (row = 0; row < myMaze.length; row++) {
            for (col = 0; col < myMaze[0].length; col++) {
                temp[row][col] = myMaze[row][col];
            }
        }

        while(!done) {
            done = true;
            for (row = 0; row < temp.length; row++) {
                for (col = 0; col < temp[0].length; col++) {
                    if (temp[row][col] != E_BEGIN && temp[row][col] != E_DEST && canGo(temp[row][col])) {
                        nDir = 0;
                        if (row > 0 && canGo(temp[row - 1][col]))
                            nDir++;
                        if (row < temp.length - 1 && canGo(temp[row + 1][col]))
                            nDir++;
                        if (col > 0 && canGo(temp[row][col - 1]))
                            nDir++;
                        if (col < temp[0].length - 1 && canGo(temp[row][col + 1]))
                            nDir++;

                        if (nDir <= 1) {
                            done = false;
                            temp[row][col] = E_WALL;
                        }
                    }
                }
            }
        }

        row = 1;
        col = 1;
        do {
            nDir = 0;
            if (row < temp.length - 1 && canGo(temp[row + 1][col])) { // down
                row++;
                nDir = 1;
            }
            else if (col < temp[0].length - 1 && canGo(temp[row][col + 1])) { // right
                col++;
                nDir = 1;
            }
            else if (row > 0 && canGo(temp[row - 1][col])) { // up
                row--;
                nDir = 1;
            }
            else if (col > 0 && canGo(temp[row][col - 1])) { // left
                col--;
                nDir = 1;
            }

            if(temp[row][col] == E_DEST)
                break;
            else if(nDir == 1) // still 1 way
                temp[row][col] = E_WALL; // fill wall
        } while (nDir != 0);

        return temp[row][col] == E_DEST;
    }

    /**
     * displayQuestion(): get all information of question after random both category and ID from Room class
     */
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
