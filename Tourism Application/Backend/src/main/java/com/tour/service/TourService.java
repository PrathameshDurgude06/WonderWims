package com.tour.service;

import java.util.List;

import com.tour.entities.Tour;

public interface TourService {
	
	Tour addPackage(Tour tour);
	
	Tour editPackage(Long id,Tour tour);
	
	List<Tour> getByName(String name);
	
	Tour getById(Long id);
	
	String deleteById(Long id);
}
