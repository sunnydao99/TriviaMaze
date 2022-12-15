package Model;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
/**
 * @author: An Nguyen
 * @version: 10/30/2022
 *
 */

/**
 * QATF class gets questions, get correct answers, get choices for True/False question.
 * This class will extend QA class
 */

public class QATF extends QA implements Serializable {
    Connection myConn;
    private String myQuesTF;
    private String myCorrAnsTF;
    private ArrayList<String> myArrChoiceTF;
    private String myCategory;
    private int myId;

    /**
     * QATF(String, int): this constructor passes two parameters category question and ID
     * @param theCate: category question
     * @param theId: ID
     */
    public QATF(String theCate, int theId) {
        myCategory = theCate;
        myId = theId;

    }

    /**
     * QATF(): default construct
     */
    public QATF() {
        myQuesTF = "";
        myCorrAnsTF = "";
        myConn = null;
        connect();

    }

    /**
     * connect(): connect database and return connection
     * @return: Connection
     */
    private Connection connect() {
        String url = "jdbc:sqlite:Database_QA.db";

        try {
            myConn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return myConn;
    }

    /**
     * getQuestion(String, int): overrider getQuestion() from QA class.
     * get question from tableTF and return question
     * @param theCategory: category
     * @param theId: id
     * @return: String
     */
    @Override
    public String  getQuestion(String theCategory, int theId) {
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
     * getAnswer(String, int): overrider getAnswer() from QA class.
     * return answer from tableTF
     * @param theCategory: category
     * @param theId: id
     * @return: String
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
     * getChoices(String, int): overrider getChoices() from QA class.
     * return options list from tableTF
     * @param theCategory: category
     * @param theId: id
     * @return: ArrayList<String>
     */
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
     * printChoicesTF(): print myArrChoiceTF list
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
