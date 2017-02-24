package com.klindziuk.personmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * AbstractDAO.java
 * This DAO class provides CRUD database operations for the table book
 * in the database.
 * @author www.codejava.net
 *
 */
public class PersonDAO extends JDBCconnector{

	
	public PersonDAO(String jdbcURL, String jdbcUsername, String jdbcPassword)  {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;

	}

	public boolean insertPerson(Person person) throws SQLException {
		String sql = "INSERT INTO person (name, surname, middlename) VALUES (?, ?, ?)";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, person.getName());
		statement.setString(2, person.getSurname());
		statement.setString(3, person.getMiddlename());
		
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}
	
	public List<Person> listAllPersons() throws SQLException {
		List<Person> listPerson = new ArrayList<>();
		
		String sql = "SELECT * FROM person";
		
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			String surname = resultSet.getString("surname");
			String middlename = resultSet.getString("middlename");

			Person person = new Person(id, name, surname, middlename);
			listPerson.add(person);
		}
		
		resultSet.close();
		statement.close();
		
		disconnect();
		
		return listPerson;
	}


	public boolean deletePerson(Person person) throws SQLException {
		String sql = "DELETE FROM person where id = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, person.getId());
		
		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;		
	}
	
	public boolean updatePerson(Person person) throws SQLException {
		String sql = "UPDATE person SET name = ?, surname = ?, middlename = ?";
		sql += " WHERE id = ?";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, person.getName());
		statement.setString(2, person.getSurname());
		statement.setString(3, person.getMiddlename());
		statement.setInt(4, person.getId());
		
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;		
	}

	public Person getPerson(int id) throws SQLException {
		Person person = null;
		String sql = "SELECT * FROM person WHERE id = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			String name = resultSet.getString("name");
			String surname = resultSet.getString("surname");
			String middlename = resultSet.getString("middlename");

			person = new Person(id, name, surname, middlename);
		}
		
		resultSet.close();
		statement.close();
		
		return person;
	}


}
