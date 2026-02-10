package com.maylin.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoCategoryResponse {
	
	//GET 
	
	private Long id;
	
	private String name;
	
	private List<DtoBookShortResponse> books=new ArrayList<>();
	
}
