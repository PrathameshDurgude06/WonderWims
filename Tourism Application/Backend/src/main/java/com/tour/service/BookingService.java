package com.tour.service;

import java.util.List;

import com.tour.dto.ApiResponse;
import com.tour.dto.BookingDTO;
import com.tour.entities.Booking;

public interface BookingService {

	ApiResponse addBooking (BookingDTO bookingDTO);
	
	Booking getBookingById(Long bookingId);
	
	List<BookingDTO> getAllBookings();
	
	ApiResponse updateBooking(Long bookingId, Booking booking);
	
	ApiResponse deleteBooking(Long bookingId);
	
}
