package Model;
import org.sqlite.SQLiteDataSource;

import java.sql.*;

public abstract class Inventory {
    private String myUrl;
    private Connection myConnIven;
    private String myFileName;
    private SQLiteDataSource ds = null;

    //should be change public instance vars into private  vars and create getter() and setter() methods

    public Inventory() {
        myUrl = "jdbc:sqlite:";
        myConnIven = null;
        myFileName = "jdbc:sqlite:Database_QA.db";
    }

    public Inventory(String theFileName) {
        try {
            // create a connection to the database
           /* myConnIven = DriverManager.getConnection(myUrl);
            System.out.println("Connection to SQLite has been established.");*/

            ds = new SQLiteDataSource();
            ds.setUrl(myFileName);

        }  catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println( "Opened database successfully" );
    }


    public void createDB() {
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

    public final void connection(){
        try {
            // create a connection to the database
            //myConnIven = DriverManager.getConnection(myFileName);
            ds = new SQLiteDataSource();
            ds.setUrl(myFileName);
            System.out.println("ds "+ ds);
            myConnIven = ds.getConnection();
            System.out.println("from Inventoyry: " + myConnIven);
            System.out.println( "Connected database successfully" );

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

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
            /* Connection myConnIven = DriverManager.getConnection(myFileName);*/
            Statement stmt = myConnIven.createStatement();
            stmt.execute(sql);
            System.out.println("created tableMC");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public final void createTableTF() {
        // SQL statement for creating a new table
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

    public final void createTableSA() {
        // SQL statement for creating a new table
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

    public final void createTableTFExtra() {
        // SQL statement for creating a new table
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

    public abstract void insertTableMC();
    public abstract void insertTableTF();
    public abstract void insertTableSA();
    public abstract void insertTableTFExtra();



}
