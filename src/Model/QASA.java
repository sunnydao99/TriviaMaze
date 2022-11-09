package Model;

import java.sql.*;
import java.util.ArrayList;

public class QASA extends QA{
    Connection myConn;
    private String myQuesSA;
    private String myCorrAnsSA;
    private String myHint;

    public void QASA() {
        myQuesSA = "";
        myCorrAnsSA = "";
        myHint = "";
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

    //get question from tableSA
    public String  getQuestionSA(int theId, String theCategory) {
        String sql = "SELECT IDQuest, Category, Question "
                + "FROM tableSA WHERE IDQuest = ? AND Category = ?";
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
            myQuesSA = question;
            return question;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return question;
    }

    public String getCorrAnsSA(int theId, String theCategory){
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
                myCorrAnsSA = corrAns;
            }

            return corrAns;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return corrAns;
    }

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
