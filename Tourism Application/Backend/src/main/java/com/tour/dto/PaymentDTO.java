package com.tour.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
	 private Long paymentId;
	
	 private String paymentMethod;
	    
	 private double amount;
	    
	 private LocalDate date;
	    
	 private String status;
	 
	 @JsonProperty(access = Access.WRITE_ONLY)
	 private Long bookingId;


}
