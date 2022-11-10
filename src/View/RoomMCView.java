package View;
import java.awt.event.*;
import javax.swing.*;

import static java.awt.Color.BLUE;
import static java.awt.Color.RED;

public class RoomMCView extends JFrame {
    private JFrame mainFrame;
    private JTextArea taQuestion;
    private JRadioButton radioBtA;
    private JRadioButton radioBtB;
    private JRadioButton radioBtC;
    private JRadioButton radioBtD;
    private ButtonGroup groupRadio;
    private JButton btnHelper50;
    private JButton btnSubmit;


    // Constructor to setup GUI components and event handlers
    public RoomMCView() {
        prepareGUI();
    }

    private void prepareGUI(){
        mainFrame = new JFrame("Welcome to challenge^^");
        mainFrame.setSize(500,400);
        mainFrame.setLayout(null);

        taQuestion = new JTextArea("Display question area");
        taQuestion.setBounds(17,33,400,90);
        btnHelper50 = new JButton("50/50");
        btnHelper50.setBackground(BLUE);
        btnHelper50.setBounds(17, 8, 50,20);
        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(230, 330, 80, 30);
        btnSubmit.setBackground(RED);

        radioBtA = new JRadioButton();
        radioBtB = new JRadioButton();
        radioBtC = new JRadioButton();
        radioBtD = new JRadioButton();
        groupRadio = new ButtonGroup();

        radioBtA.setText("A hello");
        radioBtB.setText("B hello");
        radioBtC.setText("C hello");
        radioBtD.setText("D hello");
        radioBtA.setBounds(12, 120, 100, 80);
        radioBtB.setBounds(12, 170, 100, 80);
        radioBtC.setBounds(12, 220, 100, 80);
        radioBtD.setBounds(12, 270, 100, 80);

        mainFrame.add(taQuestion);
        mainFrame.add(radioBtA);
        mainFrame.add(radioBtB);
        mainFrame.add(radioBtC);
        mainFrame.add(radioBtD);
        mainFrame.add(btnHelper50);
        mainFrame.add(btnSubmit);


        groupRadio.add(radioBtA);
        groupRadio.add(radioBtB);
        groupRadio.add(radioBtC);
        groupRadio.add(radioBtD);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        mainFrame.setVisible(true);
    }
    public void showEventDemo(){

        btnHelper50.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(btnHelper50,"Reduce choices");
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
