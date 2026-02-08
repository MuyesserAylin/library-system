package com.maylin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
public enum Status {
	
	AVAILABLE("Available"),
	BORROWED("Borrowed"),
	LOST("Lost");
	
	private final String displayValue;
	

}
