package com.tour.repository;

import com.tour.entities.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    
    // Custom query method to find a tour by its title
    Optional<Tour> findByTitle(String title);
}
