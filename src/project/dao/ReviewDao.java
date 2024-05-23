package project.dao;

import java.util.List;
import java.util.Optional;

import project.dao.entity.Review;
import project.dto.CreateReviewDto;

public interface ReviewDao {
	Optional<Review> getReviewById(Long id);
	
	List<Review> getReviewsByResortId(long resortId);
	
	Review createReview(CreateReviewDto createReviewDto);
}
