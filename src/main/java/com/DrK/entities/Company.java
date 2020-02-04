package com.DrK.entities;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "company")
@Data
public class Company {
	
	@Id
	private String _id;
	
	private String CompanyName;
}
