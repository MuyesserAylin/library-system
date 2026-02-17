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
public class DtoAuthorResponse {
	
	private Long id;
	
	private String authorName;
	
	private List<DtoBookForAuthor> books=new ArrayList<>();
	

}
