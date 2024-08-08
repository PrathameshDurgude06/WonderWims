package com.tour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tour.entities.Itinerary;

public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {

}
