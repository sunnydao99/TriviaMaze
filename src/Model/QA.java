package Model;

import java.io.*;
import java.sql.*;
import java.util.*;

/**
 * This class is created to read data from CSV file and insert data into all tables
 * @version: 10.27.22
 * @author: An Nguyen, Satinder Singh
 *
 */

public class QA extends Inventory implements Serializable{

    private File myFileCSV_QMC;
    private File myFileCSV_QAS;
    private File myFileCSV_QTF;
    private File myFileCSV_QTFE;
    private Scanner myScanMC;
    private Scanner myScanSA;
    private Scanner myScanTF;
    private Scanner myScanTFE;
    private String myCategory;
    private ArrayList<Integer> myIdList;
    private String myCate;
    private int myId;

    private Connection myConn;

    /**
     * This constructor is used to declare four files for 4 tables
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
     * This constructor is built to declare four files for 4 tables
     * and pass two parameters - Category and ID
     * @param theCate the category
     * @param theId  The ID
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
     * This method is created to make a connection to Database
     * @exception SQLException in the case it cannot get the connection
     * @return Connection the connection to database
     */
    public Connection connect() {
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
     * This class is overrider from  insertTableMC() of Inventory class
     * @exception SQLException if it cannot get the connection
     * @exception FileNotFoundException if the file cannot be found
     * @exception IOException if the input or output failed to read or get
     */
    @Override
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
     * This class is overrider from  insertTableTF() of Inventory class
     * @exception SQLException if it cannot get the connection
     * @exception FileNotFoundException if the file cannot be found
     * @exception IOException if the input or output failed to read or get
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
     * This class is overrider from  insertTableSA() of Inventory class
     * @exception SQLException if it cannot get the connection
     * @exception FileNotFoundException if the file cannot be found
     * @exception IOException if the input or output failed to read or get
     */
    @Override
    public void insertTableSA() {
        String sql = "INSERT INTO tableSA(IDQuest,Category, Question, CorrectAnswer, Hints ) VALUES(?, ?,?, ?, ?)";

        try{

            myScanSA = new Scanner(myFileCSV_QAS);
            myScanSA.useDelimiter(",\n");   //sets the delimiter pattern

            String line =  "";
            PreparedStatement statement = connect().prepareStatement(sql);

            String iDQuest = "";
            String category= "";
            String question = "";
            String correctAnswer = "";
            String hints = "";

            myScanSA.nextLine();
            while (myScanSA.hasNext())
            {
                line = myScanSA.nextLine();
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
     * This class is overrider from  insertTableTFExtra() of Inventory class
     * @exception SQLException if it cannot get the connection
     * @exception FileNotFoundException if the file cannot be found
     * @exception IOException if the input or output failed to read or get
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
     * This method is used to get the question from the
     * given category and ID, and return it
     * @param theCate The category of question
     * @param theId The ID of category
     * @return String The question
     */
    public  String getQuestion(String theCate, int theId){
        myCate = theCate;
        myId = theId;
        return null;
    }

    /**
     * This method is built to get the answer from category Question and ID,
     * and return it
     * @param theCate The category of answer
     * @param theId  The ID of answer
     * @return String The answer
     */
    public String getAnswer(String theCate, int theId){
        myCate = theCate;
        myId = theId;
        return null;
    }

    /**
     * This method is used to get a list of choices from given category Question and ID,
     * return the list of choices
     * @param theCate The category of choices
     * @param theId The ID of choices
     * @return ArrayList<String> A list of choices
     */
    public ArrayList<String> getChoices(String theCate, int theId){
        myCate = theCate;
        myId = theId;
        return null;
    }

}
