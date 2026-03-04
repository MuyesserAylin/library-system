package com.maylin.service;

import com.maylin.dto.DtoBookItemRequest;
import com.maylin.dto.DtoBookItemResponse;

public interface IBookItemService {
	
	public DtoBookItemResponse saveBookitem(DtoBookItemRequest request);

}
