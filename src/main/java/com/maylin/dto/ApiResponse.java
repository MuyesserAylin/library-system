package com.maylin.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class  ApiResponse<T> {
	
	private boolean succes;
	private int statusCode;
	private Integer errorCode;
	private String message;
	private T data;
	private String path;
	private LocalDateTime timestamp=LocalDateTime.now();

}
