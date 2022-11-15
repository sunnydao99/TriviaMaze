package View;
import Model.QA;
import Model.QATF;

import java.awt.event.*;
import java.util.ArrayList;
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

    private String myCate;
    private int myId;
    private String myCorrAns;
    private ArrayList<String> arrChoice;
    private ArrayList<String> arrRedChoice;
    private ArrayList<String> arrOpt50;
    private QA myBank;

    // Constructor to setup GUI components and event handlers
    public RoomTFView(String theCate, int theId) {
        myCate = theCate;
        myId = theId;
        myBank = new QATF(theCate, theId);
        prepareGUI(theCate, theId);
    }
    public RoomTFView(){

    }

    private void prepareGUI(String theCate, int theId){
        mainFrame = new JFrame("Welcome to challenge^^");
        mainFrame.setSize(500,400);
        mainFrame.setLayout(null);

        taQuestion = new JTextArea();
        taQuestion.setBounds(17,33,450,90);
        taQuestion.setText(displayQuestion(theCate, theId));
        btnExtension = new JButton("Extension");
        btnExtension.setBackground(BLUE);
        btnExtension.setBounds(17, 8, 80,20);
        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(230, 330, 80, 30);
        btnSubmit.setBackground(RED);

        radioBtA = new JRadioButton();
        radioBtB = new JRadioButton();
        groupRadio = new ButtonGroup();
        arrChoice = displayChoices(theCate, theId);
        radioBtA.setText(arrChoice.get(0));
        radioBtB.setText(arrChoice.get(1));
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
                String corrAns = displayAnswer(myCate, myId);
                String text = "";
                if(!(radioBtA.isSelected() || radioBtB.isSelected())){
                    JOptionPane.showMessageDialog(btnSubmit, "Please, select your answer!");
                }
                else {
                    if (radioBtA.isSelected()) {
                        if (corrAns.equals("TRUE")) {
                            text = "It's correct. You're pass!";
                        } else {
                            text = "It's not correct. Please, try other door!";
                        }
                    } else if (radioBtB.isSelected()) {
                        if (corrAns.equals("FALSE")) {
                            text = "It's correct. You're pass!";
                        } else {
                            text = "It's not correct. Please, try other door!";
                        }
                    }
                    JOptionPane.showMessageDialog(btnSubmit,text);
                }

            }
        });
    }

    public String displayQuestion(String theCate, int theId) {
        String ques = myBank.getQuestion(theCate, theId);
        System.out.println(ques);
        return ques;
    }

    public ArrayList<String> displayChoices(String theCate, int theId) {
        arrChoice = new ArrayList<String>();
        ArrayList<String> temp = new ArrayList<String>();
        temp = myBank.getChoices(theCate, theId);
        arrChoice.addAll(temp);
        return arrChoice;
    }

    public String displayAnswer(String theCate, int theId) {
        String ans = myBank.getAnswer(theCate, theId);
        myCorrAns = ans;
        System.out.println(ans);
        return ans;
    }


}
