package View;
import Model.QA;
import Model.QATF;
import Model.QATFExtra;

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
    private JButton btnSwitch;
    private JButton btnSubmit;

    public boolean checkAns;
    private String myCate;
    private int myId;

    private String myCorrAns;
    private ArrayList<String> arrChoice;
    private QA myBank;

    // Constructor to setup GUI components and event handlers
    public RoomTFView(String theCate, int theId) {
        myCate = theCate;
        myId = theId;
        myBank = new QATF(theCate, theId);
        prepareGUI(theCate, theId);
        checkAns = false;
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
        btnSwitch = new JButton("Switch Question");
        btnSwitch.setBackground(BLUE);
        btnSwitch.setBounds(17, 8, 80,20);
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
        mainFrame.add(btnSwitch);
        mainFrame.add(btnSubmit);

        groupRadio.add(radioBtA);
        groupRadio.add(radioBtB);


        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                mainFrame.dispose();
                //System.exit(0);
            }
        });

        showEventDemo();
    }

    public void roomShow(){
        mainFrame.setVisible(true);
    }

    public void showEventDemo(){

        btnSwitch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(btnSwitch,"Your question is going to switch!");

            }
        });

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String corrAns = displayAnswer(myCate, myId);
                String userAns = "";

                for (int i = 0; i < 4; i++) {
                    if (radioBtA.isSelected()) {
                        userAns = "TRUE";
                        break;
                    } else if (radioBtB.isSelected()) {
                        userAns = "FALSE";
                        break;
                    }
                }

                if(userAns.equals("")) {
                    JOptionPane.showMessageDialog(btnSubmit, "Please, select your answer!");
                }
                else if(userAns.equals(corrAns)){
                    JOptionPane.showMessageDialog(btnSubmit, "It's correct. You're pass!");
                    RoomMCView.index++;
                    checkAns = true;
                }
                else {
                    JOptionPane.showMessageDialog(btnSubmit, "It's not correct. Please, try other door!");
                    checkAns = false;
                }
                mainFrame.dispose();
                //System.exit(0);

            }
        });
    }

    public String displayQuestion(String theCate, int theId) {
        String ques = myBank.getQuestion(theCate, theId);
        //System.out.println(ques);
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
        //System.out.println(ans);
        return ans;
    }

    public String displayQuestionExtra(String theCate, int theId){
        myBank = new QATFExtra(theCate, theId);
        String quesE = myBank.getQuestion(theCate, theId);
        return quesE;

    }
    public ArrayList<String> displayChoicesExtra (String theCate, int theId) {
        myBank = new QATFExtra(theCate, theId);
        arrChoice = new ArrayList<String>();
        ArrayList<String> temp = new ArrayList<String>();
        temp = myBank.getChoices(theCate, theId);
        arrChoice.addAll(temp);
        return arrChoice;
    }

    public String displayAnswerExtra (String theCate, int theId) {
        myBank = new QATFExtra(theCate, theId);
        String ans = myBank.getAnswer(theCate, theId);
        myCorrAns = ans;
        //System.out.println(ans);
        return ans;
    }


}
