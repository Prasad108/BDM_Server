package com.app.BookName;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.app.Book.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "book_name", catalog = "bdm")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BookName {
	private Integer id;
	private String name;
	private String abbreviation;
	@JsonIgnore
	private Set<Book> books = new HashSet<Book>(0);
	
	

	public BookName() {
		super();
	}



	public BookName(String name, String abbreviation) {
		super();
		this.name = name;
		this.abbreviation = abbreviation;
	}
	
	

	public BookName(Integer id, String name, String abbreviation, Set<Book> books) {
		super();
		this.id = id;
		this.name = name;
		this.abbreviation = abbreviation;
		this.books = books;
	}



	public BookName(String name, String abbreviation, Set<Book> books) {
		super();
		this.name = name;
		this.abbreviation = abbreviation;
		this.books = books;
	}



	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "abbreviation", length = 45)
	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	
	

}
