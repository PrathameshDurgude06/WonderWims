package com.tour.service;

import java.util.List;

import com.tour.dto.ApiResponse;
import com.tour.dto.TourDTO;

public interface TourService {
	
	ApiResponse addTour(TourDTO tourDTO);
	
    ApiResponse updateTour(Long tourId, TourDTO tourDTO);
    
    ApiResponse deleteTour(Long tourId);
    
    TourDTO getTourByTitle(String title);
    
    List<TourDTO> getAllTours();
}
