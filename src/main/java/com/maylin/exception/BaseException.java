package com.maylin.exception;

import org.springframework.http.HttpStatus;

import com.maylin.enums.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
public class BaseException extends RuntimeException{
	
	private final ErrorCode errorCode;
	
	private final int statusCode;
	
	public BaseException(ErrorCode errorCode,HttpStatus status) {
		
		super(errorCode.getErrorMessage());
		this.errorCode=errorCode;
		this.statusCode=status.value();
		
	}
	
	

}
