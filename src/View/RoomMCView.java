package View;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import static java.awt.Color.BLUE;
import static java.awt.Color.RED;

import Model.QA;
import Model.QAMC;

public class RoomMCView extends JFrame {
    private JFrame mainFrame;
    private JTextArea taQuestion;
    private JRadioButton radioBtA;
    private JRadioButton radioBtB;
    private JRadioButton radioBtC;
    private JRadioButton radioBtD;
    private ButtonGroup groupRadio;
    private JButton btnHelper50;
    private JButton btn;

    public static int index;
    public boolean checkAns;

    private String myCate;
    private int myId;

    private String myCorrAns;
    private ArrayList<String> arrChoice;
    private ArrayList<String> arrRedChoice;
    private ArrayList<String> arrOpt50;
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
        mainFrame = new JFrame("Welcome to challenge^^");
        mainFrame.setSize(500, 450);
        mainFrame.setLayout(null);

        taQuestion = new JTextArea("");
        taQuestion.setBounds(17, 33, 450, 90);
        taQuestion.setText(displayQuestion(theCate, theId));
        btnHelper50 = new JButton("50/50");
        btnHelper50.setBackground(BLUE);
        btnHelper50.setBounds(17, 8, 50, 20);

        btn = new JButton("Submit");
        btn.setBackground(RED);
        btn.setBounds(230, 340, 80, 30);

        radioBtA = new JRadioButton();
        radioBtB = new JRadioButton();
        radioBtC = new JRadioButton();
        radioBtD = new JRadioButton();
        groupRadio = new ButtonGroup();
        arrChoice = displayChoices(theCate, theId);
        radioBtA.setText(arrChoice.get(0));
        radioBtB.setText(arrChoice.get(1));
        radioBtC.setText(arrChoice.get(2));
        radioBtD.setText(arrChoice.get(3));
        radioBtA.setBounds(12, 120, 400, 80);
        radioBtB.setBounds(12, 170, 400, 80);
        radioBtC.setBounds(12, 220, 400, 80);
        radioBtD.setBounds(12, 270, 400, 80);

        mainFrame.add(taQuestion);
        mainFrame.add(radioBtA);
        mainFrame.add(radioBtB);
        mainFrame.add(radioBtC);
        mainFrame.add(radioBtD);
        mainFrame.add(btnHelper50);
        mainFrame.add(btn);

        groupRadio.add(radioBtA);
        groupRadio.add(radioBtB);
        groupRadio.add(radioBtC);
        groupRadio.add(radioBtD);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                mainFrame.dispose();
                //System.exit(0);
            }
        });


        showEventDemo();

    }

    public void roomShow(){
        mainFrame.setVisible(true);
    }

    public void showEventDemo() {

        btnHelper50.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(btnHelper50, "Reduce choices");
                arrOpt50 = disappearHalfChoice(myCate, myId);
                for (int i = 0; i < arrOpt50.size(); i++) {
                    if (arrOpt50.get(i).equals("A")) {
                        radioBtA.setVisible(false);
                    } else if (arrOpt50.get(i).equals("B")) {
                        radioBtB.setVisible(false);
                    } else if (arrOpt50.get(i).equals("C")) {
                        radioBtC.setVisible(false);
                    } else {
                        radioBtD.setVisible(false);
                    }
                }

            }
        });


        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String corrAns = displayAnswer(myCate, myId);
                String userAns = "";

                for (int i = 0; i < 4; i++) {
                    if (radioBtA.isSelected()) {
                        userAns = "A";
                        break;
                    } else if (radioBtB.isSelected()) {
                        userAns = "B";
                        break;
                    } else if (radioBtC.isSelected()) {
                        userAns = "C";
                        break;
                    } else if (radioBtD.isSelected()) {
                        userAns = "D";
                        break;
                    }
                }
                if(userAns.equals("")) {
                    JOptionPane.showMessageDialog(btn, "Please, select your answer!");
                }
                else if(userAns.equals(corrAns)){
                    JOptionPane.showMessageDialog(btn, "It's correct. You're pass!");
                    checkAns = true;
                    index++;
                }
                else {
                    JOptionPane.showMessageDialog(btn, "It's not correct. Please, try other door!");
                    checkAns = false;
                }
                //System.out.println("index: " + index);
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

    public ArrayList<String> disappearHalfChoice(String theCate, int theId) {
        arrOpt50 = new ArrayList<String>();
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
