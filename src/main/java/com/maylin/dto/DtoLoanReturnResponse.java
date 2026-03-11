package com.maylin.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoLoanReturnResponse {

	
	private Long id;
	
	private String memberName;
	
	private String bookTitle;
	
	private String barcode;
	
	private LocalDate loanDate;
	
	private LocalDate dueDate;
	
	private LocalDate returnDate;
	
	private Double penalty;
}
