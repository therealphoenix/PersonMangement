package com.klindziuk.personmanagement;

import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Hp on 23.02.2017.
 */
public class JDBCconnector {
    protected String jdbcURL;
    protected String jdbcUsername;
    protected String jdbcPassword;
    protected java.sql.Connection jdbcConnection;

    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(
                    jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }
}
