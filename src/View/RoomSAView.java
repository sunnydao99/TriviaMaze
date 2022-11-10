package View;
import java.awt.event.*;
import javax.swing.*;

import static java.awt.Color.BLUE;
import static java.awt.Color.RED;

public class RoomSAView extends JFrame {
    private JFrame mainFrame;
    private JTextArea taQuestion;
    private JTextField taAnswer;
    private JButton btnHints;
    private JButton btnSubmit;


    // Constructor to setup GUI components and event handlers
    public RoomSAView() {
        prepareGUI();
    }

    private void prepareGUI(){
        mainFrame = new JFrame("Welcome to challenge^^");
        mainFrame.setSize(500,400);
        mainFrame.setLayout(null);

        taQuestion = new JTextArea("Display question area");
        taQuestion.setBounds(17,33,400,90);
        taAnswer = new JTextField();
        taAnswer.setBounds(20, 150, 200, 40);
        btnHints = new JButton("Hints");
        btnHints.setBackground(BLUE);
        btnHints.setBounds(17, 8, 50,20);
        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(230, 330, 80, 30);
        btnSubmit.setBackground(RED);


        mainFrame.add(taQuestion);
        mainFrame.add(btnHints);
        mainFrame.add(taAnswer);
        mainFrame.add(btnSubmit);


        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        mainFrame.setVisible(true);
    }
    public void showEventDemo(){

        btnHints.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(btnHints,"Display Hints");
            }
        });

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(btnSubmit,"Correct!^^");
            }
        });
    }


}
