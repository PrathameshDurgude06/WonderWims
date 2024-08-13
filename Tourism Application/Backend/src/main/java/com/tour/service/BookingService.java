package com.tour.service;

import java.time.LocalDate;
import java.util.List;

import com.tour.dto.ApiResponse;
import com.tour.dto.BookingDTO;

public interface BookingService {

    // Method to book a tour package
    ApiResponse bookTourPackage(BookingDTO bookingDTO);

    // Method for customers to get all their bookings
    List<BookingDTO> getCustomerBookings(Long userId);

    // Method for admins to get all bookings
    List<BookingDTO> getAllBookings();

    // Method to delete a specific booking by customer
    ApiResponse deleteBookingByCustomer(Long bookingId, Long userId);

    // Method to get a specific booking by its ID
    BookingDTO getBookingById(Long bookingId);
    
    //Method to get a bookings by status
    List<BookingDTO> searchBookings(String status, LocalDate startDate);
    
    //Method to update a booking by its ID
    ApiResponse updateBooking(Long bookingId, BookingDTO bookingDTO);
}
