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
public class DtoAuthorRequest {
	
	@NotBlank(message="Firstname cannot be blank.")
	@Size(min=2,max=50,message="Firstname must be between 2 and 50 characters.")
	@Pattern(regexp = "^[a-zA-ZçÇğĞıİöÖşŞüÜ\\s]+$", message = "Firstname cannot be that.")
	private String firstName;
	
	
	@NotBlank(message="Lastname cannot be blank.")
	@Size(min=2,max=50,message="Lastname must be between 2 and 50 characters.")
	@Pattern(regexp = "^[a-zA-ZçÇğĞıİöÖşŞüÜ\\s]+$", message = "Lastname cannot be that.")
	private String lastName;
	

}

