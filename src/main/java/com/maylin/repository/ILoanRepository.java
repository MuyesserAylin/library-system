package com.maylin.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.maylin.model.Loan;
@Repository
public interface ILoanRepository  extends JpaRepository<Loan,Long>{
	
	@Query("SELECT count(l) FROM Loan l WHERE l.member.id=:memberId and l.returnDate IS NULL")
	int countActiveLoansByMemberId(@Param("memberId")Long memberId);
	
	@Query("SELECT count(l) FROM Loan l WHERE l.member.id=:memberId and "
			+ "l.dueDate<:today and returnDate IS NULL")
	int hasOverDueLoansByMemberId(@Param("memberId")Long memberId,@Param("today")LocalDate today);

}
