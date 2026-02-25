package com.maylin.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoBookResponse {
	
	private Long id;
	
	private String title;
	
	private String ISBN;
	
	private DtoAuthorSummary author;
	
	private List<DtoCategoryShortResponse> categories=new ArrayList<DtoCategoryShortResponse>();

}
