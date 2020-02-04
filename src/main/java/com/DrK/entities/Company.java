package com.DrK.entities;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
public class Company {
	
	@Id
	private String id;
	
	private String CompanyName;
}
