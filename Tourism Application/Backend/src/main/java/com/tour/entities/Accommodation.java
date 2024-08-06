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
@Table(name = "accommodations")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Accommodation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "acco_id")
	private Long accoId;
	
	private String name;
	
    private String type;
    
    private String location;
    
    private String details;
    
    private LocalDate checkIn;
    
    private LocalDate checkOut;
	
    @OneToOne
    @JoinColumn(name = "dest_id")
    private Destination destination;
}
