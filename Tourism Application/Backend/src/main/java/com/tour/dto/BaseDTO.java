package com.tour.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class BaseDTO {
	
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDate creationDate;
	@JsonProperty(access = Access.READ_ONLY)
	private LocalDateTime updationTimeStamp;
}
