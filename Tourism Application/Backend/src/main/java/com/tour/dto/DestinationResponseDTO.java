package com.tour.dto;

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
public class DestinationResponseDTO {

    private String destName;
    private String state;
    private String description;
    
    // Accommodation details
    private AccommodationResponseDTO accommodation;
}
