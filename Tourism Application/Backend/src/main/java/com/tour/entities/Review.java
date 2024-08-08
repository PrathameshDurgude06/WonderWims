package com.tour.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Review {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "review_id")
	 private Long reviewId;
	 
	 private int rating;
	 
	 private String comment;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name="commenter_id",nullable = false)
	 private User commenter;

	 @OneToOne
	 @JoinColumn(name = "booking_id")
	 private Booking booking;
}
