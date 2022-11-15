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
    String myCate;
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

    public QA(String theCate, int theId){
        myCate = theCate;
        myId = theId;
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

    public  String getQuestion(String theCate, int theId){
        myCate = theCate;
        myId = theId;
        return null;
    }
    public String getAnswer(String theCate, int theId){
        myCate = theCate;
        myId = theId;
        return null;
    }
    public ArrayList<String> getChoices(String theCate, int theId){
        myCate = theCate;
        myId = theId;
        return null;
    }

}
