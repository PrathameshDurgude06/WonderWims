package com.tour.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tour.entities.Booking;
import com.tour.entities.User;

public interface BookingRepository extends JpaRepository<Booking,Long> {
	 List<Booking> findByUser(User user);
	 
	// Custom query to find bookings by status and booking date
	 List<Booking> findByStatusAndBookingDate(String status, LocalDate startDate);
	    
	 // Check if a booking with the same ID exists
	 boolean existsById(Long bookingId);
}
