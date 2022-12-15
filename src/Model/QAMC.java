package Model;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * QAMC class is used to get questions, correct answers, choices,
 * and create Reduce Choices for helping players to get right answer.
 * This class will extend to the QA class
 * @author: An Nguyen, Satinder Singh
 * @version: 10.28.22
 *
 */

public class QAMC extends QA implements Serializable {
    Connection myConn;
    private String myQuesMC;
    private String myCorrAnsMC;
    private ArrayList<String> myArrChoiceMC;
    private ArrayList<String> myArrRedChoiceMC;
    private String myCategory;
    private int myId;

    /**
     * This constructor is used to pass two parameters which are
     * category question and ID
     * @param theCate The category of question
     * @param theId The ID of category of question
     */
    public QAMC(String theCate, int theId) {
        myQuesMC = "";
        myCorrAnsMC = "";
        myConn = null;
        connect();
        myCategory = theCate;
        myId = theId;
    }

    /**
     * This is the default construct
     */
    public QAMC() {
        myQuesMC = "";
        myCorrAnsMC = "";
        myConn = null;
        connect();
        myId = 0;
        myCategory = "";
    }

    /**
     * This method is created to make a connection to database
     * and return it
     * @return Connection the connection to the database
     */
    public Connection connect() {
        String url = "jdbc:sqlite:Database_QA.db";

        try {
            myConn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return myConn;
    }

    /**
     * This method is being overrider from getQuestion() of QA class
     *  and used to get and return the question from tableMC
     * @param theCate The category of question
     * @param theId The ID of question
     * @exception SQLException if it cannot get the question to return
     * @return String The question
     */
    @Override
    public String getQuestion(String theCate, int theId) {
        myCategory = theCate;
        myId = theId;
        String sql = "SELECT IDQuest,Category,Question "
                + "FROM tableMC WHERE IDQuest = ? AND Category = ?";
        String question = "";
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the value
            pstmt.setInt(1, theId);
            pstmt.setString(2, theCate);
            ResultSet rs = pstmt.executeQuery();
            // loop through the result set
            while (rs.next()) {
                question = rs.getString("Question");

            }
            myQuesMC = question;
            return question;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return question;
    }

    /**
     * This method is overrider from getAnswer() method of QA class.
     * to return answer from tableMC
     * @param theCategory The category of question
     * @param theId The ID of question
     * @exception SQLException if it cannot get the answer and return
     * @return String The answer
     */
    @Override
    public String getAnswer(String theCategory, int theId){
        myCategory = theCategory;
        myId = theId;
        String sql = "SELECT IDQuest,Category, CorrectAnswer "
                + "FROM tableMC WHERE IDQuest = ? AND Category = ?";
        String corrAns = "";
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, theId);
            pstmt.setString(2, theCategory);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                corrAns = rs.getString("CorrectAnswer");

            }
            myCorrAnsMC = corrAns;
            return corrAns;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return corrAns;
    }

    /**
     * This method is overrider from getChoices() method of QA class
     * to return a list of choices from tableMC
     * @param theCategory The category of question
     * @param theId The ID of question
     * @exception SQLException if it cannot get the choices and return it
     * @return ArrayList A String arraylist of choices
     */
    @Override
    public ArrayList<String> getChoices(String theCategory, int theId){
        myCategory = theCategory;
        myId = theId;
        String sql = "SELECT IDQuest,Category, ChoiceA, ChoiceB, ChoiceC, ChoiceD "
                + "FROM tableMC WHERE IDQuest = ? AND Category = ?";

        ArrayList<String> choices = new ArrayList<String>();
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, theId);
            pstmt.setString(2, theCategory);
            ResultSet rs = pstmt.executeQuery();

            myArrChoiceMC = new ArrayList<String>();
            while (rs.next()) {
                String choiceA = rs.getString("ChoiceA");
                String choiceB = rs.getString("ChoiceB");
                String choiceC = rs.getString("ChoiceC");
                String choiceD = rs.getString("ChoiceD");
                choices.add(choiceA);
                choices.add(choiceB);
                choices.add(choiceC);
                choices.add(choiceD);

            }
            myArrChoiceMC.addAll(choices);
            return choices;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return choices;
    }

    /**
     * This method is built to reduce two choices and store them into a list
     * @param theCategory The category of choices
     * @param theId The ID of choices
     * @return ArrayList A String arraylist of choices
     */
    public ArrayList<String> getArrRedChoiceMC(String theCategory, int theId) {
        myCategory = theCategory;
        myId = theId;
        myArrRedChoiceMC = new ArrayList<String>();
        myCorrAnsMC = getAnswer(theCategory, theId);
        int corrAns;
        if (myCorrAnsMC.equals("A")) {
            corrAns = 0;
        } else if (myCorrAnsMC.equals("B")) {
            corrAns = 1;
        } else if (myCorrAnsMC.equals("C")) {
            corrAns = 2;
        } else {
            corrAns = 3;
        }
        //get correct Ans
        for (int i = 0; i < myArrChoiceMC.size(); i++) {
            if (corrAns == i) {
                myCorrAnsMC = myArrChoiceMC.get(i);
                break;
            }
        }
        //Random reduce choices
        int temp1, temp2;
        Random rand = new Random();
        do{
            temp1 = rand.nextInt(4);
            temp2 = rand.nextInt(4);
        }while (temp1 == temp2 || (temp1 == corrAns || temp2 == corrAns));
        myArrRedChoiceMC.add(myArrChoiceMC.get(temp1));
        myArrRedChoiceMC.add(myArrChoiceMC.get(temp2));
        return myArrRedChoiceMC;
    }

    /**
     * This method is created to get indexes from myArrRedChoiceMC list
     * and store them into a list
     * @param theCate category of choices
     * @param theId The ID of choices
     * @return ArrayList<Integer> A list of integer
     */
    public ArrayList<Integer> getIndexForRedChoice(String theCate, int theId){
        int temp1 = 0;
        int temp2 = 0;
        ArrayList<Integer> arrOpt = new ArrayList<Integer>();
        myArrChoiceMC = getChoices(theCate, theId);
        myArrRedChoiceMC = getArrRedChoiceMC(theCate, theId);
        for(int i = 0; i < myArrChoiceMC.size(); i++){
            if(myArrChoiceMC.get(i).equals(myArrRedChoiceMC.get(0))){
                temp1 = i;
            }
            else if (myArrChoiceMC.get(i).equals(myArrRedChoiceMC.get(1))){
                temp2 = i;
            }
        }
        arrOpt.add(temp1);
        arrOpt.add(temp2);
        return arrOpt;

    }

    /**
     * This method is used to print the choices for multiple choices question
     */
    public void printChoicesMC() {
        System.out.print("[");
        for (int i = 0; i < myArrChoiceMC.size(); i++) {

            if (i == (myArrChoiceMC.size() - 1)) {
                System.out.print(myArrChoiceMC.get(myArrChoiceMC.size() - 1));
            } else {
                System.out.print(myArrChoiceMC.get(i) + ", ");
            }
        }
        System.out.println("]");
    }

    /**
     * This method is used to print the choices after reducing options
     */
    public void printRedChoiceMC() {
        System.out.print("[");
        for (int i = 0; i < myArrRedChoiceMC.size(); i++) {

            if (i == (myArrRedChoiceMC.size() - 1)) {
                System.out.print(myArrRedChoiceMC.get(myArrRedChoiceMC.size() - 1));
            } else {
                System.out.print(myArrRedChoiceMC.get(i) + ", ");
            }
        }
        System.out.println("]");
    }


}
