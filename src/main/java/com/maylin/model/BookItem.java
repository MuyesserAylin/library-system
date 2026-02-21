package com.maylin.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.maylin.enums.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="bookitem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookItem {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="barcode",unique=true,nullable=false)
	private String barcode;
	
	
	@Column(name="status")
	@Enumerated(EnumType.STRING)
	private Status status=Status.AVAILABLE;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="book_id",referencedColumnName = "id",nullable=false)
	private Book book;
	
	@OneToMany(mappedBy="bookItem")
	private List<Loan> loans=new ArrayList<>();
	
	

}
