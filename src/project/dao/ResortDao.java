package project.dao;

import project.dao.entity.Resort;
import project.dto.CreateResortDto;
import project.dto.UpdateResortDto;
import project.util.DatabaseConnectionFactory;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ResortDao {
    private static final Logger LOGGER = System.getLogger(ResortDao.class.getName());
    private static final String SQL_SELECT_RESORTS = """
            SELECT r.id AS resort_id, r.name AS resort_name, r.description, r.location, r.how_to_get_there, r.resort_fee, r.cottage_fee, r.pool_fee,
            r.resort_image, r.pool_image, r.cottage_image, r.user_id, r.permit_image, r.created_at AS resort_created_at, r.updated_at AS resort_updated_at
            FROM resort r
            """;
    private static final String SQL_SELECT_RESORT_BY_NAME = "SELECT name FROM resort WHERE name = ?";
    private static final String SQL_SELECT_RESORT_BY_ID = SQL_SELECT_RESORTS + " WHERE r.id = ?";
    private static final String SQL_SELECT_RESORT_BY_USER_ID = SQL_SELECT_RESORTS + " WHERE r.user_id = ?";
    private static final String SQL_SELECT_RESORT_BY_USER_ID_AND_TOWN_ID = SQL_SELECT_RESORTS + " INNER JOIN town_resort tr ON tr.resort_id = r.id WHERE r.user_id = ? AND tr.town_id = ?";
    private static final String SQL_SELECT_RESORTS_BY_TOWN_ID = SQL_SELECT_RESORTS + " INNER JOIN town_resort tr ON tr.resort_id = r.id WHERE tr.town_id = ?";
    private static final String SQL_INSERT_RESORT = """
            INSERT INTO resort (name, user_id, created_at)
            VALUES (?, ?, ?)
            """;
    private static final String SQL_INSERT_TWON_RESORT = """
            INSERT INTO town_resort (town_id, resort_id, created_at)
            VALUES (?, ?, ?)
            """;
    private static final String SQL_UPDATE_RESORT = """
            UPDATE resort SET description = ?, location = ?, how_to_get_there = ?, resort_fee = ?,
            cottage_fee = ?, pool_fee = ?, resort_image = ?, pool_image = ?, cottage_image = ?, updated_at = ?
            WHERE id = ?
            """;
    private static final String SQL_UPDATE_RESORT_PERMIT_IMAGE = "UPDATE resort SET permit_image = ?, updated_at = ? WHERE id = ?";
    private final Connection connection;

    public ResortDao() {
        this.connection = DatabaseConnectionFactory.getConnection();
    }

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

    public Optional<Resort> getResortById(long id) {
        try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_RESORT_BY_ID)) {
            int i = 1;
            statement.setLong(i++, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapToResort(rs));
                } else {
                    LOGGER.log(Level.INFO, "No resort with ID " + id + " found");
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
        }

        return Optional.empty();
    }

    public Optional<Resort> getResortByUserId(long id) {
        try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_RESORT_BY_USER_ID)) {
            int i = 1;
            statement.setLong(i++, id);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapToResort(rs));
                } else {
                    LOGGER.log(Level.INFO, "No resort with user ID " + id + " found");
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
        }

        return Optional.empty();
    }

    public Optional<Resort> getResortByUserIdAndTownId(long userId, int townId) {
        try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_RESORT_BY_USER_ID_AND_TOWN_ID)) {
            int i = 1;
            statement.setLong(i++, userId);
            statement.setInt(i++, townId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapToResort(rs));
                } else {
                    LOGGER.log(Level.INFO, "No resort for user with ID " + userId + " and town with ID " + townId + " found");
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
        }

        return Optional.empty();
    }

    public List<Resort> getResortsByTownId(int townId) {
        final List<Resort> resorts = new ArrayList<>();

        try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_RESORTS_BY_TOWN_ID)) {
            int i = 1;
            statement.setInt(i++, townId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    resorts.add(mapToResort(rs));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
        }

        if (resorts.isEmpty()) {
            LOGGER.log(Level.INFO, "No resorts for town with ID " + townId + " found");
        }

        return resorts;
    }

    public Long createResort(CreateResortDto createResortDto) {
        try (PreparedStatement statement = this.connection.prepareStatement(SQL_INSERT_RESORT, Statement.RETURN_GENERATED_KEYS)) {
            int i = 1;
            statement.setString(i++, createResortDto.name());
            statement.setLong(i++, createResortDto.userId());
            statement.setTimestamp(i++, Timestamp.from(createResortDto.createdAt()));
            if (statement.executeUpdate() == 0) {
                throw new SQLException("Creating resort failed, no rows affected.");
            }

            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    long resortId = rs.getLong(1);
                    LOGGER.log(Level.DEBUG, "Resort ID generated: " + resortId);
                    this.saveTownResort(resortId, createResortDto);
                    return resortId;
                } else {
                    LOGGER.log(Level.INFO, "No resort ID generated");
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
        }

        return null;
    }

    private void saveTownResort(long resortId, CreateResortDto createResortDto) throws SQLException {
        try (PreparedStatement statement = this.connection.prepareStatement(SQL_INSERT_TWON_RESORT)) {
            for (int townId : createResortDto.townIds()) {
                int i = 1;
                statement.setInt(i++, townId);
                statement.setLong(i++, resortId);
                statement.setTimestamp(i++, Timestamp.from(createResortDto.createdAt()));
                statement.addBatch();
            }
            int[] result = statement.executeBatch();
            if (result == null || result.length == 0) {
                throw new SQLException("Creating resort failed, no rows affected.");
            } else {
                String resultString = Arrays.stream(result)
                        .mapToObj(String::valueOf)
                        .collect(Collectors.joining(",", "[", "]"));
                LOGGER.log(Level.INFO, resultString);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
        }
    }

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

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
        }

        return false;
    }

    public boolean updatePermitImage(long resortId, String permitImage, Instant updatedAt) {
        try (PreparedStatement statement = this.connection.prepareStatement(SQL_UPDATE_RESORT_PERMIT_IMAGE)) {
            int i = 1;
            statement.setString(i++, permitImage);
            statement.setTimestamp(i++, Timestamp.from(updatedAt));
            statement.setLong(i++, resortId);

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
        }

        return false;
    }

    private static Resort mapToResort(ResultSet rs) throws SQLException {
        var createdAt = rs.getTimestamp("resort_created_at");
        var updatedAt = rs.getTimestamp("resort_updated_at");

        return new Resort(
                rs.getLong("resort_id"),
                rs.getString("resort_name"),
                rs.getString("description"),
                rs.getString("location"),
                rs.getString("how_to_get_there"),
                rs.getBigDecimal("resort_fee"),
                rs.getBigDecimal("cottage_fee"),
                rs.getBigDecimal("pool_fee"),
                rs.getString("resort_image"),
                rs.getString("pool_image"),
                rs.getString("cottage_image"),
                rs.getLong("user_id"),
                rs.getString("permit_image"),
                createdAt != null ? createdAt.toInstant() : null,
                updatedAt != null ? updatedAt.toInstant() : null
        );
    }

}
