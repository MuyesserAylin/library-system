package com.maylin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maylin.model.Author;

@Repository
public interface IAuthorRepository extends JpaRepository<Author,Long> {
	
	boolean existsByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName,String lastName);

}
