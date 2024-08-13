package com.tour.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tour.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking,Long> {
	 List<Booking> findByUserId(Long userId);
}
