package com.tour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tour.entities.Tour;

public interface TourRepository extends JpaRepository<Tour, Long>{

}
