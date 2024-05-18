package project.dao.impl;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import project.dao.ResortDao;
import project.dao.RoomDao;
import project.dao.entity.Resort;
import project.dao.entity.Room;
import project.dto.CreateResortDto;
import project.dto.UpdateResortDto;
import project.util.DatabaseConnectionFactory;

public class DefaultResortDao implements ResortDao {
	private static final Logger LOGGER = System.getLogger(DefaultResortDao.class.getName());
	private static final String SQL_SELECT_RESORT_BY_NAME = "SELECT name FROM resort WHERE name = ?";
	private static final String SQL_SELECT_RESORT_BY_ID = """
			SELECT id, name, description, location, how_to_get_there, resort_fee, cottage_fee, pool_fee,
			resort_image, pool_image, cottage_image, town_id, user_id, created_at, updated_at 
			FROM resort
			WHERE id = ?
			""";
	private static final String SQL_SELECT_RESORT_BY_USER_ID_AND_TOWN_ID = """
			SELECT id, name, description, location, how_to_get_there, resort_fee, cottage_fee, pool_fee,
			resort_image, pool_image, cottage_image, town_id, user_id, created_at, updated_at 
			FROM resort
			WHERE user_id = ? AND town_id = ?
			""";
	private static final String SQL_INSERT_RESORT = """
			INSERT INTO resort (name, user_id, town_id, created_at)
			VALUES (?, ?, ?, ?)
			""";
	private static final String SQL_UPDATE_RESORT = """
			UPDATE resort SET description = ?, location = ?, how_to_get_there = ?, resort_fee = ?, 
			cottage_fee = ?, pool_fee = ?, resort_image = ?, pool_image = ?, cottage_image = ?, updated_at = ?
			WHERE id = ?
			""";
	private final Connection connection;
	private final RoomDao roomDao;
	
	public DefaultResortDao() {
		this.connection = DatabaseConnectionFactory.getConnection();
		this.roomDao = new DefaultRoomDao();
	}

	@Override
	public boolean isResortExists(String name) {
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_RESORT_BY_NAME)) {
			statement.setString(1, name);
			try (ResultSet rs = statement.executeQuery()) {
				boolean resortExists = rs.next();
				if (!resortExists) {
					LOGGER.log(Level.INFO, "No resort name " + name + " exists");
				}
				
				return resortExists;
			}
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}

		return true;
	}

	@Override
	public Optional<Resort> getResortById(int id) {
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_RESORT_BY_ID)) {
			int i = 1;
			statement.setInt(i++, id);
			try (ResultSet rs = statement.executeQuery()) {
				if (rs.next()) {
					long resortId = rs.getLong("id");
					List<Room> rooms = this.roomDao.getRoomByResortId(resortId);
					return Optional.of(mapToResort(rs, rooms));
				} else {
					LOGGER.log(Level.INFO, "No resort with ID " + id + " found");
				}
			}
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}

		return Optional.empty();
	}

	
	@Override
	public List<Resort> getResortsByUserIdAndTownId(long userId, int townId) {
		final List<Resort> resorts = new ArrayList<Resort>();
		
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_RESORT_BY_USER_ID_AND_TOWN_ID)) {
			int i = 1;
			statement.setLong(i++, userId);
			statement.setInt(i++, townId);
			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					long resortId = rs.getLong("id");
					List<Room> rooms = this.roomDao.getRoomByResortId(resortId);
					resorts.add(mapToResort(rs, rooms));
				}
			}
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}
		
		if (resorts.isEmpty()) {
			LOGGER.log(Level.INFO, "No resorts found for user with ID " + userId + " and town with ID " + townId + " found");
		}

		return resorts;
	}

	@Override
	public Long createResort(CreateResortDto createUserDto) {
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_INSERT_RESORT, Statement.RETURN_GENERATED_KEYS)) {
			int i = 1;
			statement.setString(i++, createUserDto.name());
			statement.setLong(i++, createUserDto.userId());
			statement.setInt(i++, createUserDto.townId());
			statement.setTimestamp(i++, Timestamp.from(createUserDto.createdAt()));
			if (statement.executeUpdate() == 0) {
				throw new SQLException("Creating resort failed, no rows affected.");
			}

			try (ResultSet rs = statement.getGeneratedKeys()) {
			    if (rs.next()) {
			    	Long id = rs.getLong(1);
			    	LOGGER.log(Level.DEBUG, "Resort ID generated: " + id);
			        return id;
			    } else {
			    	LOGGER.log(Level.INFO, "No resort ID genereted");
			    }
			}
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}

		return null;
	}

	@Override
	public boolean updateResort(UpdateResortDto updateResortDto) {
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_UPDATE_RESORT)) {
			int i = 1;
			statement.setString(i++, updateResortDto.description());
			statement.setString(i++, updateResortDto.location());
			statement.setString(i++, updateResortDto.howToGetThere());
			statement.setBigDecimal(i++, updateResortDto.resortFee());
			statement.setBigDecimal(i++, updateResortDto.cottageFee());
			statement.setBigDecimal(i++, updateResortDto.poolFee());
			statement.setString(i++, updateResortDto.resortImage());
			statement.setString(i++, updateResortDto.poolImage());
			statement.setString(i++, updateResortDto.cottageImage());
			statement.setTimestamp(i++, Timestamp.from(updateResortDto.updatedAt()));
			statement.setLong(i++, updateResortDto.id());
			
			return statement.executeUpdate() == 0;
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}
		
		return false;
	}

	private static Resort mapToResort(ResultSet rs, List<Room> rooms) throws SQLException {
		var createdAt = rs.getTimestamp("created_at");
		var updatedAt = rs.getTimestamp("updated_at");
		
		return new Resort(
				rs.getLong("id"), 
				rs.getString("name"), 
				rs.getString("description"), 
				rs.getString("location"), 
				rs.getString("how_to_get_there"),
				rs.getBigDecimal("resort_fee"),
				rs.getBigDecimal("cottage_fee"),
				rs.getBigDecimal("pool_fee"),
				rs.getString("resort_image"), 
				rs.getString("pool_image"), 
				rs.getString("cottage_image"), 
				rooms,
				rs.getLong("use_id"), 
				rs.getInt("town_id"),
				createdAt != null ? createdAt.toInstant() : null,
				updatedAt != null ? updatedAt.toInstant() : null
			);
	}

}
