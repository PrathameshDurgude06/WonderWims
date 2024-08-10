package com.tour.service;

import java.util.List;
import java.util.Optional;

import com.tour.dto.ApiResponse;
import com.tour.dto.ReviewDTO;
import com.tour.entities.Review;

public interface ReviewService {
	
	List<Review> getAllReviews();
	
	Optional<Review> getReviewById(Long id);
	
	ApiResponse crateNewReview(ReviewDTO reviewDTO);
	
	void deleteReview(Long id);
}
