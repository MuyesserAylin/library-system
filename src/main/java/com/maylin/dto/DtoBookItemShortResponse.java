package com.maylin.dto;


import com.maylin.enums.Status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoBookItemShortResponse {
	
	private Long id;
	
	private String barcode;
	
	private Status status;

}
