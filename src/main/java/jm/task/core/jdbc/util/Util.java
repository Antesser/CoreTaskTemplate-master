package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String hostName = "localhost";
    private static final String dbName = "user";
    private static final String userName = "root";
    private static final String password = "u1727578l";


    public static Connection getMySQLConnection()  {

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName+"?useSSL=false";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connectionURL, userName,
                    password);
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        System.out.println("Connected database successfully...");
        return conn;
    }
}