package com.klindziuk.personmanagement.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class provides connection to database.
 */
public class DBconnector {
    private static final String URL ="jdbc:mysql://localhost:3306/phonebook";
    private static final  String USERNAME = "root";
    private static final  String PASSWORD = "root";
    private static DBconnector instance = null;
    public Connection jdbcConnection;

    public static DBconnector getInstance()
    {
        if (instance == null)
        {
            instance = new DBconnector();
        }

        return instance;
    }

    public void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                    URL, USERNAME, PASSWORD);
        }
    }

    public void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
}