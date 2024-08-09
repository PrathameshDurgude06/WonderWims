package com.tour.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccommodationDTO {
	
	private Long accoId;
	
	private String name;
	
    private String type;
    
    private String location;
    
    private String details;
    
    private LocalDate checkIn;
    
    private LocalDate checkOut;
	
    @JsonProperty(access = Access.WRITE_ONLY)
    private Long destinationId;
}
