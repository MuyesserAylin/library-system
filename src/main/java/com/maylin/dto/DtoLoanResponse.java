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
public class DtoLoanResponse {
	
	private Long id;
	
    private LocalDate loanDate;
	
	private LocalDate dueDate;
	
	private String memberName;
	
	private String bookTitle;
	
	private String barcode;
	
	
	

}
