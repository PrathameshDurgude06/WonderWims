package com.tour.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "payments")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Payment {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
    private Long paymentId;
	
	@Column(name = "payment_method")
    private String paymentMethod;
    
    private double amount;
    
    private LocalDate date;
    
    private String status;
    
    @OneToOne
    @JoinColumn(name="booking_id")
    private Booking booking;
}
