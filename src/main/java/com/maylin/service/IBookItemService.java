package com.maylin.service;

import java.util.List;

import com.maylin.dto.DtoBookItemDetailResponse;
import com.maylin.dto.DtoBookItemRequest;
import com.maylin.dto.DtoBookItemResponse;

public interface IBookItemService {
	
	public DtoBookItemResponse saveBookitem(DtoBookItemRequest request);
	
	public DtoBookItemDetailResponse getBookItemById(Long id);

	public void deleteBookItem(Long id);
	
	public List<DtoBookItemResponse> getBookItemByBookId(Long bookId);
}
