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

import project.dao.RoomDao;
import project.dao.entity.Room;
import project.dao.entity.RoomAvailabilityType;
import project.dto.CreateRoomDto;
import project.dto.UpdateRoomDto;
import project.util.DatabaseConnectionFactory;

public class DefaultRoomDao implements RoomDao {
	private static final Logger LOGGER = System.getLogger(DefaultRoomDao.class.getName());
	private static final String SQL_SELECT_ROOM_BY_ID = """
			SELECT
			    r.id, r.resort_id, r.room_type, r.num_of_pax, r.rate_per_night, r.description, r.room_image1, r.room_image2, r.created_at, r.updated_at,
			    rat.id AS room_availability_type_id, rat.name AS room_availability_type_name,
			    rat.created_at AS room_availability_type_created_at, rat.updated_at AS room_availability_type_updated_at
			FROM room r
			LEFT JOIN room_availability_type rat ON r.room_availability_type_id = rat.id
			WHERE r.id = ?
			""";
	private static final String SQL_SELECT_ROOM_BY_RESORT_ID = """
			SELECT
			    r.id, r.resort_id, r.room_type, r.num_of_pax, r.rate_per_night, r.description, r.room_image1, r.room_image2, r.created_at, r.updated_at,
			    rat.id AS room_availability_type_id, rat.name AS room_availability_type_name,
			    rat.created_at AS room_availability_type_created_at, rat.updated_at AS room_availability_type_updated_at
			FROM room r
			LEFT JOIN room_availability_type rat ON r.room_availability_type_id = rat.id
			WHERE r.resort_id = ?
			""";
	private static final String SQL_INSERT_ROOM = """
			INSERT INTO room (
			    resort_id, room_availability_type_id, room_type, num_of_pax,
			    rate_per_night, description, room_image1, room_image2, created_at
			)
			VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
			""";
	private static final String SQL_UPDATE_ROOM = """
			UPDATE room SET room_availability_type_id = ?, num_of_pax = ?, rate_per_night = ?,
			description = ?, room_image1 = ?, room_image2 = ?, updated_at = ?
			WHERE id = ?
			""";
	private final Connection connection;
	
	public DefaultRoomDao() {
		this.connection = DatabaseConnectionFactory.getConnection();
	}

	@Override
	public Optional<Room> getRoomById(long id) {
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_ROOM_BY_ID)) {
			int i = 1;
			statement.setLong(i++, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return Optional.of(mapToRoom(rs));
			} else {
				LOGGER.log(Level.INFO, "No room with ID " + id + " found");
			}
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}

		return Optional.empty();
	}

	@Override
	public List<Room> getRoomsByResortId(long resortId) {
		final List<Room> rooms = new ArrayList<>();
		
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_ROOM_BY_RESORT_ID)) {
			int i = 1;
			statement.setLong(i++, resortId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				rooms.add(mapToRoom(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (rooms.isEmpty()) {
			LOGGER.log(Level.INFO, "No rooms found for resort with ID " + resortId + " found");
		}

		return rooms;
	}

	@Override
	public Room createRoom(CreateRoomDto createRoomDto) {
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_INSERT_ROOM, Statement.RETURN_GENERATED_KEYS)) {
			int i = 1;
			statement.setLong(i++, createRoomDto.resortId());
			statement.setInt(i++, createRoomDto.roomAvailabilityTypeId());
			statement.setString(i++, createRoomDto.roomType());
			statement.setInt(i++, createRoomDto.numberOfPax());
			statement.setBigDecimal(i++, createRoomDto.ratePerNight());
			statement.setString(i++, createRoomDto.description());
			statement.setString(i++, createRoomDto.roomImage1());
			statement.setString(i++, createRoomDto.roomImage2());
			statement.setTimestamp(i++, Timestamp.from(createRoomDto.createdAt()));
			if (statement.executeUpdate() == 0) {
				throw new SQLException("Creating room failed, no rows affected.");
			}
			
			try (ResultSet rs = statement.getGeneratedKeys()) {
			    if (rs.next()) {
			    	long id = rs.getLong(1);
			    	LOGGER.log(Level.DEBUG, "Room ID generated: " + id);
			        return this.getRoomById(id).orElse(null);
			    } else {
			    	LOGGER.log(Level.ERROR, "No room ID genereted");
			    }
			}
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}

		return null;
	}
	
	@Override
	public Room updateRoom(UpdateRoomDto updateRoomDto) {
		long roomId = updateRoomDto.id();
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_UPDATE_ROOM)) {
			int i = 1;
			statement.setInt(i++, updateRoomDto.roomAvailabilityTypeId());
			statement.setInt(i++, updateRoomDto.numberOfPax());
			statement.setBigDecimal(i++, updateRoomDto.ratePerNight());
			statement.setString(i++, updateRoomDto.description());
			statement.setString(i++, updateRoomDto.roomImage1());
			statement.setString(i++, updateRoomDto.roomImage2());
			statement.setTimestamp(i++, Timestamp.from(updateRoomDto.updatedAt()));
			statement.setLong(i++, roomId);
			if (statement.executeUpdate() == 0) {
				throw new SQLException("Updating room failed, no rows affected.");
			}
			
			return this.getRoomById(roomId).orElse(null);
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}

		return null;
	}

	private static Room mapToRoom(ResultSet rs) throws SQLException {
		var createdAt = rs.getTimestamp("created_at");
		var updatedAt = rs.getTimestamp("updated_at");
		
		return new Room(
				rs.getLong("id"), 
				mapToRoomAvailabilityType(rs), 
				rs.getString("room_type"), 
				rs.getInt("num_of_pax"), 
				rs.getBigDecimal("rate_per_night"), 
				rs.getString("description"), 
				rs.getString("room_image1"), 
				rs.getString("room_image2"), 
				createdAt != null ? createdAt.toInstant() : null,
				updatedAt != null ? updatedAt.toInstant() : null
			);
	}
	
	private static RoomAvailabilityType mapToRoomAvailabilityType(ResultSet rs) throws SQLException {
		var createdAt = rs.getTimestamp("room_availability_type_created_at");
		var updatedAt = rs.getTimestamp("room_availability_type_updated_at");
		
		return new RoomAvailabilityType(
				rs.getInt("room_availability_type_id"),
				rs.getString("room_availability_type_name"),
				createdAt != null ? createdAt.toInstant() : null,
				updatedAt != null ? updatedAt.toInstant() : null
			);
	}

}
