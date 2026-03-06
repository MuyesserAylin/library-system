package com.maylin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.maylin.model.BookItem;

@Repository
public interface IBookItemRepository extends JpaRepository<BookItem,Long>{
	
	boolean existsByBarcode(String barcode);
	
   @Query("SELECT b FROM BookItem b LEFT JOIN FETCH b.book " +
           "LEFT JOIN FETCH b.loans l " +
		   "LEFT JOIN FETCH l.member " +
		   "WHERE b.id=:id")
   Optional<BookItem> findByIdWithLoans(@Param("id")Long id);

}
