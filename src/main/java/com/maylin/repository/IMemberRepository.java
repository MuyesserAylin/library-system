package com.maylin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.maylin.model.Loan;
import com.maylin.model.Member;
@Repository
public interface IMemberRepository extends JpaRepository<Member,Long> {
	
	boolean existsByEmailIgnoreCase(String email);
	
	@Query("SELECT m FROM Member m LEFT JOIN FETCH m.loans l LEFT JOIN FETCH l.bookItem bi LEFT JOIN FETCH  bi.book b where m.id=:id")
	Optional<Member> findMemberWithLoans(@Param("id")Long id);
	
	Optional<Member> findByEmailIgnoreCase(String email);

}
