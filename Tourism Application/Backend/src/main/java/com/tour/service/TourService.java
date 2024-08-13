package com.tour.service;

import java.util.List;

import com.tour.dto.ApiResponse;
import com.tour.dto.TourDTO;
import com.tour.dto.TourResponseDTO;

public interface TourService {
	
	// Method to add a new tour
    ApiResponse addTour(TourDTO tourDTO);

    // Method to update an existing tour by its ID
    ApiResponse updateTour(Long tourId, TourDTO tourDTO);

    // Method to delete a tour by its ID
    ApiResponse deleteTour(Long tourId);

    // Method to get all tours
    List<TourResponseDTO> getAllTours();

    // Method to get tours by title, returning a list of detailed TourResponseDTOs
    List<TourResponseDTO> getTourByTitle(String title);

    // Method to get a specific tour by its ID, returning a detailed TourResponseDTO
    TourResponseDTO getTourById(Long tourId);
    
}
