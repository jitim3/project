package project.service.impl;

import java.util.List;
import java.util.Optional;

import project.dao.ReviewDao;
import project.dao.entity.Review;
import project.dao.impl.DefaultReviewDao;
import project.dto.CreateReviewDto;
import project.dto.ReviewDto;
import project.service.ReviewService;

public class DefaultReviewService implements ReviewService {
	private final ReviewDao reviewDao;
	
	public DefaultReviewService() {
		this.reviewDao = new DefaultReviewDao();
	}

	@Override
	public Optional<ReviewDto> getReviewById(Long id) {		
		return this.reviewDao.getReviewById(id)
				.map(this::mapToReviewDto);
	}

	@Override
	public List<ReviewDto> getReviews() {
		return this.reviewDao.getReviews().stream()
				.map(this::mapToReviewDto)
				.toList();
	}

	@Override
	public ReviewDto createReview(CreateReviewDto createReviewDto) {
		Review createdReview = this.reviewDao.createReview(createReviewDto);
		
		return this.mapToReviewDto(createdReview);
	}
	
	private ReviewDto mapToReviewDto(Review review) {
		return new ReviewDto(
				review.id(), 
				review.userId(), 
				review.resortId(), 
				review.comment(), 
				review.createdAt(), 
				review.updatedAt()
			);
	}
}
