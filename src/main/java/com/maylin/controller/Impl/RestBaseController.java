package com.maylin.controller.Impl;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;

import com.maylin.dto.ApiResponse;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

public abstract class RestBaseController {
	
	protected <T> ResponseEntity<ApiResponse<T>> ok(T data,HttpServletRequest request ){
		
		ApiResponse<T> response=new ApiResponse<T>();
		response.setSucces(true);
		response.setStatusCode(200);
		response.setErrorCode(null);
		response.setMessage("OK");
		response.setData(data);
		response.setPath(request.getRequestURI());
		response.setTimestamp(LocalDateTime.now());
		return ResponseEntity.status(200).body(response);
		
	}
	
    protected <T> ResponseEntity<ApiResponse<T>> created(T data,HttpServletRequest request ){
		
		ApiResponse<T> response=new ApiResponse<T>();
		response.setSucces(true);
		response.setStatusCode(200);
		response.setErrorCode(null);
		response.setMessage("Created");
		response.setData(data);
		response.setPath(request.getRequestURI());
		response.setTimestamp(LocalDateTime.now());
		return ResponseEntity.status(201).body(response);
		
	}
	
	

}
