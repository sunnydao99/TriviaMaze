package View;
import Model.QA;
import Model.QATF;
import Model.QATFExtra;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

import static java.awt.Color.BLUE;
import static java.awt.Color.RED;

public class RoomTFView extends JFrame {
    private JFrame myMainFrame;
    private JTextArea myTaQuestion;
    private JRadioButton myRadioBtA;
    private JRadioButton myRadioBtB;
    private ButtonGroup myGroupRadio;
    private JButton myBtnSwitch;
    private JButton myBtnSubmit;

    private  JLabel myLbTimer;
    private Font myFont1;
    Timer myTimer;
    private int mySecond, myMinute;
    private String myddSecond, myddMinute;
    DecimalFormat mydFormat = new DecimalFormat("00");

    public boolean myCheckAns;
    private String myCate;
    private int myId;

    private String myCorrAns;
    private ArrayList<String> myArrChoice;
    private QA myBank;
    private boolean mySwitch;

    // Constructor to setup GUI components and event handlers
    public RoomTFView(String theCate, int theId) {
        myCate = theCate;
        myId = theId;
        myBank = new QATF(theCate, theId);
        prepareGUI(theCate, theId);
        myCheckAns = false;
        mySwitch = false;
    }
    public RoomTFView(){

    }

    private void prepareGUI(String theCate, int theId){
        myMainFrame = new JFrame("Welcome to challenge^^");
        myMainFrame.setSize(500,400);
        myMainFrame.setLayout(null);

        myTaQuestion = new JTextArea();
        myTaQuestion.setBounds(17,33,450,90);
        myTaQuestion.setText(displayQuestion(theCate, theId));
        myTaQuestion.setLineWrap(true);
        myTaQuestion.setWrapStyleWord(true);

        myBtnSwitch = new JButton("Switch Question");
        myBtnSwitch.setBackground(BLUE);
        myBtnSwitch.setBounds(17, 8, 120,20);
        myBtnSubmit = new JButton("Submit");
        myBtnSubmit.setBounds(230, 330, 80, 30);
        myBtnSubmit.setBackground(RED);

        myRadioBtA = new JRadioButton();
        myRadioBtB = new JRadioButton();
        myGroupRadio = new ButtonGroup();
        myArrChoice = displayChoices(theCate, theId);
        myRadioBtA.setText(myArrChoice.get(0));
        myRadioBtB.setText(myArrChoice.get(1));
        myRadioBtA.setBounds(12, 120, 100, 80);
        myRadioBtB.setBounds(12, 170, 100, 80);

        myFont1 = new Font("Arial", Font.PLAIN, 50);
        myLbTimer = new JLabel();
        myLbTimer.setBounds(370, 280, 80, 70);
        myLbTimer.setText("00:60");
        mySecond = 60;
        myMinute = 0;
        countingTimer();
        myTimer.start();

        myMainFrame.add(myLbTimer);
        myMainFrame.add(myTaQuestion);
        myMainFrame.add(myRadioBtA);
        myMainFrame.add(myRadioBtB);
        myMainFrame.add(myBtnSwitch);
        myMainFrame.add(myBtnSubmit);

        myGroupRadio.add(myRadioBtA);
        myGroupRadio.add(myRadioBtB);

        myMainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                myTimer.stop();
                myMainFrame.dispose();
                //System.exit(0);
            }
        });

        showEventDemo();
    }

    public void roomShow(){
        myMainFrame.setVisible(true);
    }

    public void showEventDemo(){

        myBtnSwitch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(myBtnSwitch,"Your question is going to switch!");
                Random rand = new Random();
                int max = 12;
                int min = 1;
                int id = rand.nextInt(max + 1 - min) + min;
                myId = id;
                myCate = "TFE";
                //System.out.println("id random: " + id);
                myTaQuestion.setText(displayQuestionExtra(myCate, id));
                myArrChoice = displayChoicesExtra(myCate, id);
                myRadioBtA.setText(myArrChoice.get(0));
                myRadioBtB.setText(myArrChoice.get(1));
                mySwitch = true;

            }
        });

        myBtnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String corrAns = displayAnswer(myCate, myId);
                String userAns = "";
                String text = "";

                if(mySwitch == true){
                    corrAns = displayAnswerExtra(myCate, myId);
                }

                for (int i = 0; i < 4; i++) {
                    if (myRadioBtA.isSelected()) {
                        userAns = "TRUE";
                        break;
                    } else if (myRadioBtB.isSelected()) {
                        userAns = "FALSE";
                        break;
                    }
                }

                if(userAns.equals("")) {
                    JOptionPane.showMessageDialog(myBtnSubmit, "Please, select your answer!");
                }
                else if(userAns.equals(corrAns)){
                    text = "It's correct. You're pass!";
                    ImageIcon icon = new ImageIcon("Assets/correct.jpeg");
                    JOptionPane.showMessageDialog(myBtnSubmit, text, "Check^^", JOptionPane.INFORMATION_MESSAGE, icon);
                    //RoomMCView.index++;
                    myCheckAns = true;
                }
                else {
                    text = "It's not correct. Please, try other door!";
                    ImageIcon icon = new ImageIcon("Assets/wrong.png");
                    JOptionPane.showMessageDialog(myBtnSubmit, text, "Check^^",JOptionPane.INFORMATION_MESSAGE, icon);
                    myCheckAns = false;
                }
                myTimer.stop();
                myMainFrame.dispose();
                //System.exit(0);

            }
        });
    }

    private void countingTimer(){
        myTimer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(myMinute ==0 && mySecond ==0) {
                    myTimer.stop();

                }
                else{
                    mySecond--;
                    myddSecond = mydFormat.format(mySecond);
                    myddMinute = mydFormat.format(myMinute);
                    myLbTimer.setText(myddMinute + ":"+ myddSecond);

                }
            }
        });
    }

    public String displayQuestion(String theCate, int theId) {
        String ques = myBank.getQuestion(theCate, theId);
        //System.out.println(ques);
        return ques;
    }

    public ArrayList<String> displayChoices(String theCate, int theId) {
        myArrChoice = new ArrayList<String>();
        ArrayList<String> temp = new ArrayList<String>();
        temp = myBank.getChoices(theCate, theId);
        myArrChoice.addAll(temp);
        return myArrChoice;
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
        myArrChoice = new ArrayList<String>();
        ArrayList<String> temp = new ArrayList<String>();
        temp = myBank.getChoices(theCate, theId);
        myArrChoice.addAll(temp);
        return myArrChoice;
    }

    public String displayAnswerExtra (String theCate, int theId) {
        myBank = new QATFExtra(theCate, theId);
        String ans = myBank.getAnswer(theCate, theId);
        myCorrAns = ans;
        //System.out.println(ans);
        return ans;
    }


}
