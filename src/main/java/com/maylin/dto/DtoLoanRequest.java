package com.maylin.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoLoanRequest {
	
	@NotNull(message="Member must be selected.")
	@Min(value=1,message="Member ID must be greater than 0.")
	private Long memberId;
	
	@NotNull(message="BookItem must be selected.")
	@Min(value=1,message="BookItem ID must be greater than 0.")
	private Long bookitemId;

}
