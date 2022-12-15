package Model;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
/**
 * QATF is a child of QA class, built to get questions, correct answers, and choices
 * for True/False question.
 * @version 10.30.22
 * @author An Nguyen, Satinder Singh
 */

public class QATF extends QA implements Serializable {
    Connection myConn;
    private String myQuesTF;
    private String myCorrAnsTF;
    private ArrayList<String> myArrChoiceTF;
    private String myCategory;
    private int myId;

    /**
     * The constructor passes two parameters -  the category and ID
     * @param theCate The category
     * @param theId The ID
     */
    public QATF(String theCate, int theId) {
        myCategory = theCate;
        myId = theId;

    }

    /**
     * This is the default construct
     */
    public QATF() {
        myQuesTF = "";
        myCorrAnsTF = "";
        myConn = null;
        connect();

    }

    /**
     * This method is built to make connect to the database
     * and return connection
     * @exception SQLException if it cannot get connect to the database
     * @return Connection The connection to the database
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
     * This method is overrider from getQuestion() method of QA class, and
     * used to get question from tableTF and return it
     * @exception SQLException if it cannot get the choices and return it
     * @param theCategory The category of question
     * @param theId The ID of the question
     * @return String The question
     */
    @Override
    public String getQuestion(String theCategory, int theId) {
        myCategory = theCategory;
        myId = theId;
        String sql = "SELECT IDQuest, Category, Question "
                + "FROM tableTF WHERE IDQuest = ? AND Category = ?";
        String question = "";

        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the value
            pstmt.setInt(1, theId);
            pstmt.setString(2, theCategory);
            ResultSet rs = pstmt.executeQuery();
            // loop through the result set

            while (rs.next()) {
                question = rs.getString("Question");
            }
            myQuesTF = question;
            return question;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return question;
    }

    /**
     * This method is overrider from getAnswer() method of QA class, and
     * used to get answer from tableTF and return it
     * @exception SQLException if it cannot get the choices and return it
     * @param theCategory The category of answer
     * @param theId The ID of the answer
     * @return String The answer
     */
    public String getAnswer(String theCategory, int theId){
        myCategory = theCategory;
        myId = theId;
        String sql = "SELECT IDQuest,Category, CorrectAnswer "
                + "FROM tableTF WHERE IDQuest = ? AND Category = ?";
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
            myCorrAnsTF = corrAns;
            return corrAns;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return corrAns;
    }

    /**
     * This method is overrider from getChoices() method of QA class, and
     * used to get a list of choices from tableTF and return it
     * @exception SQLException if it cannot get the choices and return it
     * @param theCategory The category of choices
     * @param theId The ID of the choices
     * @return ArrayList<String> A list of choices
     */
    @Override
    public ArrayList<String> getChoices(String theCategory, int theId){
        myCategory = theCategory;
        myId = theId;
        String sql = "SELECT IDQuest,Category, ChoiceA, ChoiceB "
                + "FROM tableTF WHERE IDQuest = ? AND Category = ?";

        ArrayList<String> choices = new ArrayList<String>();
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, theId);
            pstmt.setString(2, theCategory);
            ResultSet rs = pstmt.executeQuery();
            // loop through the result set
            myArrChoiceTF = new ArrayList<String>();
            while (rs.next()) {
                String choiceA = rs.getString("ChoiceA");
                String choiceB = rs.getString("ChoiceB");
                choices.add(choiceA);
                choices.add(choiceB);

            }
            myArrChoiceTF.addAll(choices);

            return choices;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return choices;
    }

    /**
     * This method is built to print the choices for True or False questions
     */
    public void printChoicesTF() {
        System.out.print("[");
        for (int i = 0; i < myArrChoiceTF.size(); i++) {

            if (i == (myArrChoiceTF.size() - 1)) {
                System.out.print(myArrChoiceTF.get(myArrChoiceTF.size() - 1));
            } else {
                System.out.print(myArrChoiceTF.get(i) + ", ");
            }
        }
        System.out.println("]");
    }


}
