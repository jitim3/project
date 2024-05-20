package project.service;

import java.util.List;
import java.util.Optional;

import project.dto.CreateReviewDto;
import project.dto.ReviewDto;

public interface ReviewService {
	Optional<ReviewDto> getReviewById(Long id);
	
	List<ReviewDto> getReviews();
	
	ReviewDto createReview(CreateReviewDto createReviewDto);
}
