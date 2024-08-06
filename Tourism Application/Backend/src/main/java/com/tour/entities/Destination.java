package com.tour.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "destinations")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Destination {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dest_id")
	private Long destId;
	
	@Column(name = "dest_name")
	private String destName;
	
	private String state;
	
	private String description;
	
	
}
