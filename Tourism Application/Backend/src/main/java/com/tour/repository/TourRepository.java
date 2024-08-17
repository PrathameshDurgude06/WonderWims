package com.tour.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tour.entities.Tour;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    
	 // Custom query method to find tours by title
    List<Tour> findByTitle(String title);
    
    boolean existsByTitleAndStartDate(String title, LocalDate startDate);
    
    Optional<Tour> findById(Long tourId);
    
    @Query("SELECT t FROM Tour t LEFT JOIN FETCH t.destinations")
    List<Tour> findAllWithDestinations();
    
    @Modifying
    @Query("DELETE FROM Tour t WHERE t.id = :tourId")
    void deleteTourById(@Param("tourId") Long tourId);
 
}
