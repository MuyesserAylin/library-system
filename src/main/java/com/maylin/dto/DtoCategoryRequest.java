package com.maylin.dto;

import jakarta.validation.constraints.NotBlank;
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
public class DtoCategoryRequest {

	//INSERT-UPDATE
	
	@NotBlank(message="Category name cannot be blank.")
	@Size(min=2,max=50 ,message="Category name must be between 2 and 50 characters.")
	@Pattern(regexp="^[a-zA-ZçĞüÜöÖşŞİı\\s-]+$", message="Category name can only contain letters, spaces, and hyphens.")
	private String name;
	
}
