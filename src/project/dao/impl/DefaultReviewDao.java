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

import project.dao.ReviewDao;
import project.dao.entity.Review;
import project.dto.CreateReviewDto;
import project.util.DatabaseConnectionFactory;

public class DefaultReviewDao implements ReviewDao {
	private static final Logger LOGGER = System.getLogger(DefaultReviewDao.class.getName());
	private static final String SQL_SELECT_REVIEWS = "SELECT id, user_id, resort_id, comment, created_at, updated_at FROM review";
	private static final String SQL_SELECT_REVIEW_BY_ID = SQL_SELECT_REVIEWS + " WHERE id = ?";
	private static final String SQL_INSERT_REVIEW = "INSERT INTO review(user_id, resort_id, comment, created_at) VALUES(?, ?, ?, ?)";
	private final Connection connection;
	
	public DefaultReviewDao() {
		this.connection = DatabaseConnectionFactory.getConnection();
	}

	@Override
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

	@Override
	public List<Review> getReviews() {
		final List<Review> reviews = new ArrayList<>();
		
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_SELECT_REVIEWS)) {
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

	@Override
	public Review createReview(CreateReviewDto createReviewDto) {
		try (PreparedStatement statement = this.connection.prepareStatement(SQL_INSERT_REVIEW, Statement.RETURN_GENERATED_KEYS)) {
			int i = 1;
			statement.setLong(i++, createReviewDto.userId());
			statement.setLong(i++, createReviewDto.resortId());
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
				rs.getLong("id"),  
				rs.getLong("user_id"),
				rs.getLong("resort_id"),
				rs.getString("content"),
				createdAt != null ? createdAt.toInstant() : null,
				updatedAt != null ? updatedAt.toInstant() : null
			);
	}
}
