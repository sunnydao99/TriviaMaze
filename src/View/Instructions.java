package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Instructions {
    private JFrame myInstFrame;
    public Instructions(){
        prepareGUI();
    }
    private void prepareGUI(){
        myInstFrame = new JFrame("Instructions on How To Play");
        myInstFrame.setSize(800,700);
        //myInstFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //myInstFrame.setUndecorated(true);
        myInstFrame.setLayout(new BorderLayout());
        myInstFrame.setBackground(Color.getHSBColor(240,100,72));
        myInstFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        myInstFrame.setVisible(true);

        String inst = " Thank you for playing Trivia Maze! \n In this page " +
                "we will go over how to play this game. \n\n The Player starts out at the "  +
                "top left of the maze grid and has to exit\n at the bottom right "  +
                "by answering trivia questions related \n to computer science.\n" +
                " Player will transverse the maze using Door buttons located \n" +
                " in each Room. Clicking the Door will spawn a question of type \n" +
                " True/False (TF), Multiple Choice(MC), or Short Answer(SA). \n" +
                " Answering the questions correctly unlocks the Door to move \n" +
                " to the adjacent Room.\n" +
                " Reach the Exit to complete the game and Have Fun!";

        JTextArea textArea = new JTextArea(inst);
        textArea.setForeground(Color.getHSBColor(240,100,72));
        Font instFont = new Font("Instruction Font", Font.ITALIC, 18);

        textArea.setFont(instFont);
        myInstFrame.add(textArea);

    }
}
