package com.tour.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tour.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
