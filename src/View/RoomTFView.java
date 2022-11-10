package View;
import java.awt.event.*;
import javax.swing.*;

import static java.awt.Color.BLUE;
import static java.awt.Color.RED;

public class RoomTFView extends JFrame {
    private JFrame mainFrame;
    private JTextArea taQuestion;
    private JRadioButton radioBtA;
    private JRadioButton radioBtB;
    private ButtonGroup groupRadio;
    private JButton btnExtension;
    private JButton btnSubmit;


    // Constructor to setup GUI components and event handlers
    public RoomTFView() {
        prepareGUI();
    }

    private void prepareGUI(){
        mainFrame = new JFrame("Welcome to challenge^^");
        mainFrame.setSize(500,400);
        mainFrame.setLayout(null);

        taQuestion = new JTextArea("Display question area");
        taQuestion.setBounds(17,33,400,90);
        btnExtension = new JButton("Extension");
        btnExtension.setBackground(BLUE);
        btnExtension.setBounds(17, 8, 80,20);
        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(230, 330, 80, 30);
        btnSubmit.setBackground(RED);

        radioBtA = new JRadioButton();
        radioBtB = new JRadioButton();
        groupRadio = new ButtonGroup();

        radioBtA.setText("A hello");
        radioBtB.setText("B hello");
        radioBtA.setBounds(12, 120, 100, 80);
        radioBtB.setBounds(12, 170, 100, 80);

        mainFrame.add(taQuestion);
        mainFrame.add(radioBtA);
        mainFrame.add(radioBtB);
        mainFrame.add(btnExtension);
        mainFrame.add(btnSubmit);

        groupRadio.add(radioBtA);
        groupRadio.add(radioBtB);


        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        mainFrame.setVisible(true);
    }
    public void showEventDemo(){

        btnExtension.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(btnExtension,"Give 60 seconds!");
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
