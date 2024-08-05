package com.tour.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public class BaseEntity {
	@Column(name = "creation_date")
	@CreationTimestamp
	private LocalDate creationDate;
	
	@UpdateTimestamp
	@Column(name = "updated_on")
	private LocalDate updationDate;

	public BaseEntity() {
		// TODO Auto-generated constructor stub
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDate getUpdationDate() {
		return updationDate;
	}

	public void setUpdationDate(LocalDate updationDate) {
		this.updationDate = updationDate;
	}

	@Override
	public String toString() {
		return "BaseEntity [creationDate=" + creationDate + ", updationDate=" + updationDate + "]";
	}
	
	
}
