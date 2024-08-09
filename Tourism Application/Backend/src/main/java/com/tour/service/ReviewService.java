package com.tour.service;

import java.util.List;
import java.util.Optional;

import com.tour.entities.Review;

public interface ReviewService {
	
	List<Review> getAllReviews();
	
	Optional<Review> getReviewById(Long id);
	
	Review saveReview(Review review);
	
	void deleteReview(Long id);
}
