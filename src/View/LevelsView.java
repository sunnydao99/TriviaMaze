package View;

import Model.Room;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;

/**
 * @author: An Nguyen
 * @version: 12/22/2022
 *
 */

public class LevelsView extends JFrame {

    private final int RECT_SIZE = 35;
    private final int E_PATH = 0;
    private final int E_WALL = 1;
    private final int E_FAIL = 2;
    private final int E_PASS = 3;
    private final int E_DOOR = 4;
    private final int E_BEGIN = 5;
    private final int E_DEST = 6;


    private  int[][] maze =
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


    private int myPreX, myPreY;
    private int myCurrentX, myCurrentY;

    private RoomMCView myViewMC;
    private RoomSAView myViewSA;
    private RoomTFView myViewTF;
    private String myCate;
    private int myId;
    private String fileName = "Assets/StoredData.txt";
    private ArrayList<String> myQues;
    private String myAns;
    private ArrayList<String>  myCorrectAns;

    public LevelsView() {
        myQues = new ArrayList<String>();
        myCorrectAns = new ArrayList<String>();
        myViewMC = new RoomMCView();
        myViewSA = new RoomSAView();
        myViewTF = new RoomTFView();
        myPreX = 1;
        myPreY = 1;
        myCurrentX = 1;
        myCurrentY = 1;
        myCate = "";
        myId = 0;
        prepareGUI();
    }

    private void prepareGUI() {

        this.setTitle("Welcome to the Challenge");
        this.setSize(640, 480);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(LevelsView.super.getOwner(),
                        "Would you like to save your game?", "Save Game?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    try {
                        FileOutputStream file = new FileOutputStream(fileName);
                        ObjectOutputStream out = new ObjectOutputStream(file);

                        out.writeObject(maze);
                        out.writeObject(myCurrentX);
                        out.writeObject(myCurrentY);

                        String st = printQues();
                        char ch[] = st.toCharArray();
                        for (int i = 0; i < st.length(); i++) {

                            // we will write the string by writing each
                            // character one by one to file
                            out.writeObject(ch[i]);
                        }

                        //out.writeObject(myGameLevel);

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

                if (myCurrentX == col && myCurrentY == row) {
                    g.setColor(Color.PINK);
                    g.fillOval(RECT_SIZE * col, RECT_SIZE * row, RECT_SIZE, RECT_SIZE);
                }

            }
        }


    }

    public void setMaze(int[][] theMaze){

        for (int row = 0; row < theMaze.length; row++) {
            for (int col = 0; col < theMaze[0].length; col++) {
                maze[row][col] = theMaze[row][col];
            }
        }

    }

    @Override
    protected void processKeyEvent(KeyEvent ke) {

        boolean reDraw = false;
        //System.out.println(this.isActive());
        if (ke.getID() != KeyEvent.KEY_PRESSED) {
            return;
        }

        if (myCate.equals("MC")) {
            if (myViewMC.getMyCheckAns() == false) {
                maze[myCurrentY][myCurrentX] = E_FAIL;
                myCurrentX = myPreX;
                myCurrentY = myPreY;
            }
            else {
                maze[myCurrentY][myCurrentX] = E_PASS;
            }

            reDraw = true;
            myCate = "";
        } else if (myCate.equals("TF")) {
            if (myViewTF.myCheckAns == false) {
                maze[myCurrentY][myCurrentX] = E_FAIL;
                myCurrentX = myPreX;
                myCurrentY = myPreY;
            }
            else {
                maze[myCurrentY][myCurrentX] = E_PASS;
            }

            reDraw = true;
            myCate = "";
        } else if (myCate.equals("SA")) {
            if (myViewSA.myCheckAns == false) {
                maze[myCurrentY][myCurrentX] = E_FAIL;
                myCurrentX = myPreX;
                myCurrentY = myPreY;
            }
            else {
                maze[myCurrentY][myCurrentX] = E_PASS;
            }

            reDraw = true;
            myCate = "";
        }

        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            if (myCurrentY > 0) {
                if(canGo(maze[myCurrentY - 1][myCurrentX])) {
                    myPreX = myCurrentX;
                    myPreY = myCurrentY;
                    myCurrentY -= 1;
                    reDraw = true;
                }
            }
        } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            if (myCurrentY < maze.length - 1) {
                if(canGo(maze[myCurrentY + 1][myCurrentX])) {
                    myPreX = myCurrentX;
                    myPreY = myCurrentY;
                    myCurrentY += 1;
                    reDraw = true;
                }
            }
        } else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            if (myCurrentX > 0) {
                if(canGo(maze[myCurrentY][myCurrentX - 1])) {
                    myPreX = myCurrentX;
                    myPreY = myCurrentY;
                    myCurrentX -= 1;
                    reDraw = true;
                }
            }
        } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (myCurrentX < maze[0].length - 1) {
                if(canGo(maze[myCurrentY][myCurrentX + 1])) {
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

            if(maze[myCurrentY][myCurrentX] == E_DOOR) {
                displayQuestion();

            }
            else if(maze[myCurrentY][myCurrentX] == E_DEST){
                actionWinner();
            }

        }
    }

    public void displayQuestion() {
        Room.randomIDCategory();
        myId = Room.getLastID();
        myCate = Room.getLastCategory();
        String tempQues = "";
        String tempCorrect = "";
        if (myCate.equals("MC")) {
            myViewMC = new RoomMCView(myCate, myId);
            myViewMC.roomShow();
            tempQues = myViewMC.displayQuestion(myCate, myId);
            tempCorrect = myViewMC.displayAnswer(myCate, myId);

        } else if (myCate.equals("TF")) {
            myViewTF = new RoomTFView(myCate, myId);
            myViewTF.roomShow();
            tempQues = myViewTF.displayQuestion(myCate, myId);
            tempCorrect = myViewTF.displayAnswer(myCate, myId);
        } else {
            myViewSA = new RoomSAView(myCate, myId);
            myViewSA.roomShow();
            tempQues = myViewSA.displayQuestion(myCate, myId);
            tempCorrect = myViewSA.displayAnswer(myCate, myId);
        }
        myQues.add(tempQues);
        myCorrectAns.add(tempCorrect);
    }


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


    public boolean canGo(int position) {
        if(position > E_FAIL || position == E_PATH)
            return true;
        else
            return false;
    }

    public boolean searchPath() {
        int nDir, row, col;
        boolean done = false;
        int[][] temp = new int[maze.length][maze[0].length];
        for (row = 0; row < maze.length; row++) {
            for (col = 0; col < maze[0].length; col++) {
                temp[row][col] = maze[row][col];
            }
        }

       /* System.out.println("maze: ");
        print(maze);*/

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
            if (row < temp.length - 1 && canGo(temp[row + 1][col])) {
                row++;
                nDir = 1;
            }
            else if (col < temp[0].length - 1 && canGo(temp[row][col + 1])) {
                col++;
                nDir = 1;
            }
            else if (row > 0 && canGo(temp[row - 1][col])) {
                row--;
                nDir = 1;
            }
            else if (col > 0 && canGo(temp[row][col - 1])) {
                col--;
                nDir = 1;
            }

            if(temp[row][col] == E_DEST)
                break;
            else if(nDir == 1)
                temp[row][col] = E_WALL;
        } while (nDir != 0);

        /*System.out.println("temp: ");
        print(temp);*/

        return temp[row][col] == E_DEST;
    }

    public void print(int[][] values) {
        for(int row = 0; row < values.length; row++) {
            for(int col = 0; col < values[0].length; col++) {
                System.out.print(values[row][col] + " ");
            }
            System.out.println();
        }
    }

    public String printQues() {
        String temp = "";
        for (int i = 0; i < myQues.size(); i++) {

            if (i == (myQues.size() - 1)) {
                System.out.print(temp + myQues.get(myQues.size() - 1));
            } else {
                System.out.print(temp + myQues.get(i) + ", ");
            }
        }
        temp = "";
        System.out.println(temp);
        return temp;
    }

    public String printCorrect() {
        String temp = "";
        for (int i = 0; i < myCorrectAns.size(); i++) {

            if (i == (myCorrectAns.size() - 1)) {
                System.out.print(temp + myCorrectAns.get(myCorrectAns.size() - 1));
            } else {
                System.out.print(temp + myCorrectAns.get(i) + ", ");
            }
        }
        temp = "";
        System.out.println(temp);
        return temp;
    }

}
