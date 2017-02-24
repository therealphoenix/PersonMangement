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
 * Created by Hp on 23.02.2017.
 */
public class PhoneDAO extends JDBCconnector {

	public PhoneDAO(String jdbcURL, String jdbcUsername, String jdbcPassword)  {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;

	}

    	public boolean insertPhone(Phone phone,int person_id) throws SQLException {
		String sql = "INSERT INTO phone (owner, number) VALUES (?, ?)";
		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, person_id);
		statement.setString(2, phone.getNumber());


		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}

    public List<Phone> listAllPhones() throws SQLException {
        ArrayList<Phone> phones = new ArrayList<>();

		//  WHERE `owner`=" + owner_id );
		String sql = ("SELECT * FROM `phone` ");

		connect();

		Statement statement = jdbcConnection.createStatement();
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

		disconnect();

        return phones;
    }


	public boolean deletePhone(Phone phone) throws SQLException {
		String sql = "DELETE FROM phone where id = ?";

		connect();

		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, phone.getId());

		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;
	}



}
