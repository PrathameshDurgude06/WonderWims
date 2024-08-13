package com.tour.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tour.dto.BookingDTO;
import com.tour.dto.TourDTO;
import com.tour.service.BookingService;
import com.tour.service.TourService;


@RestController
@ControllerAdvice
@RequestMapping("/v1")
@CrossOrigin("*")
public class AdminController {
	
	@Autowired
	private TourService tourService;
	
	@Autowired
	private BookingService bookingService;
	
	//create new tour
	@GetMapping("/tours")
	public ResponseEntity<?> viewTours(){
		return ResponseEntity.ok(tourService.getAllTours());
	}
	
	@PostMapping("/tours/add")
	public ResponseEntity<?> createTour(@RequestBody TourDTO tour){
		return ResponseEntity.status(HttpStatus.CREATED).body(tourService.addTour(tour));
	}
	
	@PutMapping("/tours/{tourId}")
	public ResponseEntity<?> updateTour(@PathVariable Long tourId, @RequestBody TourDTO tour){
		return ResponseEntity.ok(tourService.updateTour(tourId, tour));
	}
	
	@DeleteMapping("/tours/{tourId}")
	public ResponseEntity<?> deleteTour(@PathVariable Long tourId){
		return ResponseEntity.ok(tourService.deleteTour(tourId));
	}
	
	@GetMapping("/bookings")
    public ResponseEntity<List<BookingDTO>> getAllBookings() {
        List<BookingDTO> bookings = bookingService.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }
	

}
