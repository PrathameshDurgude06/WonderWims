package com.tour.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tour.entities.Tour;

public interface TourRepository extends JpaRepository<Tour, Long>{

	List<Tour> findAllByTitle(String name);
}
