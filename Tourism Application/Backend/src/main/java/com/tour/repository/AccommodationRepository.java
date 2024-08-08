package com.tour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tour.entities.Accommodation;

public interface AccommodationRepository extends JpaRepository<Accommodation,Long>{

}
