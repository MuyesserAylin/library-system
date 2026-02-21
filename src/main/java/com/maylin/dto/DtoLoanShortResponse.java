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
public class DtoLoanShortResponse {
	
	private Long id;
	
    private String title;
	
	private String barcode;
	
	private LocalDate loanDate;
	
	private LocalDate dueDate;
	
	private LocalDate returnDate;
	
	//Şimdi loan ıcınde bookItem var burdan bu bookItem nesnesınden barcode ulscam ve 
	// bu bbokItem ıcınde book var burdanda title ulasıcam

}
