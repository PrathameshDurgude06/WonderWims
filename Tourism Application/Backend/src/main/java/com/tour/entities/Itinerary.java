package com.tour.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "itinearies")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Itinerary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "itinerary_id")
	private Long itineraryId;
	
	private String day;
	
	private String description;
	
	private String location;
	
	@ManyToOne
	@JoinColumn(name = "tour_id")
	private Tour tour;
}
