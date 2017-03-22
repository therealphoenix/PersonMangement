package com.klindziuk.personmanagement;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * PhoneDAO.java
 * This DAO class provides CRUD database operations for the table phone
 * in the database.
 */
public class PhoneDAO {
   private DBconnector connector;
   private PreparedStatement preparedStatement;
   private Statement statement;
   private ResultSet resultSet;

    public PhoneDAO() {
        connector = new DBconnector();
    }

    public boolean insertPhone(Phone phone, int person_id) throws SQLException {
        boolean rowInserted = false;
        try {
            String sql = "INSERT INTO phone (owner, number) VALUES (?, ?)";
            connector.connect();
            preparedStatement = connector.jdbcConnection.prepareStatement(sql);
            preparedStatement.setInt(1, person_id);
            preparedStatement.setString(2, phone.getNumber());
            rowInserted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception e) { /* ignored */ }
            try {
                connector.disconnect();
            } catch (Exception e) { /* ignored */ }
        }
        return rowInserted;
    }

    public List<Phone> listAllPhones() throws SQLException {
        ArrayList<Phone> phones = new ArrayList<>();
        try {
            String sql = ("SELECT * FROM `phone` ");
            connector.connect();
            statement = connector.jdbcConnection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int owner = resultSet.getInt("owner");
                String number = resultSet.getString("number");
                Phone phone = new Phone(id, owner, number);
                phones.add(phone);
            }
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (Exception e) { /* ignored */ }
            try {
                statement.close();
            } catch (Exception e) { /* ignored */ }
            try {
                connector.disconnect();
            } catch (Exception e) { /* ignored */ }
        }
        return phones;
    }

    public List<Phone> listPersonPhones(int owner_id) throws SQLException {
        ArrayList<Phone> phones = new ArrayList<>();
        try {
            String sql = "SELECT * FROM phone where owner = ?";
            connector.connect();
            preparedStatement = connector.jdbcConnection.prepareStatement(sql);
            preparedStatement.setInt(1, owner_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int owner = resultSet.getInt("owner");
                String number = resultSet.getString("number");
                Phone phone = new Phone(id, owner, number);
                phones.add(phone);
            }
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (Exception e) { /* ignored */ }
            try {
                preparedStatement.close();
            } catch (Exception e) { /* ignored */ }
            try {
                connector.disconnect();
            } catch (Exception e) { /* ignored */ }
        }
        return phones;
    }

    public boolean deletePhone(Phone phone) throws SQLException {
        boolean rowDeleted = false;
        try {
            String sql = "DELETE FROM phone where id = ?";
            connector.connect();
            preparedStatement = connector.jdbcConnection.prepareStatement(sql);
            preparedStatement.setInt(1, phone.getId());
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception e) { /* ignored */ }
            try {
                connector.disconnect();
            } catch (Exception e) { /* ignored */ }
        }
        return rowDeleted;
    }

    public boolean updatePhone(Phone phone) throws SQLException {
        boolean rowUpdated = false;
        try {
            String sql = "UPDATE phone SET number = ?";
            sql += " WHERE id = ?";
            connector.connect();
            preparedStatement = connector.jdbcConnection.prepareStatement(sql);
            preparedStatement.setString(1, phone.getNumber());
            preparedStatement.setInt(2, phone.getId());
            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception e) { /* ignored */ }
            try {
                connector.disconnect();
            } catch (Exception e) { /* ignored */ }
        }
        return rowUpdated;
    }

    public Phone getPhone(int id, int owner_id) throws SQLException {
        Phone phone = null;
        try {

            String sql = "SELECT * FROM phone WHERE id = ?";
            connector.connect();
            preparedStatement = connector.jdbcConnection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String number = resultSet.getString("number");
                phone = new Phone(id, owner_id, number);
            }
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (Exception e) { /* ignored */ }
            try {
                connector.disconnect();
            } catch (Exception e) { /* ignored */ }
        }
        return phone;
    }

}
