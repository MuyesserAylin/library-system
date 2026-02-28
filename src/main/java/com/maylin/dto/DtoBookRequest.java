package com.maylin.dto;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
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
	
	@JsonProperty("ISBN")
	@NotBlank(message="ISBN cannot be blank.")
	@Pattern(regexp = "^[0-9\\-X]{10,17}$", message = "Invalid ISBN format.")
	private String ISBN;
	
	@NotNull(message="An author must be selected.")
	@Min(value = 1, message = "Author ID must be greater than 0.")
	private Long authorId;
	
	@NotEmpty(message="At least one category must be selected.")
	private Set<Long> categoryIds;

}
