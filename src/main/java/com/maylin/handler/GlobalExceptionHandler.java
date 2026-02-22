package com.maylin.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.maylin.dto.ApiResponse;
import com.maylin.enums.ErrorCode;
import com.maylin.exception.BaseException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	
	@ExceptionHandler(BaseException.class)
	public 	 ResponseEntity<ApiResponse<Object>> handleBaseException(BaseException ex,HttpServletRequest request) {
		
		
	  return 	ResponseEntity.status(ex.getStatusCode()).body(createApiResponse(ex.getStatusCode(),ex.getErrorCode(),null,request.getRequestURI()));
	}
	
	private <T> ApiResponse<T> createApiResponse(int statusCode,ErrorCode errorCode,T data,String path){
		
		ApiResponse<T> response=new ApiResponse<T>();
		response.setSucces(false);
		response.setStatusCode(statusCode);
		response.setErrorCode(errorCode.getErrorCode());
		response.setMessage(errorCode.getErrorMessage());
		response.setData(data);
		response.setPath(path);
		
		return response;
	}

}
