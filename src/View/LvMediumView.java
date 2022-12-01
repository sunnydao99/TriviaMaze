package View;

import Model.Room;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


public class LvMediumView extends JFrame {

    private final int RECT_SIZE = 35;
    private final int E_WALL = 1;
    private final int E_FAIL = 2;
    private final int E_PASS = 3;
    private final int E_DOOR = 4;
    private final int E_DEST = 5;
    private final int E_PATH = 8;

    private final int[][] mazeMedium =
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



    private int preX, preY;
    private int currentX, currentY;

    private RoomMCView viewMC;
    private RoomSAView viewSA;
    private RoomTFView viewTF;
    private String cate;
    private int id;

    public LvMediumView() {
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

        this.setTitle("Welcome to Medium");
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


        for (int row = 0; row < mazeMedium.length; row++) {
            for (int col = 0; col < mazeMedium[0].length; col++) {
                Color color;
                switch (mazeMedium[row][col]) {
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


            }
        }


    }

    @Override
    protected void processKeyEvent(KeyEvent ke) {
        boolean reDraw = false;
        if (ke.getID() != KeyEvent.KEY_PRESSED) {
            return;
        }

        if (cate.equals("MC")) {
            if (viewMC.checkAns == false) {
                mazeMedium[currentY][currentX] = E_FAIL;
                currentX = preX;
                currentY = preY;
            }
            else {
                mazeMedium[currentY][currentX] = E_PASS;
            }

            reDraw = true;
            cate = "";
        } else if (cate.equals("TF")) {
            if (viewTF.checkAns == false) {
                mazeMedium[currentY][currentX] = E_FAIL;
                currentX = preX;
                currentY = preY;
            }
            else {
                mazeMedium[currentY][currentX] = E_PASS;
            }

            reDraw = true;
            cate = "";
        } else if (cate.equals("SA")) {
            if (viewSA.checkAns == false) {
                mazeMedium[currentY][currentX] = E_FAIL;
                currentX = preX;
                currentY = preY;
            }
            else {
                mazeMedium[currentY][currentX] = E_PASS;
            }

            reDraw = true;
            cate = "";
        }

        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            if (currentY > 0) {
                if(mazeMedium[currentY - 1][currentX] > E_FAIL) {
                    preX = currentX;
                    preY = currentY;
                    currentY -= 1;
                    reDraw = true;
                }
            }
        } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
            if (currentY < mazeMedium.length - 1) {
                if(mazeMedium[currentY + 1][currentX] > E_FAIL) {
                    preX = currentX;
                    preY = currentY;
                    currentY += 1;
                    reDraw = true;
                }
            }
        } else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            if (currentX > 0) {
                if(mazeMedium[currentY][currentX - 1] > E_FAIL) {
                    preX = currentX;
                    preY = currentY;
                    currentX -= 1;
                    reDraw = true;
                }
            }
        } else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (currentX < mazeMedium[0].length - 1) {
                if(mazeMedium[currentY][currentX + 1] > E_FAIL) {
                    preX = currentX;
                    preY = currentY;
                    currentX += 1;
                    reDraw = true;
                }
            }
        }

        if (reDraw) {
            repaint();
            if(mazeMedium[currentY][currentX] == E_DOOR) {
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

