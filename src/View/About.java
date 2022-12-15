package View;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.*;

/**
 * @author: Satinder singh
 * @version 12/2/2022
 *
 */

/**
 * This class displays information about the developers on a new Jframe window
 */
public class About {
    private JFrame myAboutFrame;
    /**
     * @return void
     * This method displays a JFrame with text about the developers
     */
    void prepareGUI(){
        myAboutFrame = new JFrame("About Page");
        myAboutFrame.setSize(800,700);
        myAboutFrame.setLayout(new BorderLayout());
        myAboutFrame.setBackground(Color.getHSBColor(240,100,76));
        myAboutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        myAboutFrame.setVisible(true);

        String help =  " This program was developed by \n" +
                " An Nguyen, Satinder Singh, Xuan Dao(Sunny)\n" +
                " for TCSS 360 Software Development And Quality Assurance Techniques\n" +
                " Professor Tom Capaul, University of Washington Tacoma";
        JTextArea textArea = new JTextArea(help);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        Font instFont = new Font("Instruction Font", Font.BOLD, 20);

        textArea.setFont(instFont);
        myAboutFrame.add(textArea);
    }
}
