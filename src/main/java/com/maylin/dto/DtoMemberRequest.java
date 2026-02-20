package com.maylin.dto;

import jakarta.validation.constraints.Email;
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
public class DtoMemberRequest {
	
	@NotBlank(message="Firstname cannot be blank.")
	@Size(min=2,max=50,message="Firstname must be between 2 and 50 characters.")
	@Pattern(regexp = "^[a-zA-ZçÇğĞıİöÖşŞüÜ\\s]+$", message = "Firstname cannot be that.")
	private String firstName;
	
	@NotBlank(message="Lastname cannot be blank.")
	@Size(min=2,max=50,message="Lastname must be between 2 and 50 characters.")
	@Pattern(regexp = "^[a-zA-ZçÇğĞıİöÖşŞüÜ\\s]+$", message = "Lastname cannot be that.")
	private String lastName;
	
	@NotBlank(message="Email cannot be blank.")
	@Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.(com|org|net|gov|edu|tr)(\\.(com|org|net|gov|edu|tr))?$", message = "Wrong email format!")
	private String email;
	
	@NotBlank(message="Password cannot be blank.")
	@Size(min=6,message="Password must be minimum 6 chracters.")
	@Pattern(regexp = "^\\S+$", message = "Password cannot contain spaces.")
	private String password;

}
