package com.maylin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.maylin.model.Book;
@Repository
public interface IBookRepository extends JpaRepository<Book,Long>{
	
	boolean existsByISBN(String isbn);
	
	
	
	
	@Query("SELECT b FROM Book b " +                
		       "LEFT JOIN FETCH b.author " +          
		       "LEFT JOIN FETCH b.categories " +           
		       "WHERE b.id = :id")
		Optional<Book> findByIdWithCategories(@Param("id") Long id);
	
	
	@Query("SELECT b FROM Book b " +                         
		       "LEFT JOIN FETCH b.bookItems " +           
		       "WHERE b.id = :id")
		Optional<Book> findByIdWithBookItems(@Param("id") Long id);
	
	
	@Query("SELECT b FROM Book b " +                
		       "LEFT JOIN FETCH b.author " +          
		       "LEFT JOIN FETCH b.categories ")
		List<Book> findAllWithCategories();
}
