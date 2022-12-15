package View;
import Model.QA;
import Model.QASA;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;
import static java.awt.Color.BLUE;
import static java.awt.Color.RED;

/**
 * @author: An Nguyen
 * @version: 11/8/2022
 *
 */

/**
 * RoomSAView class use GUI to design and display for short answer question.
 */
public class RoomSAView extends JFrame {
    private JFrame myMainFrame;
    private JTextArea myTaQuestion;
    private JTextField myTfInputAns;
    private JTextArea myTaHint;
    private JButton myBtnHints;
    private JButton myBtnSubmit;

    private  JLabel myLbTimer;
    private  Font myFont1;
    Timer myTimer;
    private int mySecond, myMinute;
    private String myddSecond, myddMinute;
    DecimalFormat mydFormat = new DecimalFormat("00");

    public boolean myCheckAns;
    private String myCate;
    private int myId;
    private String myCorrAns;
    private String myHint;
    private QA myBank;

    /**
     * RoomSAView(String, int): this is constructor passing two parameters
     * to set up GUI components and event handlers
     * @param theCate: category
     * @param theId: id
     */
    public RoomSAView(String theCate, int theId) {
        myCate = theCate;
        myId = theId;
        myBank = new QASA(theCate, theId);
        myHint = "";
        prepareGUI(theCate, theId);
        myCheckAns = false;
        roomShow();
    }

    /**
     * RoomSAView(): constructor
     */
    public RoomSAView(){
        myCate = "";
        myId = 0;
        myHint = "";
        myCheckAns = false;
    }

    /**
     * prepareGUI(String, id): set up components
     * @param theCate: category
     * @param theId: id
     */
    private void prepareGUI(String theCate, int theId){
        myMainFrame = new JFrame("Welcome to challenge^^");
        myMainFrame.setSize(500,400);
        myMainFrame.setLayout(null);

        myTaQuestion = new JTextArea();
        myTaQuestion.setBounds(17,33,400,90);
        myTaQuestion.setText(displayQuestion(theCate, theId));
        myTaQuestion.setLineWrap(true);
        myTaQuestion.setWrapStyleWord(true);

        myTfInputAns = new JTextField();
        myTfInputAns.setBounds(20, 250, 200, 40);

        myTaHint = new JTextArea();
        myTaHint.setBounds(17, 150, 380, 60 );
        myTaHint.setVisible(false);

        myBtnHints = new JButton("Hints");
        myBtnHints.setBackground(BLUE);
        myBtnHints.setBounds(17, 8, 50,20);
        myBtnSubmit = new JButton("Submit");
        myBtnSubmit.setBounds(230, 330, 80, 30);
        myBtnSubmit.setBackground(RED);

        myFont1 = new Font("Arial", Font.PLAIN, 50);
        myLbTimer = new JLabel();
        myLbTimer.setBounds(350, 300, 80, 70);
        myLbTimer.setText("00:60");
        mySecond = 60;
        myMinute = 0;
        countingTimer();
        myTimer.start();

        myMainFrame.add(myLbTimer);
        myMainFrame.add(myTaQuestion);
        myMainFrame.add(myBtnHints);
        myMainFrame.add(myTfInputAns);
        myMainFrame.add(myBtnSubmit);
        myMainFrame.add(myTaHint);

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
     * showEventDemo(): create action listener for Helper Hints button, and submit button
     * For Hints button, it displays hints
     * For submit button, if players answer correct answer will display the message dialog
     * that confirm passing this door. Otherwise, the message dialog display no passing this door.
     */
    public void showEventDemo(){

        myBtnHints.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myHint = "Hints: ";
                myHint += displayHints(myCate, myId);
                myTaHint.setText(myHint);
                myTaHint.setVisible(true);

            }
        });

        myBtnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = "";
                String temp = myTfInputAns.getText();
                myCorrAns = displayAnswer(myCate, myId);
                if(temp.toUpperCase().equals(myCorrAns.toUpperCase())){
                    text = "It's correct. You're pass!";
                    ImageIcon icon = new ImageIcon("Assets/correct.jpeg");
                    JOptionPane.showMessageDialog(myBtnSubmit, text, "Check^^", JOptionPane.INFORMATION_MESSAGE, icon);
                    myCheckAns = true;
                }
                else{
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
     * displayAnswer(String, int): get correct answer and return answer
     * @param theCate: category
     * @param theId: id
     * @return: String
     */
    public String displayAnswer(String theCate, int theId) {
        String ans = myBank.getAnswer(theCate, theId);
        return ans;
    }

    /**
     * displayHints(String, int): return hints
     * @param theCate: category
     * @param theId: id
     * @return: String
     */
    public String displayHints(String theCate, int theId) {
        String hint = ((QASA) myBank).getHintSA(theCate, theId);
        return hint;
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


}
