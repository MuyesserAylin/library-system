package com.maylin.dto;

import java.time.LocalDate;

import com.maylin.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoLoanShortResponse {
	
	private Long id;
	
    private String title;
	
	private String barcode;
	
	private Status status;
	
	private LocalDate loanDate;
	
	private LocalDate dueDate;
	
	private LocalDate returnDate;
	
	

}
