package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    static Connection connection = null;
    private Util() throws SQLException {
    }
    public static Connection connect() {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root1234");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("problems");
        }
        return connection;
    }
}
