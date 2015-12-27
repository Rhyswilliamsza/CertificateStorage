//RhysIT.co.za
package Backend;

import java.sql.*;

public class DatabaseOperations {
    static Connection dbConnect = null;

    public static void connect() {
        try {
            dbConnect = DriverManager.getConnection("jdbc:sqlite:Certificates.db");
            System.out.println("Database connected successfully!");
            create();
        } catch (SQLException e) {
            System.out.println("Database error." + e);
        }
    }

    public static void create() {
        queryVoid("CREATE TABLE IF NOT EXISTS certificates (\"private\" String, \"public\" String, UNIQUE (\"private\", \"public\"));");
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
        ResultSet rs = null;
        try {
            Statement query = dbConnect.createStatement();
            rs = query.executeQuery(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}