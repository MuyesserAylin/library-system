package com.maylin.dto;

import java.util.List;
import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class DtoBookRequest {
	
	@NotBlank(message="Title cannot be blank.")
	@Size(min=2,max=255,message="Title must be between 2 and 255 characters.")
	private String title;
	
	@NotBlank(message="ISBN cannot be blank.")
	@Pattern(regexp = "^(978|979)?[- ]?\\d{1,5}[- ]?\\d{1,7}[- ]?\\d{1,7}[- ]?\\d{1}$", 
            message = "Invalid ISBN format.")
	private String ISBN;
	
	@NotNull(message="An author must be selected.")
	private Long authorId;
	
	@NotEmpty(message="At least one category must be selected.")
	private Set<Long> categoryIds;

}
