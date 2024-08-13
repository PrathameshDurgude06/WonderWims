package com.tour.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DestinationDTO {
    
    private Long destId;
    private String destName;
    private String state;
    private String description;
    
    // Accommodation details
    private AccommodationDTO accommodation;
}
