package edu.northeastern.cs5200;

import java.sql.*;

public class YuConnection {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/cs5200_spring2020_qi_yu_jdbc_a3";
    private static final String USER = "root";
    private static final String PASSWORD = "13572468";
    private static Connection dbConnection = null;

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER);

        if (dbConnection == null) {
            dbConnection = DriverManager.getConnection(URL, USER, PASSWORD);
            return dbConnection;
        } else { return dbConnection; } }

    public static void closeConnection() {
        try {
            if (dbConnection != null) {
                dbConnection.close();
                dbConnection = null; }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
