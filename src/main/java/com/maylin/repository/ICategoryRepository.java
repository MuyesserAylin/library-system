package com.maylin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.maylin.model.Category;

@Repository
public interface ICategoryRepository extends JpaRepository<Category,Long> {
	
	//Tek bir sorgu ile hem category hem de içindeki book nesnelerını cekebılıyoruz.
	@Query("select c  from Category c LEFT JOIN FETCH c.books where c.id=:id")
	Optional<Category> findCategoryWithBooks(@Param(value="id")Long id);
	
	//JPA otomatık olarak exists ıle true/false odner ByName DBDEKİ name kolonuna bakar.
	boolean existsByNameIgnoreCase(String name);
	
	List<Category> findByOrderByNameAsc();
	
	

}
