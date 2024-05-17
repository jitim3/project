package project.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import project.dao.ResortDao;
import project.dao.entity.Resort;
import project.util.DatabaseConnectionFactory;

public class DefaultResortDao implements ResortDao {
	private static final String SQL_SELECT_RESORT_EXISTS = "SELECT name FROM resort WHERE name = ?";
	private static final String SQL_SELECT_RESORT_BY_ID = """
			SELECT id, name, town_id, user_id, created_at, updated_at 
			FROM user
			WHERE username = ? AND password = ? AND user_type_id = ?
			""";
	private static final String SQL_INSERT_RESORT = """
			INSERT INTO resort (username, password, user_type_id, created_at)
			VALUES (?, ?, ?, ?)
			""";
	private final Connection connection;
	
	public DefaultResortDao() {
		this.connection = DatabaseConnectionFactory.getConnection();
	}

	@Override
	public boolean isResortExists(String name) {
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_RESORT_EXISTS)) {
			statement.setString(1, name);
			ResultSet rs = statement.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public Optional<Resort> getResortById(int id) {
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_RESORT_BY_ID)) {
			int i = 1;
			statement.setInt(i++, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return Optional.of(mapToResort(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Optional.empty();
	}

	@Override
	public Optional<Resort> getResortByName(String name) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Resort> getResortByUserId(long userId) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public boolean createResort(Resort resort) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private static Resort mapToResort(ResultSet rs) {
		return null;
	}

}
