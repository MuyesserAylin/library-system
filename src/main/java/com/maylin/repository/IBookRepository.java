package com.maylin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maylin.model.Book;
@Repository
public interface IBookRepository extends JpaRepository<Book,Long>{
	
	boolean existsByISBN(String isbn);

}
