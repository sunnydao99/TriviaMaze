package Model;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author: An Nguyen
 * @version: 12/11/2022
 */

/**
 * QATFExtra class get questions, get correct answers, get choices for True/False question.
 * This class uses for switch question button
 * This class will extend QA class
 */
public class QATFExtra extends QA implements Serializable {
    Connection myConn;
    private String myQuesTFE;
    private String myCorrAnsTFE;
    private ArrayList<String> myArrChoiceTFE;
    private String myCategory;
    private int myId;

    /**
     * QATFExtra(String, int): constructor pass two parameters
     * @param theCate: category
     * @param theId: id
     */
    public QATFExtra(String theCate, int theId) {
        myCategory = theCate;
        myId = theId;

    }

    /**
     * QATFExtra(): default constructor
     */
    public QATFExtra() {
        myQuesTFE = "";
        myCorrAnsTFE = "";
        myConn = null;
        connect();
    }

    /**
     * connect(): connect database and return connection
     * @return: Connection
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
     * getQuestion(String, int): overrider getQuestion() from QA class.
     * get question from tableTFExtra and return question
     * @param theCategory: category
     * @param theId: id
     * @return: String
     */
    @Override
    public String  getQuestion(String theCategory, int theId) {
        myCategory = theCategory;
        myId = theId;
        String sql = "SELECT IDQuest, Category, Question "
                + "FROM tableTFExtra WHERE IDQuest = ? AND Category = ?";
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
            myQuesTFE = question;
            return question;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return question;
    }

    /**
     * getAnswer(String, int): overrider getAnswer() from QA class.
     * return answer from tableTFExtra
     * @param theCategory: category
     * @param theId: id
     * @return: String
     */
    @Override
    public String getAnswer(String theCategory, int theId){
        myCategory = theCategory;
        myId = theId;
        String sql = "SELECT IDQuest,Category, CorrectAnswer "
                + "FROM tableTFExtra WHERE IDQuest = ? AND Category = ?";
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
            myCorrAnsTFE = corrAns;
            return corrAns;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return corrAns;
    }

    /**
     * getChoices(String, int): overrider getChoices() from QA class.
     * return options list from tableTFExtra
     * @param theCategory: category
     * @param theId: id
     * @return: ArrayList<String>
     */
    public ArrayList<String> getChoices(String theCategory, int theId){
        myCategory = theCategory;
        myId = theId;
        String sql = "SELECT IDQuest,Category, ChoiceA, ChoiceB "
                + "FROM tableTFExtra WHERE IDQuest = ? AND Category = ?";

        ArrayList<String> choices = new ArrayList<String>();
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, theId);
            pstmt.setString(2, theCategory);
            ResultSet rs = pstmt.executeQuery();
            // loop through the result set
            myArrChoiceTFE = new ArrayList<String>();
            while (rs.next()) {
                String choiceA = rs.getString("ChoiceA");
                String choiceB = rs.getString("ChoiceB");
                choices.add(choiceA);
                choices.add(choiceB);

            }
            myArrChoiceTFE.addAll(choices);

            return choices;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return choices;
    }

    /**
     * printChoicesTF(): print myArrChoiceTFE list
     */
    public void printChoicesTF() {
        System.out.print("[");
        for (int i = 0; i < myArrChoiceTFE.size(); i++) {

            if (i == (myArrChoiceTFE.size() - 1)) {
                System.out.print(myArrChoiceTFE.get(myArrChoiceTFE.size() - 1));
            } else {
                System.out.print(myArrChoiceTFE.get(i) + ", ");
            }
        }
        System.out.println("]");
    }
}
