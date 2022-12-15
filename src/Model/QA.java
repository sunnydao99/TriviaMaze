package Model;

import java.io.*;
import java.lang.reflect.Array;
import java.sql.*;
import java.util.*;

/**
 * @author: An Nguyen
 * @version: 10/27/2022
 *
 */

/**
 * This class reads data from CSV file and insert data into all tables
 * This class will extend Inventory class
 */
public class QA extends Inventory implements Serializable{

    private File myFileCSV_QMC;
    private File myFileCSV_QAS;
    private File myFileCSV_QTF;
    private File myFileCSV_QTFE;
    private Scanner myScanMC;
    private Scanner myScanQS;
    private Scanner myScanTF;
    private Scanner myScanTFE;
    private String myCategory;
    private ArrayList<Integer> myIdList;
    private String myCate;
    private int myId;

    private Connection myConn;

    /**
     * QA(): constructor declares four files for 4 tables
     */
    public QA(){
        myFileCSV_QMC = new File("Database/QAMultiple.csv");
        myFileCSV_QAS = new File("Database/QAShort.csv");
        myFileCSV_QTF = new File("Database/QATrueFalse.csv");
        myFileCSV_QTFE = new File("Database/QATrueFalseExtra.csv");
        myCategory = "";
        myConn = null;
        connect();
    }

    /**
     * QA(): constructor declares four files for 4 tables  and passing two parameters
     * @param theCate: category
     * @param theId: id
     */
    public QA(String theCate, int theId){
        myCate = theCate;
        myId = theId;
        myFileCSV_QMC = new File("Database/QAMultiple.csv");
        myFileCSV_QAS = new File("Database/QAShort.csv");
        myFileCSV_QTF = new File("Database/QATrueFalse.csv");
        myFileCSV_QTFE = new File("Database/QATrueFalseExtra.csv");
        myCategory = "";

    }

    /**
     * connect(): connect Database
     * @return: Connection
     */
    private Connection connect() {
        // SQLite connectionDB string
        String url = "jdbc:sqlite:Database_QA.db";

        try {
            myConn = DriverManager.getConnection(url);
            System.out.println("myConn: " + myConn);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println( "Connected database successfully" );
        return myConn;
    }

    /**
     * insertTableMC(): overrider insertTableMC() from Inventory class
     * This method inserts data into tableMC
     */
    public void insertTableMC(){
        String sql = "INSERT INTO tableMC(IDQuest,Category, Question, ChoiceA, ChoiceB, ChoiceC, ChoiceD, CorrectAnswer)" +
                " VALUES(?, ?,?, ?, ?, ?, ? , ?)";

        try{

            myScanMC = new Scanner(myFileCSV_QMC);
            myScanMC.useDelimiter(",\n");   //sets the delimiter pattern

            String line =  "";
            PreparedStatement statement = connect().prepareStatement(sql);
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

    /**
     * insertTableTF(): overrider insertTableTF() from Inventory class
     * This method inserts data into tableTF
     */
    @Override
    public void insertTableTF() {
        String sql = "INSERT INTO tableTF(IDQuest,Category,Question,ChoiceA,ChoiceB,CorrectAnswer) " +
                "VALUES(?, ?,?, ?, ?, ?)";

        try{
            myScanTF = new Scanner(myFileCSV_QTF);
            myScanTF.useDelimiter(",\n");   //sets the delimiter pattern

            String line =  "";
            PreparedStatement statement = connect().prepareStatement(sql);

            String iDQuest = "";
            String category= "";
            String question = "";
            String choiceA = "";
            String choiceB = "";
            String correctAnswer = "";

            myScanTF.nextLine();
            while (myScanTF.hasNext())
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

    /**
     * insertTableSA(): overrider insertTableSA() from Inventory class
     * This method inserts data into tableSA
     */
    @Override
    public void insertTableSA() {
        String sql = "INSERT INTO tableSA(IDQuest,Category, Question, CorrectAnswer, Hints ) VALUES(?, ?,?, ?, ?)";

        try{

            myScanQS = new Scanner(myFileCSV_QAS);
            myScanQS.useDelimiter(",\n");   //sets the delimiter pattern

            String line =  "";
            PreparedStatement statement = connect().prepareStatement(sql);

            String iDQuest = "";
            String category= "";
            String question = "";
            String correctAnswer = "";
            String hints = "";

            myScanQS.nextLine();
            while (myScanQS.hasNext())
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

    /**
     * insertTableTFExtra(): overrider insertTableTFExtra() from Inventory class
     * This method inserts data into tableTFExtra
     */
    public void insertTableTFExtra() {
        String sql = "INSERT INTO tableTFExtra(IDQuest,Category,Question,ChoiceA,ChoiceB,CorrectAnswer) " +
                "VALUES(?, ?,?, ?, ?, ?)";

        try{
            myScanTFE = new Scanner(myFileCSV_QTFE);
            myScanTFE.useDelimiter(",\n");   //sets the delimiter pattern

            String line =  "";
            PreparedStatement statement = connect().prepareStatement(sql);

            String iDQuest = "";
            String category= "";
            String question = "";
            String choiceA = "";
            String choiceB = "";
            String correctAnswer = "";

            myScanTFE.nextLine();
            while (myScanTFE.hasNext())
            {
                line = myScanTFE.nextLine();
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
            System.out.println("Inserted tableTFE");
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * getQuestion(String, int): from category Question and ID, this method return Question
     * @param theCate: category
     * @param theId: id
     * @return: String
     */
    public  String getQuestion(String theCate, int theId){
        myCate = theCate;
        myId = theId;
        return null;
    }

    /**
     * getAnswer(String, int): from category Question and ID, this method return Answer
     * @param theCate: category
     * @param theId: id
     * @return: String
     */
    public String getAnswer(String theCate, int theId){
        myCate = theCate;
        myId = theId;
        return null;
    }

    /**
     * getChoices(String, int): from category Question and ID, this method return choices
     * @param theCate: category
     * @param theId: id
     * @return: ArrayList<string>
     */
    public ArrayList<String> getChoices(String theCate, int theId){
        myCate = theCate;
        myId = theId;
        return null;
    }

}
