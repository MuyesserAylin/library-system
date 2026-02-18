package com.maylin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.maylin.model.Author;

@Repository
public interface IAuthorRepository extends JpaRepository<Author,Long> {
	
	boolean existsByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName,String lastName);
	
	@Query("select distinct a from Author a left join fetch a.books order by a.firstName asc")
	List<Author> getAllAuthors();
	
	@Query("select a from Author a left join a. books where a.id=:id")
	Optional<Author> findAuthorWithBooks(@Param("id") Long id);

	
	
	
	
	
}
