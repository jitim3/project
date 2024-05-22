package project.dao.impl;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import project.dao.PaymentDao;
import project.dto.CreatePaymentDto;
import project.util.DatabaseConnectionFactory;

public class DefaultPaymentDao implements PaymentDao {
	private static final Logger LOGGER = System.getLogger(DefaultPaymentDao.class.getName());
	private static final String SQL_INSERT_PAYMENT = """
			INSERT INTO payment (
				reservation_resort_id, reservation_room_id, reservation_cottage_id,
				reservation_resort_amount, reservation_room_amount, reservation_cottage_amount,
				created_at
			)
			VALUES (?, ?, ?, ?, ?, ?, ?) 
			""";
	private final Connection connection;

	public DefaultPaymentDao() {
		this.connection = DatabaseConnectionFactory.getConnection();
	}

	@Override
	public Long createPayment(CreatePaymentDto createPaymentDto) {
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_INSERT_PAYMENT, Statement.RETURN_GENERATED_KEYS)) {
			int i = 1;
			statement.setLong(i++, createPaymentDto.reservationResortId());
			statement.setLong(i++, createPaymentDto.reservationRoomId());
			statement.setLong(i++, createPaymentDto.reservationCottageId());
			statement.setBigDecimal(i++, createPaymentDto.reservationResortAmount());
			statement.setBigDecimal(i++, createPaymentDto.reservationRoomAmount());
			statement.setBigDecimal(i++, createPaymentDto.reservationCottageAmount());
			statement.setTimestamp(i++, Timestamp.from(createPaymentDto.createdAt()));
			if (statement.executeUpdate() == 0) {
				throw new SQLException("Creating payment reservation failed, no rows affected.");
			}

			try (ResultSet rs = statement.getGeneratedKeys()) {
			    if (rs.next()) {
			    	Long id = rs.getLong(1);
			    	LOGGER.log(Level.DEBUG, "Reservation payment ID generated: " + id);
			        return id;
			    } else {
			    	LOGGER.log(Level.INFO, "No reservation payment ID genereted");
			    }
			}
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}	
		
		return null;
	}

}
