package project.dao;

import project.util.DatabaseConnectionFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

public class WalletDao {
    private static final System.Logger LOGGER = System.getLogger(WalletDao.class.getName());
    private static final String SQL_SELECT_SUPER_ADMIN_TOTAL_SALES = "SELECT total FROM super_admin_total_sales;";
    private static final String SQL_SELECT_ADMIN_TOTAL_SALES = "SELECT admin_id, total FROM admin_total_sales WHERE admin_id = ?";
    private static final String SQL_SELECT_TOTAL_WITHDRAWAL_BY_USER_ID = "SELECT user_id, SUM(amount) AS total FROM withdrawal WHERE user_id = ? GROUP BY user_id ORDER BY total";
    private static final String SQL_INSERT_WITHDRAWAL = "INSERT INTO withdrawal (user_id, amount, created_at) VALUES (?, ?, ?)";
    private final Connection connection;

    public WalletDao() {
        this.connection = DatabaseConnectionFactory.getConnection();
    }

    public Optional<BigDecimal> getTotalSalesBySuperAdmin() {
        try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_SUPER_ADMIN_TOTAL_SALES)) {
            int i = 1;
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.of(rs.getBigDecimal("total"));
            } else {
                LOGGER.log(System.Logger.Level.INFO, "No total sales for super admin found");
            }
        } catch (SQLException e) {
            LOGGER.log(System.Logger.Level.ERROR, e);
        }

        return Optional.empty();
    }

    public Optional<BigDecimal> getTotalSalesByAdmin(long userId) {
        try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_ADMIN_TOTAL_SALES)) {
            int i = 1;
            statement.setLong(i++, userId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.of(rs.getBigDecimal("total"));
            } else {
                LOGGER.log(System.Logger.Level.INFO, "No total sales for admin " + userId + " found");
            }
        } catch (SQLException e) {
            LOGGER.log(System.Logger.Level.ERROR, e);
        }

        return Optional.empty();
    }

    public Optional<BigDecimal> getTotalWithdrawalByUserId(long userId) {
        try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_TOTAL_WITHDRAWAL_BY_USER_ID)) {
            int i = 1;
            statement.setLong(i++, userId);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.of(rs.getBigDecimal("total"));
            } else {
                LOGGER.log(System.Logger.Level.INFO, "No total withdrawal for user " + userId + " found");
            }
        } catch (SQLException e) {
            LOGGER.log(System.Logger.Level.ERROR, e);
        }

        return Optional.empty();
    }

    public boolean withdraw(long userId, BigDecimal amount, Instant createdAt) {
        try (PreparedStatement statement = this.connection.prepareStatement(SQL_INSERT_WITHDRAWAL)) {
            int i = 1;
            statement.setLong(i++, userId);
            statement.setBigDecimal(i++, amount);
            statement.setTimestamp(i++, Timestamp.from(createdAt));
            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            LOGGER.log(System.Logger.Level.ERROR, e);
        }

        return false;
    }
}
