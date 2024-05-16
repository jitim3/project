package project.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

import project.dao.UserDao;
import project.dao.entity.User;
import project.util.DatabaseConnectionFactory;
import project.util.UserType;

public class DefaultUserDao implements UserDao {
	private static final String SQL_SELECT_USER_EXISTS = "SELECT username FROM user WHERE username = ?";
	private static final String SQL_SELECT_USER = """
			SELECT id, username, password, user_type_id, created_at, updated_at 
			FROM user
			WHERE username = ? AND password = ? AND user_type_id = ?
			""";
	private static final String SQL_INSERT_USER = """
			INSERT INTO user (username, password, user_type_id, created_at)
			VALUES (?, ?, ?, ?)
			""";
	private final Connection connection;

	public DefaultUserDao() {
		this.connection = DatabaseConnectionFactory.getConnection();
	}

	@Override
	public boolean isUserExists(String username) {
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_USER_EXISTS)) {
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public Optional<User> getAdmin(String username, String password) {
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_USER)) {
			int i = 1;
			statement.setString(i++, username);
			statement.setString(i++, password);
			statement.setInt(i++, UserType.ADMIN.id());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return Optional.of(mapToUser(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Optional.empty();
	}

	@Override
	public Optional<User> getCustomer(String username, String password) {
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_USER)) {
			int i = 1;
			statement.setString(i++, username);
			statement.setString(i++, password);
			statement.setInt(i++, UserType.CUSTOMER.id());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return Optional.of(mapToUser(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Optional.empty();
	}

	@Override
	public boolean createUser(User user) {
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_INSERT_USER)) {
			int i = 1;
			statement.setString(i++, user.getUsername());
			statement.setString(i++, user.getPassword());
			statement.setInt(i++, user.getUserTypeId());
			statement.setTimestamp(i++, Timestamp.from(user.getCreatedAt()));
			return statement.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	private static User mapToUser(ResultSet rs) throws SQLException {
		var createdAt = rs.getTimestamp("created_at");
        var updatedAt = rs.getTimestamp("updated_at");
        
		return new User(
				rs.getLong("id"), 
				rs.getString("username"), 
				rs.getString("password"),
				rs.getInt("user_type_id"), 
				createdAt != null ? createdAt.toInstant() : null,
		        updatedAt != null ? updatedAt.toInstant() : null
			);
	}

}
