package com.maylin.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex ,HttpServletRequest request) {
		
		Map<String,List<String>> errors=new HashMap();
		
				for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
					String fieldName=fieldError.getField();
					String message=fieldError.getDefaultMessage();
					
					if(!errors.containsKey(fieldName)) {
						errors.put(fieldName, new ArrayList<String>());
					}
				errors.get(fieldName).add(message);
				}
				
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createApiResponse(HttpStatus.BAD_REQUEST.value(),ErrorCode.VALIDATION_ERROR,errors,request.getRequestURI()));
		
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
