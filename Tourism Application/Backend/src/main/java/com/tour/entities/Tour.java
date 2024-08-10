package com.tour.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tours")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Tour extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tour_id")
	private Long tourId;
	
	@Column(length = 20)
	private String title;
	
	private String description;
	
	private String duration;
	
	@Column(name = "start_date")
	private LocalDate startDate;
	
	private double price;
	
	@ElementCollection
	@CollectionTable(name = "tour_dest", joinColumns = @JoinColumn(name="tour_id"))
	@Column(name="dest_id")
	private List<Destination> destinations=new ArrayList<>();

//	@OneToMany
//	@JoinColumn(name = "dest_id")
//	private Destination destination;	

}
