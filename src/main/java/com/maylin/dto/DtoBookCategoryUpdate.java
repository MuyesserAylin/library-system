package com.maylin.dto;

import java.util.Set;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoBookCategoryUpdate {
	
	@NotNull(message = "Category list cannot be null.")
	@NotEmpty(message = "At least one category must be selected.")
	private Set<@Min(value=1, message="Category ID must be greater than 0.")Long> categoryIds;

}
