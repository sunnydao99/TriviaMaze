package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Room1Model {
    public void connect(){
        System.out.println("hello from Room1");
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:/Users/annguyen/Documents/UWTacoma/TCSS360/TriviaMaze/Questions.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

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
}
