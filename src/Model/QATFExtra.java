package Model;

import java.sql.*;
import java.util.ArrayList;

public class QATFExtra extends QA{
    Connection myConn;
    private String myQuesTFE;
    private String myCorrAnsTFE;
    ArrayList<String> myArrChoiceTFE;
    String myCategory;
    int myId;

    public QATFExtra(String theCate, int theId) {
        myCategory = theCate;
        myId = theId;

    }

    public QATFExtra() {
        myQuesTFE = "";
        myCorrAnsTFE = "";
        myConn = null;
        connect();
    }



    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:Database_QA.db";

        try {
            myConn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return myConn;
    }

    //get question from tableTF

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
            // set the value
            pstmt.setInt(1, theId);
            pstmt.setString(2, theCategory);
            ResultSet rs = pstmt.executeQuery();
            // loop through the result set

            while (rs.next()) {
                question = rs.getString("Question");
            }
            myQuesTFE = question;
            //System.out.println("from QATFExtra: " + question);
            return question;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return question;
    }

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
            //System.out.println("from QATFEtra: " + corrAns);
            return corrAns;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return corrAns;
    }

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
            System.out.println("from QATFExtra: choices created");

            return choices;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return choices;
    }

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
