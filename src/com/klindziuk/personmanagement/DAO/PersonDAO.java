package com.klindziuk.personmanagement.DAO;

import com.klindziuk.personmanagement.entity.Person;
import com.klindziuk.personmanagement.util.DBconnector;
import com.klindziuk.personmanagement.entity.Phone;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * PersonDAO.java
 * This DAO class provides CRUD database operations for the table person
 * in the database.
 */
public class PersonDAO {
    private DBconnector connector;
    private  PreparedStatement preparedStatement;
    private  Statement statement;
    private  ResultSet resultSet;

    public PersonDAO() {
        connector = DBconnector.getInstance();
    }

    public boolean insertPerson(Person person) {
        boolean rowInserted = false;
        try {
            String sql = "INSERT INTO person (name, surname, middlename) VALUES (?, ?, ?)";
            connector.connect();
            preparedStatement = connector.jdbcConnection.prepareStatement(sql);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurname());
            preparedStatement.setString(3, person.getMiddlename());
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

    public List<Person> listAllPersons() {
        List<Person> listPerson = new ArrayList<>();
        try {
            String sql = "SELECT * FROM person";
            connector.connect();
            statement = connector.jdbcConnection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String middlename = resultSet.getString("middlename");
                Person person = new Person(id, name, surname, middlename);
                listPerson.add(person);
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
        return listPerson;
    }

    public boolean deletePerson(Person person) {
        boolean rowDeleted = false;
        try {
            String sql = "DELETE FROM person where id = ?";
            connector.connect();
            preparedStatement = connector.jdbcConnection.prepareStatement(sql);
            preparedStatement.setInt(1, person.getId());
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

    public boolean updatePerson(Person person) {
        boolean rowUpdated = false;
        try {
            String sql = "UPDATE person SET name = ?, surname = ?, middlename = ?";
            sql += " WHERE id = ?";
            connector.connect();
            preparedStatement = connector.jdbcConnection.prepareStatement(sql);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurname());
            preparedStatement.setString(3, person.getMiddlename());
            preparedStatement.setInt(4, person.getId());
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

    public Person getPerson(int id) {
        Person person = null;
        try {
            String sql = "SELECT * FROM person WHERE id = ?";
            connector.connect();
            preparedStatement = connector.jdbcConnection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String middlename = resultSet.getString("middlename");
                person = new Person(id, name, surname, middlename);
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
        return person;
    }
}