package com.app.pojo;
// Generated 29 Nov, 2018 1:33:50 PM by Hibernate Tools 5.2.10.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.app.Book.Book;
import com.app.Challan.Challan;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * CbDetails generated by hbm2java
 */
@Entity
@Table(name = "cb_details", catalog = "bdm")
public class CbDetails implements java.io.Serializable {

	private Integer id;
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
	@JsonIdentityReference(alwaysAsId=true)
	private Book book;
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
	@JsonIdentityReference(alwaysAsId=true)
	private Challan challan;
	private int rate;
	private int quantity;
	private Integer returned;
	private int saleValue;

	public CbDetails() {
	}

	public CbDetails(Book book, Challan challan, int rate, int quantity, int saleValue) {
		this.book = book;
		this.challan = challan;
		this.rate = rate;
		this.quantity = quantity;
		this.saleValue = saleValue;
	}

	public CbDetails(Book book, Challan challan, int rate, int quantity, Integer returned, int saleValue) {
		this.book = book;
		this.challan = challan;
		this.rate = rate;
		this.quantity = quantity;
		this.returned = returned;
		this.saleValue = saleValue;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book", nullable = false)
	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "challan", nullable = false)
	public Challan getChallan() {
		return this.challan;
	}

	public void setChallan(Challan challan) {
		this.challan = challan;
	}

	@Column(name = "rate", nullable = false)
	public int getRate() {
		return this.rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	@Column(name = "quantity", nullable = false)
	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "returned")
	public Integer getReturned() {
		return this.returned;
	}

	public void setReturned(Integer returned) {
		this.returned = returned;
	}

	@Column(name = "sale_value", nullable = false)
	public int getSaleValue() {
		return this.saleValue;
	}

	public void setSaleValue(int saleValue) {
		this.saleValue = saleValue;
	}

}