package com.tour.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.tour.entities.Status;
import com.tour.entities.Tour;
import com.tour.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookingDTO {
	
	private Long bookingId;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	private LocalDate bookingDate;
	
	@Min(0)
	private double totalCost;
	
	@NotNull
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long userId;
	
	@NotNull
	@JsonProperty(access = Access.WRITE_ONLY)
    private Long tourId;
}
