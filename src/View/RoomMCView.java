package View;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.*;
import Model.QA;
import Model.QAMC;

import static java.awt.Color.*;

/**
 * @author: An Nguyen
 * @version: 11/5/2022
 *
 */

/**
 * RoomMCView class use GUI to design and display for multiple choice question.
 */
public class RoomMCView extends JFrame {
    private JFrame myMainFrame;
    private JTextArea myTaQuestion;
    private JRadioButton myRadioBtA;
    private JRadioButton myRadioBtB;
    private JRadioButton myRadioBtC;
    private JRadioButton myRadioBtD;
    private ButtonGroup myGroupRadio;
    private JButton myBtnHelper50;
    private JButton myBtnSubmit;
    private  JLabel myLbTimer;
    private  Font myFont1;
    Timer myTimer;
    private int mySecond, myMinute;
    private String myddSecond, myddMinute;
    DecimalFormat mydFormat = new DecimalFormat("00");

    private boolean myCheckAns;
    private String myCate;
    private int myId;
    private String myCorrAns;
    private ArrayList<String> myArrChoice;
    private ArrayList<String> myArrOpt50;
    private QA myBank;

    /**
     * RoomMCView(String, int): this is constructor passing two parameters
     * to set up GUI components and event handlers
     * @param theCate: category
     * @param theId: id
     */
    public RoomMCView(String theCate, int theId) {
        myCate = theCate;
        myId = theId;
        myBank = new QAMC(theCate, theId);
        prepareGUI(theCate, theId);
        myCheckAns = false;

    }

    /**
     * RoomMCView(): constructor
     */
    public RoomMCView() {
        myCate = "";
        myId= 0;
        myCorrAns = "";

    }

    /**
     * prepareGUI(String, int): set up components
     * @param theCate: category
     * @param theId: id
     */
    private void prepareGUI(String theCate, int theId) {
        myMainFrame = new JFrame("Welcome to challenge^^");
        myMainFrame.setSize(500, 450);
        myMainFrame.setLayout(null);

        myTaQuestion = new JTextArea("");
        myTaQuestion.setBounds(17, 33, 450, 90);
        myTaQuestion.setText(displayQuestion(theCate, theId));
        myTaQuestion.setLineWrap(true);
        myTaQuestion.setWrapStyleWord(true);

        myBtnHelper50 = new JButton("50/50");
        myBtnHelper50.setBackground(BLUE);
        myBtnHelper50.setBounds(17, 8, 50, 20);

        myBtnSubmit = new JButton("Submit");
        myBtnSubmit.setBackground(RED);
        myBtnSubmit.setBounds(230, 340, 80, 30);

        myRadioBtA = new JRadioButton();
        myRadioBtB = new JRadioButton();
        myRadioBtC = new JRadioButton();
        myRadioBtD = new JRadioButton();
        myGroupRadio = new ButtonGroup();
        myArrChoice = displayChoices(theCate, theId);
        myRadioBtA.setText(myArrChoice.get(0));
        myRadioBtB.setText(myArrChoice.get(1));
        myRadioBtC.setText(myArrChoice.get(2));
        myRadioBtD.setText(myArrChoice.get(3));
        myRadioBtA.setBounds(12, 120, 400, 80);
        myRadioBtB.setBounds(12, 170, 400, 80);
        myRadioBtC.setBounds(12, 220, 400, 80);
        myRadioBtD.setBounds(12, 270, 400, 80);

        myGroupRadio.add(myRadioBtA);
        myGroupRadio.add(myRadioBtB);
        myGroupRadio.add(myRadioBtC);
        myGroupRadio.add(myRadioBtD);

        myFont1 = new Font("Arial", Font.PLAIN, 50);
        myLbTimer = new JLabel();
        myLbTimer.setBounds(360, 350, 80, 70);
        myLbTimer.setText("00:60");
        mySecond = 60;
        myMinute = 0;
        countingTimer();
        myTimer.start();

        JLabel background1 = new JLabel(new ImageIcon("Assets/treasureMazeBG.jpeg"));

        myMainFrame.add(background1);
        myMainFrame.add(myLbTimer);
        myMainFrame.add(myTaQuestion);
        myMainFrame.add(myRadioBtA);
        myMainFrame.add(myRadioBtB);
        myMainFrame.add(myRadioBtC);
        myMainFrame.add(myRadioBtD);
        myMainFrame.add(myBtnHelper50);
        myMainFrame.add(myBtnSubmit);

        myMainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
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
     * showEventDemo(): create action listener for Helper 50/50 button, and submit button
     * For helper 50/50 button, no display 2 choices
     * For submit button, if players answer correct answer will display the message dialog
     * that confirm passing this door. Otherwise, the message dialog display no passing this door.
     */
    public void showEventDemo() {

        myBtnHelper50.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(myBtnHelper50, "Reduce choices");
                myArrOpt50 = disappearHalfChoice(myCate, myId);
                for (int i = 0; i < myArrOpt50.size(); i++) {
                    if (myArrOpt50.get(i).equals("A")) {
                        myRadioBtA.setVisible(false);
                    } else if (myArrOpt50.get(i).equals("B")) {
                        myRadioBtB.setVisible(false);
                    } else if (myArrOpt50.get(i).equals("C")) {
                        myRadioBtC.setVisible(false);
                    } else {
                        myRadioBtD.setVisible(false);
                    }
                }

            }
        });

        myBtnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String corrAns = displayAnswer(myCate, myId);
                String userAns = "";
                String text = "";

                for (int i = 0; i < 4; i++) {
                    if (myRadioBtA.isSelected()) {
                        userAns = "A";
                        break;
                    } else if (myRadioBtB.isSelected()) {
                        userAns = "B";
                        break;
                    } else if (myRadioBtC.isSelected()) {
                        userAns = "C";
                        break;
                    } else if (myRadioBtD.isSelected()) {
                        userAns = "D";
                        break;
                    }
                }
                if(userAns.equals("")) {
                    JOptionPane.showMessageDialog(myBtnSubmit, "Please, select your answer!");
                }
                else if(userAns.equals(corrAns)){
                    myCheckAns = true;
                    text = "It's correct. You're pass!";
                    ImageIcon icon = new ImageIcon("Assets/correct.jpeg");
                    JOptionPane.showMessageDialog(myBtnSubmit, text, "Check^^", JOptionPane.INFORMATION_MESSAGE, icon);
                    //index++;
                }
                else {
                    myCheckAns = false;
                    text = "It's not correct. Please, try other door!";
                    ImageIcon icon = new ImageIcon("Assets/wrong.png");
                    JOptionPane.showMessageDialog(myBtnSubmit, text, "Check^^",JOptionPane.INFORMATION_MESSAGE, icon);

                }

                myTimer.stop();
                myMainFrame.dispose();
                //System.exit(0);
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
        return ques;
    }

    /**
     * displayChoices(String, int): display all options from tableMC and return list choices
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
     * disappearHalfChoice(String, int): return reduce choices
     * @param theCate: category
     * @param theId: id
     * @return: String
     */
    public ArrayList<String> disappearHalfChoice(String theCate, int theId) {
        myArrOpt50 = new ArrayList<String>();
        ArrayList<Integer> temp = new ArrayList<Integer>();
        ArrayList<String> optList = new ArrayList<String>();
        temp = ((QAMC) myBank).getIndexForRedChoice(theCate, theId);

        String st = "";
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i) == 0) {
                st = "A";
                optList.add(st);
            } else if (temp.get(i) == 1) {
                st = "B";
                optList.add(st);
            } else if (temp.get(i) == 2) {
                st = "C";
                optList.add(st);
            } else {
                st = "D";
                optList.add(st);
            }
        }
        return optList;
    }

    /**
     * getMyCheckAns(): return answer
     * @return: boolean
     */
    public boolean getMyCheckAns(){
        return myCheckAns;
    }


}
