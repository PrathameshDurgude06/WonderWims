package com.tour.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tour.dto.ApiResponse;
import com.tour.dto.BookingDTO;
import com.tour.service.BookingService;

@RestController
@ControllerAdvice
@RequestMapping("/v2")
@CrossOrigin("*")
public class CustomerController {
	
	@Autowired
	private BookingService bookingService;
	
	// End point to book a tour package
    @PostMapping("/bookings/book")
    public ResponseEntity<ApiResponse> bookTourPackage(@Valid @RequestBody BookingDTO bookingDTO) {
    	return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.bookTourPackage(bookingDTO));
    }

    // End point to get booking details by booking ID
    @GetMapping("/bookings/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long bookingId) {
        BookingDTO bookingDTO = bookingService.getBookingById(bookingId);
        return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
    }

    // End point to get all bookings for the logged-in customer
    @GetMapping("/bookings")
    public ResponseEntity<List<BookingDTO>> getCustomerBookings(@RequestParam Long userId) {
        List<BookingDTO> bookings = bookingService.getCustomerBookings(userId);
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    // End point to delete a booking by booking ID -->only for the customer who made the booking
    @DeleteMapping("/bookings/{bookingId}")
    public ResponseEntity<ApiResponse> deleteBookingByCustomer(@PathVariable Long bookingId,@RequestParam Long userId) {
        ApiResponse response = bookingService.deleteBookingByCustomer(bookingId, userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	

	
}
