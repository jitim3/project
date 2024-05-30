package project.dao;

import project.dao.entity.Reservation;
import project.dto.CreateReservationDto;
import project.util.DatabaseConnectionFactory;
import project.util.ReservationStatus;

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

public class ReservationDao {
    private static final Logger LOGGER = System.getLogger(ReservationDao.class.getName());
    private static final String SQL_SELECT_RESERVATIONS = """
            SELECT
                rsv.id AS reservation_id, rsv.user_id, rst.id AS resort_id, rst.name AS resort_name,
                rst2.id AS room_resort_id, rst2.name AS room_resort_name, rm.id as room_id, rm.room_type,
                rsv.reservation_date, rsv.end_date, rsv.status, rsv.amount, rsv.created_at, rsv.updated_at
            FROM reservation rsv
            LEFT JOIN resort rst on rst.id = rsv.resort_id
            LEFT JOIN room rm on rm.id = rsv.room_id
            LEFT JOIN resort rst2 on rst2.id = rm.resort_id
            """;
    private static final String SQL_SELECT_RESERVATION_BY_ID = SQL_SELECT_RESERVATIONS + " WHERE rsv.id = ?";
    private static final String SQL_SELECT_RESERVATIONS_BY_CUSTOMER_ID = SQL_SELECT_RESERVATIONS + " WHERE rsv.user_id = ?";
    private static final String SQL_SELECT_RESERVATIONS_BY_USER_ID = SQL_SELECT_RESERVATIONS + " WHERE rst.user_id = ? OR rst2.user_id = ?";
    private static final String SQL_SELECT_RESERVATIONS_BY_RESORT_ID = SQL_SELECT_RESERVATIONS + " WHERE rsv.resort_id = ? OR rst2.id = ?";
    private static final String SQL_INSERT_COTTAGE_RESERVATION = """
            INSERT INTO reservation (user_id, resort_id, reservation_date, status, amount, created_at)
            VALUES (?, ?, ?, ?, ?, ?)
            """;
    private static final String SQL_INSERT_ROOM_RESERVATION = """
            INSERT INTO reservation (user_id, room_id, reservation_date, end_date, status, amount, created_at)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;
    private static final String SQL_UPDATE_RESERVATION_STATUS = "UPDATE reservation SET status = ? WHERE id = ?";
    private final Connection connection;


    public ReservationDao() {
        this.connection = DatabaseConnectionFactory.getConnection();
    }

    public Optional<Reservation> getReservationById(long id) {
        try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_RESERVATION_BY_ID)) {
            int i = 1;
            statement.setLong(i++, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.of(mapToReservation(rs));
            } else {
                LOGGER.log(Level.INFO, "No reservation with ID " + id + " found");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
        }

        return Optional.empty();
    }

    public List<Reservation> getReservations() {
        final List<Reservation> reservations = new ArrayList<>();

        try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_RESERVATIONS)) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    reservations.add(mapToReservation(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
        }

        if (reservations.isEmpty()) {
            LOGGER.log(Level.INFO, "No room reservations found");
        }

        return reservations;
    }

    public List<Reservation> getReservationsByCustomerId(long customerId) {
        final List<Reservation> reservations = new ArrayList<>();

        try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_RESERVATIONS_BY_CUSTOMER_ID)) {
            int i = 1;
            statement.setLong(i++, customerId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    reservations.add(mapToReservation(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
        }

        if (reservations.isEmpty()) {
            LOGGER.log(Level.INFO, "No room reservations found");
        }

        return reservations;
    }

    public List<Reservation> getReservationsByUserId(long userId) {
        final List<Reservation> reservations = new ArrayList<>();

        try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_RESERVATIONS_BY_USER_ID)) {
            int i = 1;
            statement.setLong(i++, userId);
            statement.setLong(i++, userId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    reservations.add(mapToReservation(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
        }

        if (reservations.isEmpty()) {
            LOGGER.log(Level.INFO, "No room reservations found");
        }

        return reservations;
    }

    public List<Reservation> getReservationsByResortId(long resortId) {
        final List<Reservation> reservations = new ArrayList<>();

        try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_RESERVATIONS_BY_RESORT_ID)) {
            int i = 1;
            statement.setLong(i++, resortId);
            statement.setLong(i++, resortId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    reservations.add(mapToReservation(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
        }

        if (reservations.isEmpty()) {
            LOGGER.log(Level.INFO, "No room reservations found");
        }

        return reservations;
    }

    public Long createReservation(CreateReservationDto createReservationDto) {
        Long resortId = createReservationDto.resortId();
        Long roomId = createReservationDto.roomId();
        String query;
        if (resortId != null) {
            query = SQL_INSERT_COTTAGE_RESERVATION;
        } else if (roomId != null) {
            query = SQL_INSERT_ROOM_RESERVATION;
        } else {
            throw new IllegalArgumentException("Invalid reservation; no resort ID or roomId");
        }

        try (PreparedStatement statement = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            int i = 1;
            statement.setLong(i++, createReservationDto.userId());
            if (resortId != null) {
                statement.setLong(i++, createReservationDto.resortId());
                statement.setDate(i++, Date.valueOf(createReservationDto.reservationDate()));
            }
            if (roomId != null) {
                statement.setLong(i++, createReservationDto.roomId());
                statement.setDate(i++, Date.valueOf(createReservationDto.reservationDate()));
                statement.setDate(i++, Date.valueOf(createReservationDto.endDate()));
            }
            statement.setString(i++, createReservationDto.status().value());
            statement.setBigDecimal(i++, createReservationDto.amount());
            statement.setTimestamp(i++, Timestamp.from(createReservationDto.createdAt()));
            if (statement.executeUpdate() == 0) {
                throw new SQLException("Creating reservation failed, no rows affected.");
            }

            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    LOGGER.log(Level.DEBUG, "Resort reservation ID generated: " + id);
                    return id;
                } else {
                    LOGGER.log(Level.INFO, "No resort reservation ID generated");
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
        }

        return null;
    }

    public boolean updateReservationStatus(long reservationId, ReservationStatus status, Instant updatedAt) {
        if (status == null) {
            return false;
        }

        try (PreparedStatement statement = this.connection.prepareStatement(SQL_UPDATE_RESERVATION_STATUS)) {
            int i = 1;
            statement.setString(i++, status.value());
            statement.setTimestamp(i++, Timestamp.from(updatedAt));
            statement.setLong(i++, reservationId);
            if (statement.executeUpdate() == 0) {
                throw new SQLException("Updating reservation failed, no rows affected.");
            }

            return true;
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
        }

        return false;
    }

    private static Reservation mapToReservation(ResultSet rs) throws SQLException {
        var reservationDate = rs.getDate("reservation_date");
        var endDate = rs.getDate("end_date");
        var createdAt = rs.getTimestamp("created_at");
        var updatedAt = rs.getTimestamp("updated_at");

        return new Reservation(
                rs.getLong("reservation_id"),
                rs.getLong("user_id"),
                rs.getLong("resort_id"),
                rs.getString("resort_name"),
                rs.getLong("room_resort_id"),
                rs.getString("room_resort_name"),
                rs.getLong("room_id"),
                rs.getString("room_type"),
                reservationDate != null ? reservationDate.toLocalDate() : null,
                endDate != null ? endDate.toLocalDate() : null,
                rs.getString("status"),
                rs.getBigDecimal("amount"),
                createdAt != null ? createdAt.toInstant() : null,
                updatedAt != null ? updatedAt.toInstant() : null
        );
    }
}
