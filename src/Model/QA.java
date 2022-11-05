package Model;

import java.io.*;
import java.lang.reflect.Array;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.*;

public class QA extends Inventory{
    Inventory inv;

    File myFileCSV_QMC;
    File myFileCSV_QAS;
    File myFileCSV_QTF;
    Scanner myScanMC;
    Scanner myScanQS;
    Scanner myScanTF;
    public QA(){
        myFileCSV_QMC = new File("Database/QAMultiple.csv");
        myFileCSV_QAS = new File("Database/QAShort.csv");
        myFileCSV_QTF = new File("Database/QATrueFalse.csv");
    }

    @Override
    public void connection() {
        try {
            // db parameters
            // create a connection to the database
            conn = DriverManager.getConnection(myFileName);
            System.out.println("Connection: cnn from QA" + conn);
            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public  void insertTableMC(){
        String sql = "INSERT INTO tableMC(IDQuest,Category, Question, ChoiceA, ChoiceB, ChoiceC, ChoiceD, CorectAnswer, ReduceChoice1, ReduceChoice2) VALUES(?, ?,?, ?, ?, ?, ? , ?, ?, ?)";

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
            String corectAnswer = "";
            String reduceChoice1 = "";
            String reduceChoice2 = "";

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
                corectAnswer = data[7];
                reduceChoice1 = data[8];
                reduceChoice2 = data[9];

                statement.setString(1,iDQuest);
                statement.setString(2,category);
                statement.setString(3,question);
                statement.setString(4,choiceA);
                statement.setString(5,choiceB);
                statement.setString(6,choiceC);
                statement.setString(7,choiceD);
                statement.setString(8,corectAnswer);
                statement.setString(9,reduceChoice1);
                statement.setString(10,reduceChoice2);
                statement.executeUpdate();
            }

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
        String sql = "INSERT INTO tableTF(IDQuest,Category,Question,ChoiceA,ChoiceB,CorectAnswer) VALUES(?, ?,?, ?, ?, ?)";

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
            String corectAnswer = "";

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
                corectAnswer = data[5];

                statement.setString(1,iDQuest);
                statement.setString(2,category);
                statement.setString(3,question);
                statement.setString(4,choiceA);
                statement.setString(5,choiceB);
                statement.setString(6,corectAnswer);

                statement.executeUpdate();
            }
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
        String sql = "INSERT INTO tableSA(IDQuest,Category, Question, Choice, CorectAnswer, Hints) VALUES(?, ?,?, ?, ?, ?)";

        try{

            myScanQS = new Scanner(myFileCSV_QAS);
            myScanQS.useDelimiter(",\n");   //sets the delimiter pattern

            String line =  "";
            PreparedStatement statement = conn.prepareStatement(sql);

            String iDQuest = "";
            String category= "";
            String question = "";
            String choice = "";
            String corectAnswer = "";
            String hints = "";

            myScanQS.nextLine();
            while (myScanQS.hasNext())  //returns a boolean value
            {
                line = myScanQS.nextLine();
                String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                iDQuest = data[0];
                category = data[1];
                question = data[2];
                choice = data[3];
                corectAnswer = data[4];
                hints = data[5];

                statement.setString(1,iDQuest);
                statement.setString(2,category);
                statement.setString(3,question);
                statement.setString(4,choice);
                statement.setString(5,corectAnswer);
                statement.setString(6,hints);
                statement.executeUpdate();
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}
