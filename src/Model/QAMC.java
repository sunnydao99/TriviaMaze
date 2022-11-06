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

    //get data from tableMC
    public void getDataMC(int theId, String theCategory) {
        String sql = "SELECT IDQuest,Category,Question, ChoiceA, ChoiceB, ChoiceC, ChoiceD, CorrectAnswer "
                + "FROM tableMC WHERE IDQuest = ? AND Category = ?";

        try {
            Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            // set the value
            pstmt.setInt(1, theId);
            pstmt.setString(2, theCategory);
            //
            ResultSet rs = pstmt.executeQuery();
            myArrChoiceMC = new ArrayList<String>();
            myArrRedChoiceMC = new ArrayList<String>();

            // loop through the result set
            while (rs.next()) {
                String choiceA = rs.getString("ChoiceA");
                String choiceB = rs.getString("ChoiceB");
                String choiceC = rs.getString("ChoiceC");
                String choiceD = rs.getString("ChoiceD");

                myQuesMC = rs.getString("Question");
                myCorrAnsMC = rs.getString("CorrectAnswer");
                myArrChoiceMC.add(choiceA);
                myArrChoiceMC.add(choiceB);
                myArrChoiceMC.add(choiceC);
                myArrChoiceMC.add(choiceD);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void corrAnsAndRedchoice() {
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
    }

    public String getMyQuesMC() {
        return myQuesMC;
    }
    public String getMyCorrAnsMC(){
        return myCorrAnsMC;
    }
    public ArrayList<String> getMyArrChoiceMC(){
        return myArrChoiceMC;
    }
    public ArrayList<String> getMyArrRedChoiceMC(){
        return myArrRedChoiceMC;
    }
    public void printChoices() {
        System.out.print("[");
        for (int i = 0; i < myArrChoiceMC.size(); i++) {

            if (i == (myArrChoiceMC.size() - 1)) {
                System.out.print(myArrChoiceMC.get(myArrChoiceMC.size() - 1));
            } else {
                System.out.print(myArrChoiceMC.get(i) + ",");
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
                System.out.print(myArrRedChoiceMC.get(i) + ",");
            }
        }
        System.out.println("]");
    }


}
