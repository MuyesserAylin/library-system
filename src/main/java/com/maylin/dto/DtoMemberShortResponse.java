package com.maylin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DtoMemberShortResponse {
	
	private Long id;
	
	private String memberName;
	
	private String email;

}
