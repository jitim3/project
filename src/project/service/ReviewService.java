package project.service;

import project.dao.ReviewDao;
import project.dao.entity.Review;
import project.dto.CreateReviewDto;
import project.dto.ReviewDto;

import java.util.List;
import java.util.Optional;

public class ReviewService {
	private final ReviewDao reviewDao;
	
	public ReviewService() {
		this.reviewDao = new ReviewDao();
	}

	public Optional<ReviewDto> getReviewById(Long id) {		
		return this.reviewDao.getReviewById(id)
				.map(this::mapToReviewDto);
	}

	public List<ReviewDto> getReviewsByResortId(long resortId) {
		return this.reviewDao.getReviewsByResortId(resortId).stream()
				.map(this::mapToReviewDto)
				.toList();
	}

	public ReviewDto createReview(CreateReviewDto createReviewDto) {
		Review createdReview = this.reviewDao.createReview(createReviewDto);
		
		return this.mapToReviewDto(createdReview);
	}
	
	private ReviewDto mapToReviewDto(Review review) {
		return new ReviewDto(
				review.id(), 
				review.userId(),
				review.firstName(),
				review.lastName(),
				review.resortId(), 
				review.rate(),
				review.comment(), 
				review.createdAt(), 
				review.updatedAt()
			);
	}
}
