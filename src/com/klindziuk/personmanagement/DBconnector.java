package com.klindziuk.personmanagement;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnector {
    private static final String URL ="jdbc:mysql://localhost:3306/phonebook";
    private static final  String USERNAME = "root";
    private static final  String PASSWORD = "root";
    protected java.sql.Connection jdbcConnection;

    protected void connect() throws SQLException {
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

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
}
