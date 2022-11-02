package Model;
import java.sql.*;

public class Inventory {
    private String myUrl;
    private Connection conn;
    private String myFileName;

    public Inventory() {
        myUrl = "jdbc:sqlite:";
        conn = null;
        myFileName = "";
    }

    public Inventory(String theFileName) {
        try {
            // db parameters
            // create a connection to the database
            conn = DriverManager.getConnection(myUrl);
            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public void createDB(String theFileName) {
        myFileName = myUrl + theFileName;
        try {
            conn = DriverManager.getConnection(myFileName);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createNewTable() {
        // SQLite connection string

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS employees (\n"
                + " id integer PRIMARY KEY,\n"
                + " name text NOT NULL,\n"
                + " capacity real\n"
                + ");";

        try {
            Connection conn = DriverManager.getConnection(myFileName);
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
