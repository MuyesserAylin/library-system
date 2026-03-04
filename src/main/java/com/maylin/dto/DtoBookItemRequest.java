package com.maylin.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DtoBookItemRequest {
	
	@NotBlank(message="Barcode cannot be blank.")
	@Pattern(regexp = "^[A-Z0-9]{6,20}$", message = "Barcode must be 6-20 uppercase letters and numbers.")
	private String barcode;
	
	@NotNull(message="Book must be selected")
	@Min(value=1,message="Book ID must be greater than 0.")
	private Long bookId;

}
