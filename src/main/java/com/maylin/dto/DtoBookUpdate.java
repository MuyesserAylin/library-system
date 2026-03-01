package com.maylin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoBookUpdate {
	
	@Size(min=2,max=255,message="Title must be between 2 and 255 characters.")
	private String title;

	@JsonProperty("ISBN")
	@Pattern(regexp = "^[0-9\\-X]{10,17}$", message = "Invalid ISBN format.")
	private String ISBN;
	
	@Min(value = 1, message = "Author ID must be greater than 0.")
	private Long authorId;

	

}
