package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class QAMC extends QA {
    Connection myConn;
    private String myQuesMC;
    private String myCorrAnsMC;
    ArrayList<String> myArrChoiceMC;
    ArrayList<String> myArrRedChoiceMC;

    public void QAMC() {
        myQuesMC = "";
        myCorrAnsMC = "";
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

    //get question from tableMC
    public String  getQuestionMC(int theId, String theCategory) {
        String sql = "SELECT IDQuest,Category,Question "
                + "FROM tableMC WHERE IDQuest = ? AND Category = ?";
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
            myQuesMC = question;
            return question;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return question;
    }

    public String getCorrAnsMC(int theId, String theCategory){
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
                myCorrAnsMC = corrAns;
            }

            return corrAns;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return corrAns;
    }

    public ArrayList<String> getArrChoicesMC(int theId, String theCategory){
        String sql = "SELECT IDQuest,Category, ChoiceA, ChoiceB, ChoiceC, ChoiceD "
                + "FROM tableMC WHERE IDQuest = ? AND Category = ?";

        ArrayList<String> choices = new ArrayList<String>();
        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, theId);
            pstmt.setString(2, theCategory);
            ResultSet rs = pstmt.executeQuery();
            // loop through the result set
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

    public ArrayList<String> getArrRedChoiceMC(int theId, String theCategory) {
        myCorrAnsMC = getCorrAnsMC(theId, theCategory);
        myArrChoiceMC = getArrChoicesMC(theId, theCategory);
        myArrRedChoiceMC = new ArrayList<String>();
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
        }while (temp1 == temp2 || temp1 == corrAns || temp2 == corrAns);
        myArrRedChoiceMC.add(myArrChoiceMC.get(temp1));
        myArrRedChoiceMC.add(myArrChoiceMC.get(temp2));
        return myArrRedChoiceMC;
    }

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
