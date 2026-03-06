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
public class DtoLoanForBookItem {
	
	private Long id;
	
	private LocalDate loanDate;
	
	private LocalDate dueDate;
	
	private LocalDate returnDate;
	
	private Long memberId;
	
	private String memberName;

}
