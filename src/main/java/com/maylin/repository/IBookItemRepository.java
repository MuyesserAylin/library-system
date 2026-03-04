package com.maylin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maylin.model.BookItem;

@Repository
public interface IBookItemRepository extends JpaRepository<BookItem,Long>{
	
	boolean existsByBarcode(String barcode);

}
