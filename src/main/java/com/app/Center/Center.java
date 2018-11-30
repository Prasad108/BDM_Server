package com.app.Center;
// Generated 29 Nov, 2018 1:33:50 PM by Hibernate Tools 5.2.10.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.app.Inventry.Inventry;
import com.app.User.User;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Center generated by hbm2java
 */
@Entity
@Table(name = "center", catalog = "bdm")
public class Center implements java.io.Serializable {

	private Integer id;
	@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id",scope = Inventry.class)
	@JsonIdentityReference(alwaysAsId=true)
	private Inventry inventry;
	private String name;
	private String location;
	private String pm;
	private String pocDetails;
	@JsonIgnore
	private Set<User> users = new HashSet<User>(0);

	public Center() {
	}

	public Center(String name) {
		this.name = name;
	}

	public Center(Inventry inventry, String name, String location, String pm, String pocDetails, Set<User> users) {
		this.inventry = inventry;
		this.name = name;
		this.location = location;
		this.pm = pm;
		this.pocDetails = pocDetails;
		this.users = users;
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
	@JoinColumn(name = "inventry")
	public Inventry getInventry() {
		return this.inventry;
	}

	public void setInventry(Inventry inventry) {
		this.inventry = inventry;
	}

	@Column(name = "name", nullable = false, length = 200)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "location", length = 500)
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "PM", length = 500)
	public String getPm() {
		return this.pm;
	}

	public void setPm(String pm) {
		this.pm = pm;
	}

	@Column(name = "POC_Details", length = 500)
	public String getPocDetails() {
		return this.pocDetails;
	}

	public void setPocDetails(String pocDetails) {
		this.pocDetails = pocDetails;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "center")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
