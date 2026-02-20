package com.maylin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maylin.model.Member;
@Repository
public interface IMemberRepository extends JpaRepository<Member,Long> {
	
	boolean existsByEmailIgnoreCase(String email);

}
