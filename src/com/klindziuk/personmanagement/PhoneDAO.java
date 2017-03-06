package com.klindziuk.personmanagement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hp on 23.02.2017.
 */
public class PhoneDAO {
		JDBCconnector connector;

	public PhoneDAO()  {
		connector = new JDBCconnector();

	}

    	public boolean insertPhone(Phone phone,int person_id) throws SQLException {
		String sql = "INSERT INTO phone (owner, number) VALUES (?, ?)";
		connector.connect();

		PreparedStatement statement = connector.jdbcConnection.prepareStatement(sql);
		statement.setInt(1, person_id);
		statement.setString(2, phone.getNumber());


		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		connector.disconnect();
		return rowInserted;
	}

    public List<Phone> listAllPhones() throws SQLException {
        ArrayList<Phone> phones = new ArrayList<>();


		String sql = ("SELECT * FROM `phone` ");

		connector.connect();

		Statement statement = connector.jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			int owner = resultSet.getInt("owner");
			String number = resultSet.getString("number");

			Phone phone = new Phone(id, owner, number);
			phones.add(phone);
		}

		resultSet.close();
		statement.close();

		connector.disconnect();

        return phones;
    }

	public List<Phone> listPersonPhones(int owner_id) throws SQLException {
		ArrayList<Phone> phones = new ArrayList<>();

		String sql = "SELECT * FROM phone where owner = ?";
		connector.connect();

		PreparedStatement statement = connector.jdbcConnection.prepareStatement(sql);
		statement.setInt(1, owner_id);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			int id = resultSet.getInt("id");
			int owner = resultSet.getInt("owner");
			String number = resultSet.getString("number");

			Phone phone = new Phone(id, owner, number);
			phones.add(phone);
		}

		resultSet.close();
		statement.close();

		connector.disconnect();

		return phones;
	}


	public boolean deletePhone(Phone phone) throws SQLException {
		String sql = "DELETE FROM phone where id = ?";

		connector.connect();

		PreparedStatement statement = connector.jdbcConnection.prepareStatement(sql);
		statement.setInt(1, phone.getId());

		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		connector.disconnect();
		return rowDeleted;
	}

	public boolean updatePhone(Phone phone) throws SQLException {
		String sql = "UPDATE phone SET number = ?";
		sql += " WHERE id = ?";
		connector.connect();

		PreparedStatement statement = connector.jdbcConnection.prepareStatement(sql);
		statement.setString(1, phone.getNumber());
		statement.setInt(2, phone.getId());

		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		connector.disconnect();
		return rowUpdated;
	}

	public Phone getPhone(int id, int owner_id) throws SQLException {
		Phone phone = null;
		String sql = "SELECT * FROM phone WHERE id = ?";

		connector.connect();

		PreparedStatement statement = connector.jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {

			String number = resultSet.getString("number");
			phone = new Phone(id,owner_id,number);
		}

		resultSet.close();
		statement.close();

		return phone;
	}

}
