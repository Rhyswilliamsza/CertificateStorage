//RhysIT.co.za
package Backend;

import java.sql.*;

public class DatabaseOperations {
    static Connection dbConnect = null;

    public static void connect() {
        try {
            dbConnect = DriverManager.getConnection("jdbc:sqlite:Data/Certificates.db");
            System.out.println("Database connected successfully!");
            create();
        } catch (SQLException e) {
            System.out.println("Database error." + e);
        }
    }

    public static void disconnect() {
        try {
            System.out.println("Database closed successfully!");
            dbConnect.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void create() {
        queryVoid("CREATE TABLE IF NOT EXISTS \"certificates\" (\n" +
                "\t`private`\tString UNIQUE,\n" +
                "\t`public`\tString UNIQUE,\n" +
                "\t`intermediate`\tString\n" +
                ");");
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
