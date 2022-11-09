package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class QATF extends QA{
    Connection myConn;
    private String myQuesTF;
    private String myCorrAnsTF;
    ArrayList<String> myArrChoiceTF;

    public void QATF() {
        myQuesTF = "";
        myCorrAnsTF = "";
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
    public String  getQuestionTF(int theId, String theCategory) {
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

    public String getCorrAnsTF(int theId, String theCategory){
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
                myCorrAnsTF = corrAns;
            }

            return corrAns;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return corrAns;
    }

    public ArrayList<String> getArrChoicesTF(int theId, String theCategory){
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
