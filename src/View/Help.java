package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * @author satindersingh
 * @version 12/2/2022
 *
 */

/**
 * This class displays information about the game contents on a new Jframe window
 */
public class Help {
    private JFrame myHelpFrame;
    /**
     * @return void
     * This method displays a JFrame with text about what the game is about
     */
    void prepareGUI(){
        myHelpFrame = new JFrame("Help Page");
        myHelpFrame.setSize(800,700);
        myHelpFrame.setLayout(new BorderLayout());
        myHelpFrame.setBackground(Color.getHSBColor(240,100,74));
        myHelpFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        myHelpFrame.setVisible(true);

        String help = " True/False questions display two choice, True or False\n" +
                " For True/False, there is a Switch Question button to swap question\n" +
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
