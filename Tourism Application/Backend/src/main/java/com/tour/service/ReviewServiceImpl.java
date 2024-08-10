package com.tour.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.xml.stream.events.Comment;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.custom_exception.ApiException;
import com.tour.custom_exception.ResourceNotFoundException;
import com.tour.dto.ApiResponse;
import com.tour.dto.ReviewDTO;
import com.tour.entities.Booking;
import com.tour.entities.Review;
import com.tour.entities.User;
import com.tour.repository.BookingRepository;
import com.tour.repository.ReviewRepository;
import com.tour.repository.UserRepository;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired 
	private ModelMapper mapper;
	
	@Override
	public List<Review> getAllReviews() {
		return reviewRepository.findAll();
	}

	@Override
	public Optional<Review> getReviewById(Long id) {
		 return reviewRepository.findById(id);
	} 

	@Override
	public void deleteReview(Long id) {
		 reviewRepository.deleteById(id);
		
	}

	@Override
	public ApiResponse crateNewReview(ReviewDTO reviewDTO) {
		User user=userRepository.findById(reviewDTO.getUserId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid user id"));
		Booking booking=bookingRepository.findById(reviewDTO.getBookingId())
				.orElseThrow(() -> new ResourceNotFoundException("Invalid booking id !!!!"));
		if(user.getUserId()==booking.getUser().getUserId())
			throw new ApiException("Bloggers cant comment on their own post");
		Review review=mapper.map(reviewDTO, Review.class);
		review.setCommenter(user);
		
		review.setBooking(booking);
		Review savedReview =reviewRepository.save(review);
		
		return new ApiResponse("New Review added Successfilly");
	}

}
