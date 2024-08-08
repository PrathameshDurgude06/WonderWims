package com.tour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tour.entities.Destination;

public interface DestinationRepository extends JpaRepository<Destination,Long>{

}
