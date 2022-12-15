package Model;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
/**
 * QASA class is built to get questions, correct answers, and hints
 * for short answer question.
 * @version: 10.29.22
 * @author: An Nguyen, Satinder Singh
 *
 */

public class QASA extends QA implements Serializable {
    Connection myConn;
    private String myQuesSA;
    private String myCorrAnsSA;
    private String myHint;

    private String myCategory;
    private int myId;

    /**
     * This constructor is used to pass two parameters which are
     * category question and ID
     * @param theCate The category
     * @param theId  The ID
     */
    public QASA(String theCate, int theId) {
        myCategory = theCate;
        myId = theId;
        connect();
    }

    /**
     * This is the default constructor
     */
    public  QASA() {
        myQuesSA = "";
        myCorrAnsSA = "";
        myHint = "";
        myConn = null;
        connect();

    }

    /**
     * This method is used to make a connection to the database
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
     * This method is overrider from getQuestion() method of QA class.
     * to get question from tableSA and return it
     * @param theCategory The category of question
     * @param theId The ID of the question
     * @exception SQLException if it cannot get the question and return it
     * @return String The question
     */
    @Override
    public String getQuestion(String theCategory, int theId){
        myCategory = theCategory;
        myId = theId;
        String sql = "SELECT IDQuest, Category, Question "
                + "FROM tableSA WHERE IDQuest = ? AND Category = ?";
        String question = "";

        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, theId);
            pstmt.setString(2, theCategory);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                question = rs.getString("Question");
            }

            myQuesSA = question;
            return question;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return question;
    }

    /**
     * This method is overrider from getAnswer() method of QA class.
     * to get answer from tableSA and return it
     * @param theCategory The category of answer
     * @param theId The ID of the answer
     * @exception SQLException if it cannot get the answer and return it
     * @return String The question
     */
    @Override
    public String getAnswer(String theCategory, int theId){
        myCategory = theCategory;
        myId = theId;
        String sql = "SELECT IDQuest,Category, CorrectAnswer "
                + "FROM tableSA WHERE IDQuest = ? AND Category = ?";
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

            myCorrAnsSA = corrAns;
            return corrAns;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return corrAns;
    }


    /**
     * This method is built to get the hints from the tableSA and
     * returns it
     * @param theCategory The category of hint
     * @param theId The ID of hint
     * @return String The hint
     */
    public String getHintSA(String theCategory, int theId){
        String sql = "SELECT IDQuest,Category,Hints "
                + "FROM tableSA WHERE IDQuest = ? AND Category = ?";
        String hint = "";
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, theId);
            pstmt.setString(2, theCategory);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                hint = rs.getString("Hints");
                myHint = hint;
            }

            return hint;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return hint;
    }
}
