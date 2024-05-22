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

import project.dao.ReservationDao;
import project.dto.CreateCottageReservationDto;
import project.dto.CreateResortReservationDto;
import project.dto.CreateRoomReservationDto;
import project.util.DatabaseConnectionFactory;
import project.util.ReservationStatus;

public class DefaultReservationDao implements ReservationDao {
	private static final Logger LOGGER = System.getLogger(DefaultReservationDao.class.getName());
	private static final String SQL_INSERT_RESERVATION_RESORT = """
			INSERT INTO reservation_resort (user_id, resort_id, start_date, end_date, status, amount, created_at)
			VALUES (?, ?, ?, ?, ?, ?, ?)
			""";
	private static final String SQL_INSERT_RESERVATION_ROOM = """
			INSERT INTO reservation_resort (user_id, room_id, start_date, end_date, status, amount, created_at)
			VALUES (?, ?, ?, ?, ?, ?, ?)
			""";
	private static final String SQL_INSERT_RESERVATION_COTTAGE = """
			INSERT INTO reservation_resort (user_id, resort_id, start_date, end_date, status, amount, created_at)
			VALUES (?, ?, ?, ?, ?, ?, ?)
			""";
	private static final String SQL_UPDATE_RESERVATION_RESORT_STATUS = "UPDATE reservation_resort SET status = ? WHERE id = ?";
	private static final String SQL_UPDATE_RESERVATION_ROOM_STATUS = "UPDATE reservation_room SET status = ? WHERE id = ?";
	private static final String SQL_UPDATE_RESERVATION_COTTAGE_STATUS = "UPDATE reservation_cottage SET status = ? WHERE id = ?";
	private final Connection connection;
	

	public DefaultReservationDao() {
		this.connection = DatabaseConnectionFactory.getConnection();
	}

	@Override
	public Long createResortReservation(CreateResortReservationDto createResortReservationDto) {
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_INSERT_RESERVATION_RESORT, Statement.RETURN_GENERATED_KEYS)) {
			int i = 1;
			statement.setLong(i++, createResortReservationDto.userId());
			statement.setLong(i++, createResortReservationDto.resortId());
			statement.setDate(i++, Date.valueOf(createResortReservationDto.startDate()));
			statement.setDate(i++, Date.valueOf(createResortReservationDto.endDate()));
			statement.setString(i++, createResortReservationDto.status());
			statement.setBigDecimal(i++, createResortReservationDto.amount());
			statement.setTimestamp(i++, Timestamp.from(createResortReservationDto.createdAt()));
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
	public Long createRoomReservation(CreateRoomReservationDto createRoomReservationDto) {
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_INSERT_RESERVATION_ROOM, Statement.RETURN_GENERATED_KEYS)) {
			int i = 1;
			statement.setLong(i++, createRoomReservationDto.userId());
			statement.setLong(i++, createRoomReservationDto.roomId());
			statement.setDate(i++, Date.valueOf(createRoomReservationDto.startDate()));
			statement.setDate(i++, Date.valueOf(createRoomReservationDto.endDate()));
			statement.setString(i++, createRoomReservationDto.status());
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
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_INSERT_RESERVATION_COTTAGE, Statement.RETURN_GENERATED_KEYS)) {
			int i = 1;
			statement.setLong(i++, createCottageReservationDto.userId());
			statement.setLong(i++, createCottageReservationDto.resortId());
			statement.setDate(i++, Date.valueOf(createCottageReservationDto.startDate()));
			statement.setDate(i++, Date.valueOf(createCottageReservationDto.endDate()));
			statement.setString(i++, createCottageReservationDto.status());
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
	public boolean updateResortReservationStatus(long reservationResortId, ReservationStatus status, Instant updatedAt) {
		if (status == null) {
			return false;
		}
		
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_UPDATE_RESERVATION_RESORT_STATUS)) {
			int i = 1;
			statement.setString(i++, status.value());
			statement.setTimestamp(i++, Timestamp.from(updatedAt));
			statement.setLong(i++, reservationResortId);
			if (statement.executeUpdate() == 0) {
				throw new SQLException("Updatihg resort reservation failed, no rows affected.");
			}
			
			return true;
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}

		return false;
	}

	@Override
	public boolean updateRoomReservationStatus(long reservationRoomId, ReservationStatus status, Instant updatedAt) {
		if (status == null) {
			return false;
		}
		
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_UPDATE_RESERVATION_ROOM_STATUS)) {
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
		
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_UPDATE_RESERVATION_COTTAGE_STATUS)) {
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

}
