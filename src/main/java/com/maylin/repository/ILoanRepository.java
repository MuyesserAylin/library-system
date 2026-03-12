package com.maylin.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
			+ "l.dueDate<:today and l.returnDate IS NULL")
	int hasOverDueLoansByMemberId(@Param("memberId")Long memberId,@Param("today")LocalDate today);

	
	@Query("SELECT l FROM Loan l "
			+ "LEFT JOIN FETCH l.member "
			+ "LEFT JOIN FETCH l.bookItem bi LEFT JOIN FETCH bi.book "
			+ "WHERE l.id=:loanId")
	Optional<Loan> findByIdWithDetails(@Param("loanId")Long loandId);
	
	
	@Query("SELECT l FROM Loan l "
			+ "LEFT JOIN FETCH l.bookItem bi "
			+ "LEFT JOIN FETCH bi.book b "
			+ "WHERE l.id=:loanId")
	Optional<Loan> findByIdWithBookItem(@Param("loanId")Long loanId);
	
	
	@Query("SELECT l FROM Loan l "
			+ "LEFT JOIN FETCH l.bookItem bi "
			+ "LEFT JOIN FETCH bi.book b "
			+ "WHERE l.dueDate<:today and l.returnDate IS NULL")
	List<Loan> findOverDueLoans(@Param("today")LocalDate today);
	
	
	
}
