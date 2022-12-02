package View;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.*;

import Model.QA;
import Model.QAMC;

import static java.awt.Color.*;

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

    private  JTextField tfTimer;
    private  Font font1;
    Timer timer ;
    int second, minute;
    String ddSecond, ddMinute;
    DecimalFormat dFormat = new DecimalFormat("00");

    public static int index;
    public boolean checkAns;

    private String myCate;
    private int myId;

    private String myCorrAns;
    private ArrayList<String> myArrChoice;
    private ArrayList<String> myArrRedChoice;
    private ArrayList<String> myArrOpt50;
    private QA myBank;

    // Constructor to setup GUI components and event handlers
    public RoomMCView(String theCate, int theId) {
        myCate = theCate;
        myId = theId;
        myBank = new QAMC(theCate, theId);
        index = 0;
        prepareGUI(theCate, theId);
        checkAns = false;

    }

    public RoomMCView() {

    }

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

        font1 = new Font("Arial", Font.PLAIN, 20);
        tfTimer = new JTextField();
        tfTimer.setBounds(360, 350, 80, 50);
        //tfTimer.setText("    01:00");
        CountdownTimer cntTimer = new CountdownTimer();
        tfTimer.setText(cntTimer.getStrTimer());
        //timer.start();

        myMainFrame.add(tfTimer);
        myMainFrame.add(myTaQuestion);
        myMainFrame.add(myRadioBtA);
        myMainFrame.add(myRadioBtB);
        myMainFrame.add(myRadioBtC);
        myMainFrame.add(myRadioBtD);
        myMainFrame.add(myBtnHelper50);
        myMainFrame.add(myBtnSubmit);


        myGroupRadio.add(myRadioBtA);
        myGroupRadio.add(myRadioBtB);
        myGroupRadio.add(myRadioBtC);
        myGroupRadio.add(myRadioBtD);

        myMainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                myMainFrame.dispose();
                //System.exit(0);
            }
        });


        showEventDemo();

    }

    public void roomShow(){
        myMainFrame.setVisible(true);
    }

    public void showEventDemo() {


/*
        tfTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CountdownTimer cntTimer = new CountdownTimer();
                cntTimer.countdownTimer();
                tfTimer.setText(cntTimer.getStrTimer());
                timer.start();

            }
        });
*/

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
                    JOptionPane.showMessageDialog(myBtnSubmit, "It's correct. You're pass!");
                    checkAns = true;
                    index++;
                }
                else {
                    JOptionPane.showMessageDialog(myBtnSubmit, "It's not correct. Please, try other door!");
                    checkAns = false;
                }
                //System.out.println("index: " + index);
                myMainFrame.dispose();
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

    public ArrayList<String> disappearHalfChoice(String theCate, int theId) {
        myArrOpt50 = new ArrayList<String>();
        ArrayList<Integer> temp = new ArrayList<Integer>();
        ArrayList<String> optList = new ArrayList<String>();
        temp = ((QAMC) myBank).getOptionForRedChoice(theCate, theId);


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


}
