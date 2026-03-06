package com.maylin.dto;

import java.util.List;

import com.maylin.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoBookItemDetailResponse {
	
    private Long id;
	
	private String barcode;
	
	private Status status;
	
	private DtoBookShortResponse book;
	
	public List<DtoLoanForBookItem> loans;

}
