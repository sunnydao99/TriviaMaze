package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Help {
    private JFrame myHelpFrame;
    public Help(){
        prepareGUI();
    }
    private void prepareGUI(){
        myHelpFrame = new JFrame("Help Page");
        //myHelpFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //myHelpFrame.setUndecorated(true);
        myHelpFrame.setSize(800,700);
        myHelpFrame.setLayout(new BorderLayout());
        myHelpFrame.setBackground(Color.getHSBColor(240,100,74));
        myHelpFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        myHelpFrame.setVisible(true);

        String help = " True/False questions display two choice, True or False\n" +
                " Using the Extends Time button will give you an extra 30 seconds\n" +
                " to answer before marking the question as wrong. \n" +
                " Multiple Choice questions have a helper method to reduce choices\n" +
                " by 50% by clicking the Help button\n" +
                " Short Choice questions can display hints\n" +
                " and are case insensitive" ;

        JTextArea textArea = new JTextArea(help);
        Font instFont = new Font("Instruction Font", Font.BOLD, 20);

        textArea.setFont(instFont);
        myHelpFrame.add(textArea);

    }
}
