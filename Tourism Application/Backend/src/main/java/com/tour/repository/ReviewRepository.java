package com.tour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tour.entities.Review;

public interface ReviewRepository extends JpaRepository<Review,Long> {

}
