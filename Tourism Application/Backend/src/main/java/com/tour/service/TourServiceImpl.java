package com.tour.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tour.custom_exception.ResourceNotFoundException;
import com.tour.entities.Tour;
import com.tour.repository.TourRepository;

@Service
@Transactional
public class TourServiceImpl implements TourService {

	@Autowired
	TourRepository tourRepository;
	
	@Override
	public Tour addPackage(Tour tour) {
		return tourRepository.save(tour);
	}

	@Override
	public Tour editPackage(Long id, Tour tour) {
		Tour tour2 = tourRepository.findById(id).orElseThrow();
		tour2.setTitle(tour.getTitle());
		tour2.setPrice(tour.getPrice());
		tour2.setDescription(tour.getDescription());
        tour2.setDuration(tour.getDuration());
        tour2.setStartDate(tour.getStartDate());
        return tourRepository.save(tour2);
	}

	@Override
	public List<Tour> getByName(String name) {
		return tourRepository.findAllByTitle(name);
	}

	@Override
	public Tour getById(Long id) {
		return tourRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Invalid ID"));
	}

	@Override
	public String deleteById(Long id) {
		if(tourRepository.existsById(id))
		{
			tourRepository.deleteById(id);
			return "Deleted";
		}
		return "Not deleted";
	}

}
