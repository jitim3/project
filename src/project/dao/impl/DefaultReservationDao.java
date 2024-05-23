package project.dao.impl;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import project.dao.ReservationDao;
import project.dao.entity.CottageReservation;
import project.dao.entity.Customer;
import project.dao.entity.Resort;
import project.dao.entity.RoomReservation;
import project.dto.CreateCottageReservationDto;
import project.dto.CreateRoomReservationDto;
import project.util.DatabaseConnectionFactory;
import project.util.ReservationStatus;

public class DefaultReservationDao implements ReservationDao {
	private static final Logger LOGGER = System.getLogger(DefaultReservationDao.class.getName());
	private static final String SQL_SELECT_ROOM_RESERVATIONS = """
			SELECT id, user_id, room_id, start_date, end_date, status, amount, created_at, updated_at
			FROM room_reservation
			"""; 
	private static final String SQL_SELECT_ROOM_RESERVATION_BY_ID = SQL_SELECT_ROOM_RESERVATIONS + " WHERE id = ?"; 
	private static final String SQL_SELECT_ROOM_RESERVATIONS_BY_CUSTOMER_ID = SQL_SELECT_ROOM_RESERVATIONS + " WHERE user_id = ?"; 
	private static final String SQL_INSERT_ROOM_RESERVATION = """
			INSERT INTO room_reservation (user_id, room_id, start_date, end_date, status, amount, created_at)
			VALUES (?, ?, ?, ?, ?, ?, ?)
			""";
	private static final String SQL_SELECT_COTTAGE_RESERVATIONS = """
			INSERT id, user_id, resort_id, reservation_date, status, amount, created_at, updated_at
			FROM cottage_reservation
			""";
	private static final String SQL_SELECT_COTTAGE_RESERVATION_BY_ID = SQL_SELECT_COTTAGE_RESERVATIONS + " WHERE id = ?";
	private static final String SQL_SELECT_COTTAGE_RESERVATIONS_BY_CUSTOMER_ID = SQL_SELECT_COTTAGE_RESERVATIONS + " WHERE user_id = ?";
	private static final String SQL_INSERT_COTTAGE_RESERVATION = """
			INSERT INTO cottage_reservation (user_id, resort_id, reservation_date, status, amount, created_at)
			VALUES (?, ?, ?, ?, ?, ?, ?)
			""";
	private static final String SQL_UPDATE_ROOM_RESERVATION_STATUS = "UPDATE reservation_room SET status = ? WHERE id = ?";
	private static final String SQL_UPDATE_COTTAGE_RESERVATION_STATUS = "UPDATE reservation_cottage SET status = ? WHERE id = ?";
	private final Connection connection;
	

	public DefaultReservationDao() {
		this.connection = DatabaseConnectionFactory.getConnection();
	}
	
	@Override
	public Optional<RoomReservation> getRoomReservationById(long id) {
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_ROOM_RESERVATION_BY_ID)) {
			int i = 1;
			statement.setLong(i++, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return Optional.of(mapToRoomReservation(rs));
			} else {
				LOGGER.log(Level.INFO, "No room reservation with ID " + id + " found");
			}
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}

		return Optional.empty();
	}

	@Override
	public List<RoomReservation> getRoomReservations() {
		final List<RoomReservation> roomReservations = new ArrayList<>();
		
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_ROOM_RESERVATIONS)) {
			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					roomReservations.add(mapToRoomReservation(rs));
				}
			}
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}
		
		if (roomReservations.isEmpty()) {
			LOGGER.log(Level.INFO, "No room reservations found");
		}

		return roomReservations;
	}

	@Override
	public List<RoomReservation> getRoomReservationsByCustomerId(long customerId) {
		final List<RoomReservation> roomReservations = new ArrayList<>();
		
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_ROOM_RESERVATIONS_BY_CUSTOMER_ID)) {
			int i = 1;
			statement.setLong(i++, customerId);
			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					roomReservations.add(mapToRoomReservation(rs));
				}
			}
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}
		
		if (roomReservations.isEmpty()) {
			LOGGER.log(Level.INFO, "No room reservations found");
		}

		return roomReservations;
	}

	@Override
	public Optional<CottageReservation> getCottageReservationById(long id) {
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_COTTAGE_RESERVATION_BY_ID)) {
			int i = 1;
			statement.setLong(i++, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return Optional.of(mapToCottageReservation(rs));
			} else {
				LOGGER.log(Level.INFO, "No cottage reservation with ID " + id + " found");
			}
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}

		return Optional.empty();
	}

	@Override
	public List<CottageReservation> getCottageReservations() {
		final List<CottageReservation> cottageReservations = new ArrayList<>();
		
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_COTTAGE_RESERVATIONS)) {
			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					cottageReservations.add(mapToCottageReservation(rs));
				}
			}
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}
		
		if (cottageReservations.isEmpty()) {
			LOGGER.log(Level.INFO, "No cottage reservations found");
		}

		return cottageReservations;
	}

	@Override
	public List<CottageReservation> getCottageReservationsByCustomerId(long customerId) {
		final List<CottageReservation> cottageReservations = new ArrayList<>();
		
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_COTTAGE_RESERVATIONS_BY_CUSTOMER_ID)) {
			int i = 1;
			statement.setLong(i++, customerId);
			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					cottageReservations.add(mapToCottageReservation(rs));
				}
			}
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}
		
		if (cottageReservations.isEmpty()) {
			LOGGER.log(Level.INFO, "No cottage reservations found");
		}

		return cottageReservations;
	}

	@Override
	public Long createRoomReservation(CreateRoomReservationDto createRoomReservationDto) {
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_INSERT_ROOM_RESERVATION, Statement.RETURN_GENERATED_KEYS)) {
			int i = 1;
			statement.setLong(i++, createRoomReservationDto.userId());
			statement.setLong(i++, createRoomReservationDto.roomId());
			statement.setDate(i++, Date.valueOf(createRoomReservationDto.startDate()));
			statement.setDate(i++, Date.valueOf(createRoomReservationDto.endDate()));
			statement.setString(i++, createRoomReservationDto.status().value());
			statement.setBigDecimal(i++, createRoomReservationDto.amount());
			statement.setTimestamp(i++, Timestamp.from(createRoomReservationDto.createdAt()));
			if (statement.executeUpdate() == 0) {
				throw new SQLException("Creating resort rservation failed, no rows affected.");
			}

			try (ResultSet rs = statement.getGeneratedKeys()) {
			    if (rs.next()) {
			    	Long id = rs.getLong(1);
			    	LOGGER.log(Level.DEBUG, "Resort reservation ID generated: " + id);
			        return id;
			    } else {
			    	LOGGER.log(Level.INFO, "No resort reservation ID genereted");
			    }
			}
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}	
		
		return null;
	}

	@Override
	public Long createCottageReservation(CreateCottageReservationDto createCottageReservationDto) {
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_INSERT_COTTAGE_RESERVATION, Statement.RETURN_GENERATED_KEYS)) {
			int i = 1;
			statement.setLong(i++, createCottageReservationDto.userId());
			statement.setLong(i++, createCottageReservationDto.resortId());
			statement.setDate(i++, Date.valueOf(createCottageReservationDto.reservationDate()));
			statement.setString(i++, createCottageReservationDto.status().value());
			statement.setBigDecimal(i++, createCottageReservationDto.amount());
			statement.setTimestamp(i++, Timestamp.from(createCottageReservationDto.createdAt()));
			if (statement.executeUpdate() == 0) {
				throw new SQLException("Creating resort rservation failed, no rows affected.");
			}

			try (ResultSet rs = statement.getGeneratedKeys()) {
			    if (rs.next()) {
			    	Long id = rs.getLong(1);
			    	LOGGER.log(Level.DEBUG, "Resort reservation ID generated: " + id);
			        return id;
			    } else {
			    	LOGGER.log(Level.INFO, "No resort reservation ID genereted");
			    }
			}
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}	
		
		return null;
	}

	@Override
	public boolean updateRoomReservationStatus(long reservationRoomId, ReservationStatus status, Instant updatedAt) {
		if (status == null) {
			return false;
		}
		
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_UPDATE_ROOM_RESERVATION_STATUS)) {
			int i = 1;
			statement.setString(i++, status.value());
			statement.setTimestamp(i++, Timestamp.from(updatedAt));
			statement.setLong(i++, reservationRoomId);
			if (statement.executeUpdate() == 0) {
				throw new SQLException("Updatihg room reservation failed, no rows affected.");
			}
			
			return true;
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}

		return false;
	}

	@Override
	public boolean updateCottageReservationStatus(long reservationCottagetId, ReservationStatus status, Instant updatedAt) {
		if (status == null) {
			return false;
		}
		
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_UPDATE_COTTAGE_RESERVATION_STATUS)) {
			int i = 1;
			statement.setString(i++, status.value());
			statement.setTimestamp(i++, Timestamp.from(updatedAt));
			statement.setLong(i++, reservationCottagetId);
			if (statement.executeUpdate() == 0) {
				throw new SQLException("Updatihg cottage reservation failed, no rows affected.");
			}
			
			return true;
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}

		return false;
	}

	private static RoomReservation mapToRoomReservation(ResultSet rs) throws SQLException {
		var startDate = rs.getDate("start_date");
		var endDate = rs.getDate("end_date");		
		var createdAt = rs.getTimestamp("created_at");
		var updatedAt = rs.getTimestamp("updated_at");
		
		return new RoomReservation(
				rs.getLong("id"),  
				rs.getLong("user_id"),
				rs.getLong("room_id"),
				startDate != null ? startDate.toLocalDate() : null,
				endDate != null ? endDate.toLocalDate() : null, 
				rs.getString("status"),
				rs.getBigDecimal("amount"),
				createdAt != null ? createdAt.toInstant() : null,
				updatedAt != null ? updatedAt.toInstant() : null
			);
	}

	private static CottageReservation mapToCottageReservation(ResultSet rs) throws SQLException {
		var reservationDate = rs.getDate("reservation_date");
		var createdAt = rs.getTimestamp("created_at");
		var updatedAt = rs.getTimestamp("updated_at");
		
		return new CottageReservation(
				rs.getLong("id"),  
				rs.getLong("user_id"),
				rs.getLong("resort_id"),
				reservationDate != null ? reservationDate.toLocalDate() : null,
				rs.getString("status"),
				rs.getBigDecimal("amount"),
				createdAt != null ? createdAt.toInstant() : null,
				updatedAt != null ? updatedAt.toInstant() : null
			);
	}

}
