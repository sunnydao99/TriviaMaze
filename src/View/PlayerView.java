package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PlayerView {

    private JFrame mainFrame;
    private JTextField tfInputName;
    private JLabel lbNumOfQues;
    private JLabel lbScore;
    private Panel panelRoom;


    private int myNumOfQues;
    private int myScore;

    public PlayerView(){
        prepareGUI();
    }

    private void prepareGUI(){
        mainFrame = new JFrame("Player");
        mainFrame.setBounds(10, 10, 600, 550);
        //mainFrame.setSize(500,400);
        mainFrame.setLayout(null);

        panelRoom = new Panel(new GridBagLayout());
        tfInputName = new JTextField("input name ");
        tfInputName.setBounds(150, 100, 200,40);
        /*lbNumOfQues = new JLabel();
        lbNumOfQues.setBounds(230, 160, 80,40);
        lbNumOfQues.setText("Amount Ques");
        lbScore = new JLabel();
        lbScore.setBounds(270, 190, 80,40);
        lbScore.setText("Score");*/

        panelRoom.add(tfInputName);
       /* panelRoom.add(lbNumOfQues);
        panelRoom.add(lbScore);*/

        panelRoom.setBounds(20,50, 430,130);
        panelRoom.setBackground(Color.PINK);

        mainFrame.add(panelRoom);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        mainFrame.setVisible(true);
    }

}
