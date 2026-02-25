package com.maylin.exception;

import org.springframework.http.HttpStatus;

import com.maylin.enums.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
public class BaseException extends RuntimeException{
	
	private final ErrorCode errorCode;
	
	private final int statusCode;
	
	private Object errorData;
	
	public BaseException(ErrorCode errorCode,HttpStatus status,Object errorData) {
		
		super(errorCode.getErrorMessage());
		this.errorCode=errorCode;
		this.statusCode=status.value();
		this.errorData=errorData;
		
	}
	
	public BaseException(ErrorCode errorcode,HttpStatus status) {
		this(errorcode,status,null);
	}
	
	
	
	

}
