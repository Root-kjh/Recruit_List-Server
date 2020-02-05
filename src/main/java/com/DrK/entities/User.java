package com.DrK.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import lombok.Data;

@Data
@Entity
@Table(name = "User")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idx;
	
	@Column(name = "id", nullable = false, length = 50)
	private String id;
	
	@Column(name="password", nullable = false, length = 65)
	private String password;
	
	@Column(name="Name", nullable = false, length = 50)
	private String Email;

	@Temporal(TemporalType.DATE)
	private Date SignupDate;
	
	@OneToMany
	@JoinColumn(name = "UserIdx")
	private List<UserPortfolio> UserPortfolios = new ArrayList<UserPortfolio>();
	
	@OneToMany
	@JoinColumn(name = "UserIdx")
	private List<UserLikeCompany> Companies = new ArrayList<UserLikeCompany>();
}
