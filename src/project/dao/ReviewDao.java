package project.dao;

import project.dao.entity.Review;
import project.dto.CreateReviewDto;
import project.util.DatabaseConnectionFactory;

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

public class ReviewDao {
    private static final Logger LOGGER = System.getLogger(ReviewDao.class.getName());
    private static final String SQL_SELECT_REVIEWS = """
            SELECT r.id AS review_id, r.user_id, c.first_name, c.last_name, r.resort_id, r.rate, r.comment, r.created_at, r.updated_at
            FROM review r
            LEFT JOIN customer c ON c.id = r.user_id
            """;
    private static final String SQL_SELECT_REVIEW_BY_ID = SQL_SELECT_REVIEWS + " WHERE r.id = ?";
    private static final String SQL_SELECT_REVIEW_BY_RESORT_ID = SQL_SELECT_REVIEWS + " WHERE r.resort_id = ?";
    private static final String SQL_INSERT_REVIEW = "INSERT INTO review(user_id, resort_id, rate, comment, created_at) VALUES(?, ?, ?, ?, ?)";
    private final Connection connection;

    public ReviewDao() {
        this.connection = DatabaseConnectionFactory.getConnection();
    }

    public Optional<Review> getReviewById(Long id) {
        try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_REVIEW_BY_ID)) {
            int i = 1;
            statement.setLong(i++, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return Optional.of(mapToReview(rs));
            } else {
                LOGGER.log(Level.INFO, "No review with ID " + id + " found");
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
        }

        return Optional.empty();
    }

    public List<Review> getReviewsByResortId(long resortId) {
        final List<Review> reviews = new ArrayList<>();

        try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_REVIEW_BY_RESORT_ID)) {
            int i = 1;
            statement.setLong(i++, resortId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                reviews.add(mapToReview(rs));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
        }

        if (reviews.isEmpty()) {
            LOGGER.log(Level.INFO, "No reviews found found");
        }

        return reviews;
    }

    public Review createReview(CreateReviewDto createReviewDto) {
        try (PreparedStatement statement = this.connection.prepareStatement(SQL_INSERT_REVIEW, Statement.RETURN_GENERATED_KEYS)) {
            int i = 1;
            statement.setLong(i++, createReviewDto.userId());
            statement.setLong(i++, createReviewDto.resortId());
            statement.setInt(i++, createReviewDto.rate());
            statement.setString(i++, createReviewDto.comment());
            statement.setTimestamp(i++, Timestamp.from(createReviewDto.createdAt()));
            if (statement.executeUpdate() == 0) {
                throw new SQLException("Creating review failed, no rows affected.");
            }

            try (ResultSet rs = statement.getGeneratedKeys()) {
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    LOGGER.log(Level.DEBUG, "Review ID generated: " + id);
                    return this.getReviewById(id).orElse(null);
                } else {
                    LOGGER.log(Level.ERROR, "No review ID genereted");
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e);
        }

        return null;
    }

    private static Review mapToReview(ResultSet rs) throws SQLException {
        var createdAt = rs.getTimestamp("created_at");
        var updatedAt = rs.getTimestamp("updated_at");

        return new Review(
                rs.getLong("review_id"),
                rs.getLong("user_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getLong("resort_id"),
                rs.getInt("rate"),
                rs.getString("comment"),
                createdAt != null ? createdAt.toInstant() : null,
                updatedAt != null ? updatedAt.toInstant() : null
        );
    }
}
