package com.tour.dto;

import java.time.LocalDate;

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
public class AccommodationResponseDTO {
    
    private String name;
    private String type;
    private String location;
    private String details;
    private LocalDate checkIn;
    private LocalDate checkOut;
}
