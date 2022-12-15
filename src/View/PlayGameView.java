package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridBagLayout;
import java.io.FileNotFoundException;

/**
 * @author: An Nguyen
 * @version: 11/30/2022
 *
 */

/**
 * PlayGameView class create three buttons to display Beginner, Medium, Expert Level
 */
public class PlayGameView {
    private JFrame myMainFrame;
    private JButton myBtnLevelEasy;
    private JButton myBtnLevelMedium;
    private JButton myBtnLevelHard;
    private Panel myPanelRoom;
    private String myCate;
    private int myId;

    /**
     * PlayGameView(): constructor
     */
    public PlayGameView(){
        prepareGUI();
    }

    /**
     * prepareGUI(): set up components
     */
    void prepareGUI(){
        myMainFrame = new JFrame("Levels");
        myMainFrame.setSize(500,400);
        myMainFrame.setLayout(null);
        JLabel lbPanel = new JLabel("Please, select a level!");
        lbPanel.setFont(new Font("Big Font", Font.CENTER_BASELINE, 20));
        lbPanel.setForeground(Color.BLUE);
        lbPanel.setBounds(130, 20, 250, 80);
        myPanelRoom = new Panel(new GridBagLayout());

        myBtnLevelEasy = new JButton("Beginner");
        myBtnLevelEasy.setBounds(190, 120, 130,40);
        myBtnLevelMedium = new JButton("Intermediate");
        myBtnLevelMedium.setBounds(235, 120, 130,40);
        myBtnLevelHard = new JButton("Expert");
        myBtnLevelHard.setBounds(280, 120, 130,40);


        myPanelRoom.add(myBtnLevelEasy);
        myPanelRoom.add(myBtnLevelMedium);
        myPanelRoom.add(myBtnLevelHard);

        myPanelRoom.setBounds(40,80, 400,100);
        myPanelRoom.setBackground(Color.PINK);

        myMainFrame.add(myPanelRoom);
        myMainFrame.add(lbPanel);

        myMainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                myMainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            }
        });

        myMainFrame.setVisible(true);
    }

    /**
     * showEventDemo(): create action listener for three buttons Beginner, Medium, Expert
     */
    public void showEventDemo(){

        myBtnLevelEasy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(myBtnLevelEasy,"Navigate using keyboard to exit to win!");
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {

                        try {
                            GameView lvEasy = new GameView(1);
                        } catch (FileNotFoundException ex) {
                            System.out.println(ex);
                        }

                    }
                });

            }
        });
        myBtnLevelMedium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(myBtnLevelMedium,"Navigate using keyboard to exit to win!");
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {

                        try {
                            GameView lvMedium = new GameView(2);
                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                });

            }
        });
        myBtnLevelHard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(myBtnLevelHard,"Navigate using keyboard to exit to win!");
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {

                        try {
                            GameView lvHard = new GameView(3);
                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }

                    }
                });

            }
        });

    }

}

