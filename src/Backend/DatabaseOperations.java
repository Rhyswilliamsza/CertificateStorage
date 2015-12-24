//RhysIT.co.za
package Backend;

import java.sql.*;

public class DatabaseOperations {
    static Connection dbConnect = null;

    public static void connect() {
        try {
            dbConnect = DriverManager.getConnection("jdbc:sqlite:Certificates.db");
            System.out.println("Database connected successfully!");
        } catch (SQLException e) {
            System.out.println("Database error." + e);
        }
    }

    public static void disconnect() {
        try {
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void queryVoid(String statement) {
        try {
            Statement query = dbConnect.createStatement();
            query.executeUpdate(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet queryResultSet(String statement) {
        try {
            Statement query = dbConnect.createStatement();
            ResultSet rs = query.executeQuery(statement);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
