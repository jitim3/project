package project.dao.impl;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Optional;

import project.dao.UserDao;
import project.dao.entity.User;
import project.dao.entity.UserType;
import project.dto.CreateUserDto;
import project.util.DatabaseConnectionFactory;
import project.util.UserTypes;

public class DefaultUserDao implements UserDao {
	private static final Logger LOGGER = System.getLogger(DefaultUserDao.class.getName());
	private static final String SQL_SELECT_USER_EXISTS = "SELECT username FROM user WHERE username = ?";
	private static final String SQL_SELECT_USER_BY_USERNAME_AND_PASSWORD = """
			SELECT
				u.id AS user_id, u.username, u.password, u.created_at, u.updated_at,
				ut.id AS user_type_id, ut.name AS user_type_name, ut.description AS user_type_description,
				ut.created_at AS user_type_created_at, ut.updated_at AS user_type_updated_at
			FROM user u
			LEFT JOIN user_type ut ON ut.id = u.user_type_id
			WHERE u.username = ? AND u.password = ?
			""";
	private static final String SQL_SELECT_USER = """
			SELECT
				u.id AS user_id, u.username, u.password, u.created_at, u.updated_at,
				ut.id AS user_type_id, ut.name AS user_type_name, ut.description AS user_type_description,
				ut.created_at AS user_type_created_at, ut.updated_at AS user_type_updated_at
			FROM user u
			LEFT JOIN user_type ut ON ut.id = u.user_type_id
			WHERE u.username = ? AND u.password = ? AND user_type_id = ?
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
			LOGGER.log(Level.ERROR, e);
		}

		return true;
	}

	@Override
	public Optional<User> getUserByUsernameAndPassword(String username, String password) {
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_USER_BY_USERNAME_AND_PASSWORD)) {
			int i = 1;
			statement.setString(i++, username);
			statement.setString(i++, password);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return Optional.of(mapToUser(rs));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}

		return Optional.empty();
	}

	@Override
	public Optional<User> getSuperAdmin(String username, String password) {
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_USER)) {
			int i = 1;
			statement.setString(i++, username);
			statement.setString(i++, password);
			statement.setInt(i++, UserTypes.SUPER_ADMIN.id());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return Optional.of(mapToUser(rs));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}

		return Optional.empty();
	}

	@Override
	public Optional<User> getAdmin(String username, String password) {
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_USER)) {
			int i = 1;
			statement.setString(i++, username);
			statement.setString(i++, password);
			statement.setInt(i++, UserTypes.ADMIN.id());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return Optional.of(mapToUser(rs));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}

		return Optional.empty();
	}

	@Override
	public Optional<User> getCustomer(String username, String password) {
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_USER)) {
			int i = 1;
			statement.setString(i++, username);
			statement.setString(i++, password);
			statement.setInt(i++, UserTypes.CUSTOMER.id());
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return Optional.of(mapToUser(rs));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}

		return Optional.empty();
	}

	@Override
	public boolean createUser(CreateUserDto createUserDto) {
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_INSERT_USER)) {
			int i = 1;
			statement.setString(i++, createUserDto.username());
			statement.setString(i++, createUserDto.password());
			statement.setInt(i++, createUserDto.userTypeId());
			statement.setTimestamp(i++, Timestamp.from(createUserDto.createdAt()));
			return statement.executeUpdate() == 1;
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}

		return false;
	}

	private static User mapToUser(ResultSet rs) throws SQLException {
		var createdAt = rs.getTimestamp("created_at");
		var updatedAt = rs.getTimestamp("updated_at");

		return new User(
				rs.getLong("user_id"), 
				rs.getString("username"), 
				rs.getString("password"), 
				mapToUserType(rs),
				createdAt != null ? createdAt.toInstant() : null, 
				updatedAt != null ? updatedAt.toInstant() : null
			);
	}

	private static UserType mapToUserType(ResultSet rs) throws SQLException {
		var createdAt = rs.getTimestamp("user_type_created_at");
		var updatedAt = rs.getTimestamp("user_type_updated_at");

		return new UserType(
				rs.getInt("user_type_id"), 
				rs.getString("user_type_name"),
				rs.getString("user_type_description"), 
				createdAt != null ? createdAt.toInstant() : null,
				updatedAt != null ? updatedAt.toInstant() : null
			);
	}

}
