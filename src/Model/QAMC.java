package Model;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author: An Nguyen
 * @version: 10/28/2022
 *
 */

/**
 * QAMC class get questions, get correct answers, get choices,
 * and create random Reduce Choices for multiple choice question.
 * This class will extend QA class
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
     * QAMC(String, int): this constructor passes two parameters category question and ID
     * @param theCate: category question
     * @param theId: ID
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
     * QAMC(): default construct
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
     * get question from tableMC and return question
     * @param theCate: category
     * @param theId: id
     * @return: String
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
     * getAnswer(String, int): overrider getAnswer() from QA class.
     * return answer from tableMC
     * @param theCategory: category
     * @param theId: id
     * @return: String
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
     * getChoices(String, int): overrider getChoices() from QA class.
     * returns options list from tableMC
     * @param theCategory: category
     * @param theId: id
     * @return: ArrayList<String>
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
     * getArrRedChoiceMC(String, int): uses random to reduce two choices and store them into a list
     * @param theCategory: category
     * @param theId: id
     * @return: ArrayList<String>
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
     * getIndexForRedChoice(String, int): get indexs from myArrRedChoiceMC list and store them into a list
     * @param theCate: category
     * @param theId: id
     * @return: ArrayList<Integer>
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
     * printChoicesMC(): void
     * To print myArrChoiceMC list
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
     * printRedChoiceMC(): void
     * To print reduce choice list
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
