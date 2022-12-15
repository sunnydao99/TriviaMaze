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

/**
 * @author: An Nguyen, Sunny
 * @version: 11/10/2022, updated 12/13/2022
 *
 */

/**
 * RoomTFView class use GUI to design and display for True/False question.
 */
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

    /**
     * RoomTFView(String, int): this is constructor passing two parameters
     * to set up GUI components and event handlers
     * @param theCate: category
     * @param theId: id
     */
    public RoomTFView(String theCate, int theId) {
        myCate = theCate;
        myId = theId;
        myBank = new QATF(theCate, theId);
        prepareGUI(theCate, theId);
        myCheckAns = false;
        mySwitch = false;
    }

    /**
     * RoomTFView(): default constructor
     */
    public RoomTFView(){
        myCate = "";
        myId = 0;
        myCheckAns = false;
        mySwitch = false;
    }

    /**
     * prepareGUI(String, int): set up components
     * @param theCate: category
     * @param theId: id
     */
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
            }
        });

        showEventDemo();
    }

    /**
     * roomShow(): set mainframe is visible
     */
    public void roomShow(){
        myMainFrame.setVisible(true);
    }

    /**
     * showEventDemo(): create action listener for Helper Switch Question button, and submit button
     * For Switch button, just replace current question, correct answer, and choices
     * into other True/False question in tableTFExtra.
     * ID will be created random
     * For submit button, if players answer correct answer will display the message dialog
     * that confirm passing this door. Otherwise, the message dialog display no passing this door.
     */
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

            }
        });
    }

    /**
     * countingTimer(): set timer displays 60 second, after timer reach 0 the window will be closed
     */
    private void countingTimer(){
        myTimer = new Timer(1000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if(myMinute ==0 && mySecond ==0) {
                    myTimer.stop();
                    myMainFrame.dispose();
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

    /**
     * displayQuestion(String, int): get question from tableMC and return question.
     * @param theCate: category
     * @param theId: id
     * @return: String
     */
    public String displayQuestion(String theCate, int theId) {
        String ques = myBank.getQuestion(theCate, theId);
        //System.out.println(ques);
        return ques;
    }

    /**
     * displayChoices(String, int): display all options from tableTF and return list choices
     * @param theCate: category
     * @param theId: id
     * @return: ArrayList<String>
     */
    public ArrayList<String> displayChoices(String theCate, int theId) {
        myArrChoice = new ArrayList<String>();
        ArrayList<String> temp = new ArrayList<String>();
        temp = myBank.getChoices(theCate, theId);
        myArrChoice.addAll(temp);
        return myArrChoice;
    }

    /**
     * displayAnswer(String, int): get correct answer and return answer
     * @param theCate: category
     * @param theId: id
     * @return: String
     */
    public String displayAnswer(String theCate, int theId) {
        String ans = myBank.getAnswer(theCate, theId);
        myCorrAns = ans;
        return ans;
    }

    /**
     * displayQuestionExtra(String, int): this method is for Switch button.
     * It returns question from tableTFExtra
     * @param theCate: category
     * @param theId: id
     * @return: String
     */
    public String displayQuestionExtra(String theCate, int theId){
        myBank = new QATFExtra(theCate, theId);
        String quesE = myBank.getQuestion(theCate, theId);
        return quesE;

    }

    /**
     * displayChoicesExtra(String, int): this method is for Switch button.
     * It returns list choices from tableTFExtra
     * @param theCate: id
     * @param theId: id
     * @return: ArrayList<String>
     */
    public ArrayList<String> displayChoicesExtra (String theCate, int theId) {
        myBank = new QATFExtra(theCate, theId);
        myArrChoice = new ArrayList<String>();
        ArrayList<String> temp = new ArrayList<String>();
        temp = myBank.getChoices(theCate, theId);
        myArrChoice.addAll(temp);
        return myArrChoice;
    }

    /**
     * displayAnswerExtra(String, int): this method is for Switch button.
     * It returns answer from tableTFExtra
     * @param theCate: category
     * @param theId: id
     * @return: String
     */
    public String displayAnswerExtra (String theCate, int theId) {
        myBank = new QATFExtra(theCate, theId);
        String ans = myBank.getAnswer(theCate, theId);
        myCorrAns = ans;
        return ans;
    }


}
