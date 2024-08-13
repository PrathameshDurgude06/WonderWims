package com.tour.dto;

import java.time.LocalDate;
import java.util.List;

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
public class TourDTO {
    
    private Long tourId;
    
    private String title;
    
    private String description;
    
    private String duration;
    
    private LocalDate startDate;
    
    private double price;
    
    private List<DestinationDTO> destinations; 
}
