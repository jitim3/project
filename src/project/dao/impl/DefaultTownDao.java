package project.dao.impl;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import project.dao.TownDao;
import project.dao.entity.Town;
import project.util.DatabaseConnectionFactory;

public class DefaultTownDao implements TownDao {
	private static final Logger LOGGER = System.getLogger(DefaultTownDao.class.getName());
	private static final String SQL_SELECT_TOWNS = "SELECT id, name, created_at, updated_at FROM town";
	private static final String SQL_SELECT_TOWN_BY_ID = SQL_SELECT_TOWNS + " WHERE id = ?";
	private final Connection connection;
	
	public DefaultTownDao() {
		this.connection = DatabaseConnectionFactory.getConnection();
	}

	@Override
	public Optional<Town> getTownById(int id) {
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_TOWN_BY_ID)) {
			int i = 1;
			statement.setInt(i++, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return Optional.of(mapToTown(rs));
			} else {
				LOGGER.log(Level.INFO, "No town with ID " + id + " found");
			}
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}

		return Optional.empty();
	}

	@Override
	public List<Town> getTowns() {
		final List<Town> towns = new ArrayList<>();
		
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_TOWNS)) {
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				towns.add(mapToTown(rs));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}
		
		if (towns.isEmpty()) {
			LOGGER.log(Level.INFO, "No towns found found");
		}

		return towns;
	}
	
	private static Town mapToTown(ResultSet rs) throws SQLException {
		var createdAt = rs.getTimestamp("created_at");
		var updatedAt = rs.getTimestamp("updated_at");
		
		return new Town(
				rs.getInt("id"),  
				rs.getString("name"), 
				createdAt != null ? createdAt.toInstant() : null,
				updatedAt != null ? updatedAt.toInstant() : null
			);
	}

}
