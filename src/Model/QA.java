package Model;

import java.io.*;
import java.lang.reflect.Array;
import java.sql.*;

import java.util.*;

public class QA extends Inventory{

    File myFileCSV_QMC;
    File myFileCSV_QAS;
    File myFileCSV_QTF;
    Scanner myScanMC;
    Scanner myScanQS;
    Scanner myScanTF;
    String myCategory;
    ArrayList<Integer> myIdList;
    int myId;

    Connection myConn;
    private String myQuesMC;
    private String myCorrAnsMC;
    ArrayList<String> myArrChoiceMC;
    ArrayList<String> myArrRedChoiceMC;

    private String myQuesTF;
    private String myCorrAnsTF;
    ArrayList<String> myArrChoiceTF;

    private String myQuesSA;
    private String myCorrAnsSA;
    private String myHint;

    public QA(){
        myFileCSV_QMC = new File("Database/QAMultiple.csv");
        myFileCSV_QAS = new File("Database/QAShort.csv");
        myFileCSV_QTF = new File("Database/QATrueFalse.csv");
        myCategory = "";

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

    public  void insertTableMC(){
        String sql = "INSERT INTO tableMC(IDQuest,Category, Question, ChoiceA, ChoiceB, ChoiceC, ChoiceD, CorrectAnswer)" +
                " VALUES(?, ?,?, ?, ?, ?, ? , ?)";

        try{

            myScanMC = new Scanner(myFileCSV_QMC);
            myScanMC.useDelimiter(",\n");   //sets the delimiter pattern

            String line =  "";
            PreparedStatement statement = conn.prepareStatement(sql);
            String iDQuest = "";
            String category= "";
            String question = "";
            String choiceA = "";
            String choiceB = "";
            String choiceC = "";
            String choiceD = "";
            String correctAnswer = "";

            myScanMC.nextLine();
            while (myScanMC.hasNext())  //returns a boolean value
            {
                line = myScanMC.nextLine();
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                iDQuest = data[0];
                category = data[1];
                question = data[2];
                choiceA = data[3];
                choiceB = data[4];
                choiceC = data[5];
                choiceD = data[6];
                correctAnswer = data[7];

                statement.setString(1,iDQuest);
                statement.setString(2,category);
                statement.setString(3,question);
                statement.setString(4,choiceA);
                statement.setString(5,choiceB);
                statement.setString(6,choiceC);
                statement.setString(7,choiceD);
                statement.setString(8,correctAnswer);
                statement.executeUpdate();
            }
            System.out.println("Inserted tableMC");
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertTableTF() {
        String sql = "INSERT INTO tableTF(IDQuest,Category,Question,ChoiceA,ChoiceB,CorrectAnswer) " +
                "VALUES(?, ?,?, ?, ?, ?)";

        try{
            myScanTF = new Scanner(myFileCSV_QTF);
            myScanTF.useDelimiter(",\n");   //sets the delimiter pattern

            String line =  "";
            PreparedStatement statement = conn.prepareStatement(sql);

            String iDQuest = "";
            String category= "";
            String question = "";
            String choiceA = "";
            String choiceB = "";
            String correctAnswer = "";

            myScanTF.nextLine();
            while (myScanTF.hasNext())  //returns a boolean value
            {
                line = myScanTF.nextLine();
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                iDQuest = data[0];
                category = data[1];
                question = data[2];
                choiceA = data[3];
                choiceB = data[4];
                correctAnswer = data[5];
                statement.setString(1,iDQuest);
                statement.setString(2,category);
                statement.setString(3,question);
                statement.setString(4,choiceA);
                statement.setString(5,choiceB);
                statement.setString(6,correctAnswer);
                statement.executeUpdate();
            }
            System.out.println("Inserted tableTF");
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertTableSA() {
        String sql = "INSERT INTO tableSA(IDQuest,Category, Question, CorrectAnswer, Hints ) VALUES(?, ?,?, ?, ?)";

        try{

            myScanQS = new Scanner(myFileCSV_QAS);
            myScanQS.useDelimiter(",\n");   //sets the delimiter pattern

            String line =  "";
            PreparedStatement statement = conn.prepareStatement(sql);

            String iDQuest = "";
            String category= "";
            String question = "";
            String correctAnswer = "";
            String hints = "";

            myScanQS.nextLine();
            while (myScanQS.hasNext())  //returns a boolean value
            {
                line = myScanQS.nextLine();
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                iDQuest = data[0];
                category = data[1];
                question = data[2];
                correctAnswer = data[3];
                hints = data[4];

                statement.setString(1,iDQuest);
                statement.setString(2,category);
                statement.setString(3,question);
                statement.setString(4,correctAnswer);
                statement.setString(5,hints);
                statement.executeUpdate();
            }
            System.out.println("Inserted tableSA");
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getQues(String theCate, int theId){
        String ques = "";
        if(theCate.equals("MC")){
            ques = getQuestionMC(theId, theCate);
        }
        else if(theCate.equals("TF")){
            ques = getQuestionTF(theId, theCate);
        }
        else {
            ques = getQuestionSA(theId, theCate);
        }
        System.out.println("Question: "+ques);

    }
    public void getAns(String theCate, int theId){
        String ans = "";
        if(theCate.equals("MC")){
            ans = getCorrAnsMC(theId, theCate);
        }
        else if(theCate.equals("TF")){
            ans = getCorrAnsTF(theId, theCate);
        }
        else {
            ans = getCorrAnsSA(theId, theCate);
        }
        System.out.println("Ans: "+ans);
    }
    public void getArrChoices(String theCate, int theId){
        ArrayList<String> choices = new ArrayList<String>();
        if(theCate.equals("MC")){
            choices = getArrChoicesMC(theId, theCate);
            printChoicesMC();
        }
        else if(theCate.equals("TF")){
            choices = getArrChoicesTF(theId, theCate);
            printChoicesTF();
        }

    }

    public void getArrRedChoice(String theCate, int theId){
        ArrayList<String> redChoices = new ArrayList<String>();
        if(theCate.equals("MC")){
            redChoices = getArrRedChoiceMC(theId, theCate);
        }
        printRedChoiceMC();
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
