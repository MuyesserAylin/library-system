package com.maylin.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="loan")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Loan {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="loan_date")
	private LocalDate loanDate;
	
	@Column(name="due_date")
	private LocalDate dueDate;
	
	@Column(name="return_date")
	private LocalDate returnDate;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="member_id",nullable=false)
	private Member member;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="book_item_id",nullable=false)
	private BookItem bookItem;
	

}
