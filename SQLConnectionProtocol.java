
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * SQLConnectionProtocol
 *
 * A reusable helper class to manage MySQL connections.
 *
 * Usage: Connection conn = SQLConnectionProtocol.connect(); ...
 * SQLConnectionProtocol.close(conn);
 */
public class SQLConnectionProtocol {

    private static final String URL = "jdbc:mysql://localhost:3306/words_test?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("\n****    SQL CONNECTION SUCCESSFUL!!! :D    ****\n");
            return conn;
        } catch (SQLException e) {
            System.out.println("\n****    ERROR SQL CONNECTION FAILED :(    ****\n");
            return null;
        }
    }

    /* Optional Method - I reccommend closing server on terminal lwk 
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println(" Connection closed.");
            } catch (SQLException e) {
                System.err.println(" Error closing connection: " + e.getMessage());
            }
        }
    }
     */
}
