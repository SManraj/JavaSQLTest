/*
    TODO: 
    - Get Java File to print out each and every word in the txt file *DONE 
    - Then get the words stored in the sql server *DONE
    - Don't add word count to words entity 
    - Create inteface which conncets to mysql server (Interaface will be done for each respective java file) 

 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class words_sql {

    public static void main(String[] args) {

        String pathToFile = "test-files-output-copy/newtextutf8-output.txt"; // State file path for each java file so you know what files its gonna work with
        int usrSelect = -1;
        Scanner scanner = new Scanner(System.in);

        Connection conn = SQLConnectionProtocol.connect();

        try {
            System.out.println("Select an option:\n[0] Insert words to DB\n[1] Print every word in text file");
            usrSelect = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("\nWrong input, int only!");
        }
        if (usrSelect == 0) {
            insertWordstoDB(conn, pathToFile);
        } else {
            printEveryWordInTxt(pathToFile);
        }

        /**
         * Note: Put this somewhere else // Show all tables in the database
         * String query = "SHOW TABLES;"; ResultSet rs =
         * stmt.executeQuery(query);
         *
         *          *System.out.println("📋 Tables in database '" + conn.getCatalog() +
         * "':"); while (rs.next()) { System.out.println(" - " +
         * rs.getString(1)); }
         *
         *          *rs.close(); stmt.close();
         */
    }

    //    PRINTS EVERY WORD LINE BY LINE IN THE TERMINAL
    public static void printEveryWordInTxt(String path) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+"); // ✅ lowercase 'split'
                for (String w : words) {
                    System.out.println(w);
                }
            }
        } catch (IOException e) {
            System.err.println("⚠️ ERROR reading file: " + e.getMessage());
        }
    }

    public static void insertWordstoDB(Connection conn, String path) {
        String SQLQuery = "INSERT INTO words (word) VALUES (?)";
        String SQLDeleteWords = "DELETE FROM WORDS";
        try (BufferedReader br = new BufferedReader(new FileReader(path)); PreparedStatement prepStmnt = conn.prepareStatement(SQLQuery)) { // ✅ lowercase 'prepareStatement'

            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("(?<=\\s)(?=.*\\s)|\\s+(?=.*\\s)");
                for (String w : words) {
                    if (w.isEmpty()) {
                        continue;
                    }
                    prepStmnt.setString(1, w);
                    prepStmnt.executeUpdate();
                    System.out.println("Inserted word: " + w);
                }
            }

        } catch (IOException e) {
            System.err.println("⚠️ File error: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("⚠️ SQL error: " + e.getMessage());
        }

        //  Delete words query 
        String usrSelect;
        Scanner scanner = new Scanner(System.in);

        System.out.println("DELETE WORDS? [Y] [N]");
        usrSelect = scanner.nextLine();

        if (usrSelect.equalsIgnoreCase("Y")) {
            try (Statement stmt = conn.createStatement()) {
                int rowsDeleted = stmt.executeUpdate(SQLDeleteWords);
                System.out.println("Deleted " + rowsDeleted + " rows from words table");

            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        } else {
            scanner.close();
        }

    }
}
