package com.tour.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tour.service.TourService;

@RestController
@RequestMapping("/v1/tours")
@CrossOrigin
public class AdminController {
	
	@Autowired
	private TourService tourService;
	
	//create new tour
	@GetMapping("/view")
	public ResponseEntity<?> viewTours(){
		return ResponseEntity.ok(tourService.getAllTours());
	}
	
//	@PostMapping("/add")
//	public ResponseEntity<?> createTour(@RequestBody )

}
