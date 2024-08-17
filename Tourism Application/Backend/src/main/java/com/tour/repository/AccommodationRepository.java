package com.tour.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tour.entities.Accommodation;

public interface AccommodationRepository extends JpaRepository<Accommodation,Long>{

	@Modifying
    @Query("DELETE FROM Accommodation a WHERE a.id IN :accommodationIds")
    void deleteAccommodationsByIds(@Param("accommodationIds") List<Long> accommodationIds);
}
