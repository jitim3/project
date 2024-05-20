package project.dao.impl;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import project.dao.CustomerDao;
import project.dao.entity.Customer;
import project.dto.CreateCustomerDto;
import project.dto.UpdateCustomerDto;
import project.util.DatabaseConnectionFactory;

public class DefaultCustomerDao implements CustomerDao {
	private static final Logger LOGGER = System.getLogger(DefaultCustomerDao.class.getName());
	private static final String SQL_SELECT_CUSTOMER_EXISTS = "SELECT id FROM customer WHERE id = ?";
	private static final String SQL_SELECT_CUSTOMERS = """
			SELECT 
				u.id, u.username, c.first_name, c.last_name, c.contact_number, c.email_address, u.created_at, c.updated_at FROM user u
			LEFT JOIN customer c ON c.id = u.id
			WHERE u.user_type_id = 3
			""";
	private static final String SQL_SELECT_CUSTOMER_BY_ID = SQL_SELECT_CUSTOMERS + " AND u.id = ?";
	private static final String SQL_SELECT_CUSTOMER_BY_USERNAME = SQL_SELECT_CUSTOMERS + " AND u.username = ?";
	private static final String SQL_INSERT_CUSTOMER = "INSERT INTO customer(id, first_name, last_name, contact_number, email_address, created_at) VALUES(?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_CUSTOMER = "UPDATE customer SET first_name = ?, last_name = ?, contact_number = ?, email_address = ?, updated_at = ?	WHERE id = ?";
	private final Connection connection;
	
	public DefaultCustomerDao() {
		this.connection = DatabaseConnectionFactory.getConnection();
	}
	
	@Override
	public boolean isCustomerExists(long id) {
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_CUSTOMER_EXISTS)) {
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}

		return true;
	}

	@Override
	public Optional<Customer> getCustomerById(Long id) {
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_CUSTOMER_BY_ID)) {
			int i = 1;
			statement.setLong(i++, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return Optional.of(mapToCustomer(rs));
			} else {
				LOGGER.log(Level.INFO, "No customer with ID " + id + " found");
			}
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}

		return Optional.empty();
	}

	@Override
	public Optional<Customer> getCustomerByUsername(String username) {
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_CUSTOMER_BY_USERNAME)) {
			int i = 1;
			statement.setString(i++, username);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return Optional.of(mapToCustomer(rs));
			} else {
				LOGGER.log(Level.INFO, "No customer with username " + username + " found");
			}
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}

		return Optional.empty();
	}

	@Override
	public List<Customer> getCustomers() {
		final List<Customer> customers = new ArrayList<>();
		
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_CUSTOMERS)) {
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				customers.add(mapToCustomer(rs));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}
		
		if (customers.isEmpty()) {
			LOGGER.log(Level.INFO, "No customers found found");
		}

		return customers;
	}

	@Override
	public Customer createCustomer(CreateCustomerDto createCustomerDto) {
		long id = createCustomerDto.id();
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_INSERT_CUSTOMER)) {
			int i = 1;
			statement.setLong(i++, id);
			statement.setString(i++, createCustomerDto.firstName());
			statement.setString(i++, createCustomerDto.lastName());
			statement.setString(i++, createCustomerDto.contactNumber());
			statement.setString(i++, createCustomerDto.emailAddress());
			statement.setTimestamp(i++, Timestamp.from(createCustomerDto.createdAt()));
			if (statement.executeUpdate() == 0) {
				throw new SQLException("Creating customer failed, no rows affected.");
			}

			return this.getCustomerById(id).orElse(null);
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}

		return null;
	}

	@Override
	public Customer updateCustomer(UpdateCustomerDto updateCustomerDto) {
		long customerId = updateCustomerDto.id();
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_UPDATE_CUSTOMER)) {
			int i = 1;
			statement.setString(i++, updateCustomerDto.firstName());
			statement.setString(i++, updateCustomerDto.lastName());
			statement.setString(i++, updateCustomerDto.contactNumber());
			statement.setString(i++, updateCustomerDto.emailAddress());
			statement.setTimestamp(i++, Timestamp.from(updateCustomerDto.updatedAt()));
			statement.setLong(i++, customerId);
			if (statement.executeUpdate() == 0) {
				throw new SQLException("Updatihg customer failed, no rows affected.");
			}
			
			return this.getCustomerById(customerId).orElse(null);
		} catch (SQLException e) {
			LOGGER.log(Level.ERROR, e);
		}

		return null;
	}

	private static Customer mapToCustomer(ResultSet rs) throws SQLException {
		var createdAt = rs.getTimestamp("created_at");
		var updatedAt = rs.getTimestamp("updated_at");
		
		return new Customer(
				rs.getLong("id"),  
				rs.getString("username"),
				rs.getString("first_name"),
				rs.getString("last_name"),
				rs.getString("contact_number"),
				rs.getString("email_address"),
				createdAt != null ? createdAt.toInstant() : null,
				updatedAt != null ? updatedAt.toInstant() : null
			);
	}
}
