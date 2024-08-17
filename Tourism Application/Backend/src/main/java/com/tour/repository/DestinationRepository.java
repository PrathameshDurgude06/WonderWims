package com.tour.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tour.entities.Destination;

public interface DestinationRepository extends JpaRepository<Destination,Long>{

	@Modifying
    @Query("DELETE FROM Destination d WHERE d.id IN :destinationIds")
    void deleteDestinationsByIds(@Param("destinationIds") List<Long> destinationIds);

}
