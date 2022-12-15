package Model;
import org.sqlite.SQLiteDataSource;

import java.sql.*;

/**
 * This class  create Database, four empty tables and the connection to Database.
 * @author: An Nguyen
 * @version: 10/27/2022, updated 12/11/2022
 *
 */

public abstract class Inventory {
    private String myUrl;
    private Connection myConnIven;
    private String myFileName;
    private SQLiteDataSource ds = null;

    /**
     * Initialize the connection and file name
     * Initialized @myUrl, @myConnIven, @myFileName
     */
    public Inventory() {
        myUrl = "jdbc:sqlite:";
        myConnIven = null;
        myFileName = "jdbc:sqlite:Database_QA.db";
    }

    /**
     * This method is used to create database
     * @exception SQLException if the connection isn't created
     */
    public final void createDB() {
        try {
            ds = new SQLiteDataSource();
            ds.setUrl(myFileName);
            //myConnIven = DriverManager.getConnection(myFileName);
            myConnIven = ds.getConnection();
            if (myConnIven != null) {
                DatabaseMetaData meta = myConnIven.getMetaData();
                System.out.println("A new database has been created.");

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Create a connection to database
     * @exception SQLException if the connection to database failed
     */
    public final void connectionDB(){
        try {
            ds = new SQLiteDataSource();
            ds.setUrl(myFileName);
            System.out.println("ds "+ ds);
            myConnIven = ds.getConnection();
            System.out.println( "Connected database successfully" );

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Create the data table for multiple choices
     * @exception SQLException if the table is not created or error
     */
    public final void createTableMC() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS tableMC (\n"
                + " IDQuest integer PRIMARY KEY,\n"
                + " Category  text NOT NULL,\n"
                + " Question text NOT NULL,\n"
                + " ChoiceA text NOT NULL,\n"
                + " ChoiceB text NOT NULL,\n"
                + " ChoiceC text NOT NULL,\n"
                + " ChoiceD text NOT NULL,\n"
                + " CorrectAnswer text NOT NULL,\n"
                + " capacity real\n"
                + ") ";

        try {
            Statement stmt = myConnIven.createStatement();
            stmt.execute(sql);
            System.out.println("created tableMC");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Create the data table for True/False type
     * @exception SQLException if the table is not created or error
     */
    public final void createTableTF() {
        String sql = "CREATE TABLE IF NOT EXISTS tableTF (\n"
                + " IDQuest text PRIMARY KEY,\n"
                + " Category text NOT NULL,\n"
                + " Question text NOT NULL,\n"
                + " ChoiceA text NOT NULL,\n"
                + " ChoiceB text NOT NULL,\n"
                + " CorrectAnswer text NOT NULL,\n"
                + " capacity real\n"
                + ");";

        try {
            Statement stmt = myConnIven.createStatement();
            stmt.execute(sql);
            System.out.println("created tableTF");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Create the data table for short questions and answers
     * @exception SQLException if the table is not created or error
     */
    public final void createTableSA() {
        String sql = "CREATE TABLE IF NOT EXISTS tableSA (\n"
                + " IDQuest text PRIMARY KEY,\n"
                + " Category text NOT NULL,\n"
                + " Question text NOT NULL,\n"
                + " CorrectAnswer text NOT NULL,\n"
                + " Hints text  NULL,\n"
                + " capacity real\n"
                + ");";

        try {
            Statement stmt = myConnIven.createStatement();
            stmt.execute(sql);
            System.out.println("created tableSA");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Create the data table  for True/False Extra questions
     * @exception SQLException if the table is not created or error
     */
    public final void createTableTFExtra() {
        String sql = "CREATE TABLE IF NOT EXISTS tableTFExtra (\n"
                + " IDQuest text PRIMARY KEY,\n"
                + " Category text NOT NULL,\n"
                + " Question text NOT NULL,\n"
                + " ChoiceA text NOT NULL,\n"
                + " ChoiceB text NOT NULL,\n"
                + " CorrectAnswer text NOT NULL,\n"
                + " capacity real\n"
                + ");";

        try {
            Statement stmt = myConnIven.createStatement();
            stmt.execute(sql);
            System.out.println("created tableTFExtra");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     *  create abstract method to insert table multiple choices
     */
    public abstract void insertTableMC();

    /**
     *  create abstract method to insert table True or False
     */
    public abstract void insertTableTF();

    /**
     * create abstract method to insert table Short Answer
     */
    public abstract void insertTableSA();

    /**
     *  create abstract method to insert table True or False extra
     */
    public abstract void insertTableTFExtra();



}
