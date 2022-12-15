package Model;
import org.sqlite.SQLiteDataSource;

import java.sql.*;

/**
 * @author: An Nguyen
 * @version: 10/27/2022, updated 12/11/2022
 *
 */

/**
 * This class  create Database, connect Database, and create four empty tables.
 * tableMC presents for Multiple Choice
 * tableTF presents for True/False
 * tableSA presents for Short Answer
 * tableTFExtra presents for True/False Extra will be used switch question.
 */
public abstract class Inventory {
    private String myUrl;
    private Connection myConnIven;
    private String myFileName;
    private SQLiteDataSource ds = null;

    /**
     * Constructor: void
     * Initialized @myUrl, @myConnIven, @myFileName
     */
    public Inventory() {
        myUrl = "jdbc:sqlite:";
        myConnIven = null;
        myFileName = "jdbc:sqlite:Database_QA.db";
    }

    /**
     * createDB(): void
     * Create database
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
     * connectionDB(): void
     * Create connectionDB database
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
     * createTableMC(): void
     * Create tableMC for Multiple Choice
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
     * createTableTF(): void
     * Create tableTF for True/False
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
     * createTableSA(): void
     * Create tableSA for Short Answer
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
     * createTableTFExtra(): void
     * Create tableTFExtra for True/False Extra
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
     * insertTableMC(): create abstract method
     */
    public abstract void insertTableMC();

    /**
     * insertTableTF(): create abstract method
     */
    public abstract void insertTableTF();

    /**
     * insertTableSA(): create abstract method
     */
    public abstract void insertTableSA();

    /**
     * insertTableTFExtra(): create abstract method
     */
    public abstract void insertTableTFExtra();



}
