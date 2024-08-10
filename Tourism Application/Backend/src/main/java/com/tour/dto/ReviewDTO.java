package com.tour.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO  {
	
	@JsonProperty(access = Access.READ_ONLY)
	private Long reviewId;
	
	private int rating;
	
	private String commentText;
	
	@JsonProperty(access = Access.WRITE_ONLY) //de ser only
	private Long userId;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long bookingId;

}
