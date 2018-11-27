package com.app.pojo;
// Generated 27 Nov, 2018 4:45:20 PM by Hibernate Tools 5.2.10.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Challan generated by hbm2java
 */
@Entity
@Table(name = "challan", catalog = "bdm")
public class Challan implements java.io.Serializable {

	private Integer id;
	private transient User userByIssuedTo;
	private transient User userByIssuedBy;
	private Date date;
	private Integer totalAmount;
	private Integer receivedAmount;
	private Integer expAmount;
	private String expComment;
	private byte settled;
	private transient Set<CbDetails> cbDetailses = new HashSet<CbDetails>(0);

	public Challan() {
	}

	public Challan(User userByIssuedTo, User userByIssuedBy, Date date, byte settled) {
		this.userByIssuedTo = userByIssuedTo;
		this.userByIssuedBy = userByIssuedBy;
		this.date = date;
		this.settled = settled;
	}

	public Challan(User userByIssuedTo, User userByIssuedBy, Date date, Integer totalAmount, Integer receivedAmount,
			Integer expAmount, String expComment, byte settled, Set<CbDetails> cbDetailses) {
		this.userByIssuedTo = userByIssuedTo;
		this.userByIssuedBy = userByIssuedBy;
		this.date = date;
		this.totalAmount = totalAmount;
		this.receivedAmount = receivedAmount;
		this.expAmount = expAmount;
		this.expComment = expComment;
		this.settled = settled;
		this.cbDetailses = cbDetailses;
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
	@JoinColumn(name = "issued_to", nullable = false)
	public User getUserByIssuedTo() {
		return this.userByIssuedTo;
	}

	public void setUserByIssuedTo(User userByIssuedTo) {
		this.userByIssuedTo = userByIssuedTo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "issued_by", nullable = false)
	public User getUserByIssuedBy() {
		return this.userByIssuedBy;
	}

	public void setUserByIssuedBy(User userByIssuedBy) {
		this.userByIssuedBy = userByIssuedBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date", nullable = false, length = 26)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "total_amount")
	public Integer getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Column(name = "received_amount")
	public Integer getReceivedAmount() {
		return this.receivedAmount;
	}

	public void setReceivedAmount(Integer receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	@Column(name = "exp_amount")
	public Integer getExpAmount() {
		return this.expAmount;
	}

	public void setExpAmount(Integer expAmount) {
		this.expAmount = expAmount;
	}

	@Column(name = "exp_comment", length = 200)
	public String getExpComment() {
		return this.expComment;
	}

	public void setExpComment(String expComment) {
		this.expComment = expComment;
	}

	@Column(name = "settled", nullable = false)
	public byte getSettled() {
		return this.settled;
	}

	public void setSettled(byte settled) {
		this.settled = settled;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "challan")
	public Set<CbDetails> getCbDetailses() {
		return this.cbDetailses;
	}

	public void setCbDetailses(Set<CbDetails> cbDetailses) {
		this.cbDetailses = cbDetailses;
	}

}
