package com.DrK.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name = "UserLikeCompany")
public class UserLikeCompany {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idx;

	@Column(name = "userIdx",nullable = false,length = 11)
	private long userIdx;

	@Column(name = "companyIdx",nullable = false,length = 50)
	private String companyIdx;
	
}
	