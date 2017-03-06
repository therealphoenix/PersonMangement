package com.klindziuk.personmanagement;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Hp on 23.02.2017.
 */
public class JDBCconnector {
    public static final String URL ="jdbc:mysql://localhost:3306/phonebook";
    public static final  String USERNAME = "root";
    public static final  String PASSWORD = "root";
    public  java.sql.Connection jdbcConnection;

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
