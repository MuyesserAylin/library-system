package com.maylin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoBookShortResponse {
	
	private Long id;
	
	private String title;
	
	private String authorName;
	
	

}
