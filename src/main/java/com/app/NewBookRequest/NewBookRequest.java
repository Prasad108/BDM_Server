package com.app.NewBookRequest;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "new_book_request", catalog = "bdm")
public class NewBookRequest {
	
	private Integer id;
	private String bookName;
	private String language;
	private String bookType;
	private Integer price;
	private String abbrivation;
	private String user;
	private String remark;
	private String status;
	
	public NewBookRequest() {
		super();
		// TODO Auto-generated constructor stub
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

	@Column(name = "BName", nullable = false)
	public String getBookName() {
		return bookName;
	}


	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	@Column(name = "BLang", nullable = false)
	public String getLanguage() {
		return language;
	}


	public void setLanguage(String bLang) {
		this.language = bLang;
	}

	@Column(name = "Btype", nullable = false)
	public String getBookType() {
		return bookType;
	}


	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	@Column(name = "price", nullable = false)
	public Integer getPrice() {
		return price;
	}


	public void setPrice(Integer price) {
		this.price = price;
	}

	@Column(name = "abbrivation", nullable = false)
	public String getAbbrivation() {
		return abbrivation;
	}


	public void setAbbrivation(String abbrivation) {
		this.abbrivation = abbrivation;
	}

	
	@Column(name = "status", nullable = false)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "user", nullable = false)
	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}

	@Column(name = "remarks", nullable = false)
	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "NewBookRequest [id=" + id + ", bookName=" + bookName + ", bLang=" + language + ", type=" + bookType
				+ ", price=" + price + ", abbrivation=" + abbrivation + ", user=" + user + ", remark=" + remark
				+ ", status=" + status + "]";
	}
}
