package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridBagLayout;


public class PlayGameView {
    private JFrame mainFrame;
    private JButton btnLevelEasy;
    private JButton btnLevelMedium;
    private JButton btnLevelHard;
    private Panel panelRoom;
    private String myCate;
    private int myId;

    public PlayGameView(){

        prepareGUI();
        showEventDemo();
    }

    private void prepareGUI(){
        mainFrame = new JFrame("Levels");
        mainFrame.setSize(500,400);
        mainFrame.setLayout(null);

        panelRoom = new Panel(new GridBagLayout());
        btnLevelEasy = new JButton("Beginner");
        btnLevelEasy.setBounds(190, 120, 130,40);
        btnLevelMedium = new JButton("Intermediate");
        btnLevelMedium.setBounds(235, 120, 130,40);
        btnLevelHard = new JButton("Expert");
        btnLevelHard.setBounds(280, 120, 130,40);
        panelRoom.add(btnLevelEasy);
        panelRoom.add(btnLevelMedium);
        panelRoom.add(btnLevelHard);

        panelRoom.setBounds(40,80, 400,130);
        panelRoom.setBackground(Color.PINK);

        mainFrame.add(panelRoom);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        mainFrame.setVisible(true);
    }

    public void showEventDemo(){

        btnLevelEasy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(btnLevelEasy,"Welcome to Beginner Challenge");
                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {

                        LvEasyView lvEasy = new LvEasyView();

                    }
                });

            }
        });
        btnLevelMedium.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(btnLevelMedium,"Welcome to Intermediate Challenge");

                SwingUtilities.invokeLater(new Runnable(){
                    @Override
                    public void run() {

                        LvMediumView lvEasy = new LvMediumView();

                    }
                });

            }
        });
        btnLevelHard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(btnLevelHard,"Welcome to Expert Challenge");
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

