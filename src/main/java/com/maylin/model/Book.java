package com.maylin.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="title",nullable=false)
	private String title;
	
	@Column(name="ISBN",unique=true,nullable=false)
	private String ISBN;
	
	@ManyToOne
	@JoinColumn(name="author_id",referencedColumnName = "id")
	private Author author;
	
	@OneToMany(mappedBy="book",cascade=CascadeType.REMOVE,orphanRemoval =true)
    private List<BookItem> bookItems=new ArrayList<>();	
	
	@ManyToMany
    @JoinTable(name = "book_category",
        joinColumns = {@JoinColumn(name = "book_id")},
        inverseJoinColumns = {@JoinColumn(name = "category_id")})
        private List<Category> categories=new ArrayList<>();

}
