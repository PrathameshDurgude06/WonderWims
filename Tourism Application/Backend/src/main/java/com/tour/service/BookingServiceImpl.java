package com.tour.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tour.custom_exception_handler.ResourceNotFoundException;
import com.tour.dto.ApiResponse;
import com.tour.dto.BookingDTO;
import com.tour.entities.Booking;
import com.tour.entities.Tour;
import com.tour.entities.User;
import com.tour.repository.BookingRepository;
import com.tour.repository.TourRepository;
import com.tour.repository.UserRepository;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TourRepository tourRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@Override
	public ApiResponse addBooking(BookingDTO dto) {
		Tour tour=tourRepository.findById(dto.getTourId())
				.orElseThrow(()-> new ResourceNotFoundException("Invalid Tour ID"));
		User user=userRepository.findById(dto.getUserId())
				.orElseThrow(()-> new ResourceNotFoundException("Invalid User ID"));
		// tour, user : persistent
		//map booking DTO --> Entity
		
		Booking booking= mapper.map(dto, Booking.class);
		booking.setUser(user);
		booking.setTour(tour);
		
		bookingRepository.save(booking);
		
		return new ApiResponse(HttpStatus.OK,"Booking Done SUccessfully");
		
	}

	@Override
	public Booking getBookingById(Long bookingId) {
		// Inherited method --> optional
	    return bookingRepository.findById(bookingId)
	            .orElseThrow(() -> new ResourceNotFoundException("Invalid Booking ID"));
	}

	@Override
	public List<BookingDTO> getAllBookings() {
	    return bookingRepository.findAll()  // Retrieves all Booking entities from the database
	            .stream()  // Converts the list to a Stream for processing
	            .map(booking -> mapper.map(booking, BookingDTO.class))  // Maps each Booking entity to a BookingDTO
	            .collect(Collectors.toList());  // Collects the results into a List
	}

	@Override
	public ApiResponse updateBooking(Long bookingId, Booking booking) {
	    if (bookingRepository.existsById(bookingId)) {
	    	booking.setBookingId(bookingId);
	        bookingRepository.save(booking);
	        return new ApiResponse(HttpStatus.OK,"Booking updated successfully");
	    } else {
	        throw new ResourceNotFoundException("Invalid ID");
	    }
	}

	@Override
	public ApiResponse deleteBooking(Long bookingId) {
	    if (bookingRepository.existsById(bookingId)) {
	        bookingRepository.deleteById(bookingId);
	        return new ApiResponse(HttpStatus.OK,"Booking deleted successfully");
	    } else {
	        return new ApiResponse(HttpStatus.NOT_FOUND,"Booking deletion failed: Invalid Booking ID");
	    }
	}

	
}
