package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridBagLayout;


public class PlayGameView {
    private JFrame myMainFrame;
    private JButton myBtnLevelEasy;
    private JButton myBtnLevelMedium;
    private JButton myBtnLevelHard;
    private Panel myPanelRoom;
    private String myCate;
    private int myId;

    public PlayGameView(){

        prepareGUI();
        showEventDemo();
    }

    private void prepareGUI(){
        myMainFrame = new JFrame("Levels");
        myMainFrame.setSize(500,400);
        myMainFrame.setLayout(null);

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

        myPanelRoom.setBounds(40,80, 400,130);
        myPanelRoom.setBackground(Color.PINK);

        myMainFrame.add(myPanelRoom);

        myMainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        myMainFrame.setVisible(true);
    }

    public void showEventDemo(){

        myBtnLevelEasy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(myBtnLevelEasy,"Welcome to Beginner Challenge");
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {

                        LvEasyView lvEasy = new LvEasyView();

                    }
                });

            }
        });
        myBtnLevelMedium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(myBtnLevelMedium,"Welcome to Intermediate Challenge");

                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {

                        LvMediumView lvEasy = new LvMediumView();

                    }
                });

            }
        });
        myBtnLevelHard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(myBtnLevelHard,"Welcome to Expert Challenge");
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {

                        LvHardView lvEasy = new LvHardView();

                    }
                });

            }
        });

    }

}

