package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import game.User;

public class UserDAO extends DAO<User> {

	public User create(User user) {
		try {

			PreparedStatement prepare = this.connect
					.prepareStatement("INSERT INTO user (name, password) VALUES(?, ?) ");
			prepare.setString(1, user.getName());
			prepare.setString(2, user.getPassword());

			prepare.executeUpdate();
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery("SELECT * FROM user");
			result.last();

			user = this.find(result.getLong("id"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	public User find(long id) {
		User user = new User();
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)
					.executeQuery("SELECT * FROM user WHERE id = " + id);

			if (result.first())

				user = new User(result.getLong("id"), result.getString("name"), result.getString("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public User findByUserName(String userName) {
		User user = null;

		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM user WHERE name = '" + userName + "'");
			if (result.first())
				user = new User(result.getLong("id"), result.getString("name"), result.getString("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User update(User obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(User obj) {
		// TODO Auto-generated method stub

	}

}
