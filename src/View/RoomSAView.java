package View;
import Model.QA;
import Model.QAMC;
import Model.QASA;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import static java.awt.Color.BLUE;
import static java.awt.Color.RED;

public class RoomSAView extends JFrame {
    private JFrame mainFrame;
    private JTextArea taQuestion;
    private JTextField tfInputAns;
    private JTextArea taHint;
    private JButton btnHints;
    private JButton btnSubmit;


    private String myCate;
    private int myId;
    private boolean flag;
    private String myCorrAns;
    private String myHint;
    private ArrayList<String> arrRedChoice;
    private ArrayList<String> arrOpt50;
    private QA myBank;

    // Constructor to setup GUI components and event handlers
    public RoomSAView(String theCate, int theId) {
        myCate = theCate;
        myId = theId;
        myBank = new QASA(theCate, theId);
        myHint = "";
        prepareGUI(theCate, theId);
    }
    public RoomSAView(){

    }

    private void prepareGUI(String theCate, int theId){
        mainFrame = new JFrame("Welcome to challenge^^");
        mainFrame.setSize(500,400);
        mainFrame.setLayout(null);

        taQuestion = new JTextArea();
        taQuestion.setBounds(17,33,400,90);
        taQuestion.setText(displayQuestion(theCate, theId));

        tfInputAns = new JTextField();
        tfInputAns.setBounds(20, 250, 200, 40);

        taHint = new JTextArea();
        taHint.setBounds(17, 150, 380, 60 );
        taHint.setVisible(false);

        btnHints = new JButton("Hints");
        btnHints.setBackground(BLUE);
        btnHints.setBounds(17, 8, 50,20);
        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(230, 330, 80, 30);
        btnSubmit.setBackground(RED);


        mainFrame.add(taQuestion);
        mainFrame.add(btnHints);
        mainFrame.add(tfInputAns);
        mainFrame.add(btnSubmit);
        mainFrame.add(taHint);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        mainFrame.setVisible(true);
        showEventDemo();
    }
    public void showEventDemo(){

        btnHints.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myHint = "Hints: ";
                myHint += displayHints(myCate, myId);
                taHint.setText(myHint);
                taHint.setVisible(true);
                //JOptionPane.showMessageDialog(btnHints,"Display Hints");
            }
        });

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = "";
                String temp = tfInputAns.getText();
                myCorrAns = displayAnswer(myCate, myId);
                if(temp.toUpperCase().equals(myCorrAns.toUpperCase())){
                    text = "It's correct. You're pass!";
                    RoomMCView.index++;
                }

                else{
                    text = "It's not correct. Please, try other door!";
                }
                JOptionPane.showMessageDialog(btnSubmit,text);
                //System.exit(0);
            }
        });
    }

    public String displayQuestion(String theCate, int theId) {
        String ques = myBank.getQuestion(theCate, theId);
        System.out.println(ques);
        return ques;
    }

    public String displayAnswer(String theCate, int theId) {
        String ans = myBank.getAnswer(theCate, theId);
        System.out.println(ans);
        return ans;
    }

    public String displayHints(String theCate, int theId) {
        String hint = ((QASA) myBank).getHintSA(theCate, theId);
        return hint;
    }


}
