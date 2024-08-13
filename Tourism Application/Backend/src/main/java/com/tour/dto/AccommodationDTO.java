package com.tour.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationDTO {
    
    private Long accoId;
    
    private String name;
    
    private String type;
    
    private String location;
    
    private String details;
    
    private LocalDate checkIn;
    
    private LocalDate checkOut;
}
