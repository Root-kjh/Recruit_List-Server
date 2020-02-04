package com.DrK.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.DrK.entities.Company;
import com.DrK.repositories.CompanyRepository;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Service
@AllArgsConstructor
public class CompanyServicelmpl implements CompanyService{

	@Setter(onMethod_ = {@Autowired})
	private MongoTemplate mongoTemplate;
	
	@Override
	public List<Company> getList() {
		return mongoTemplate.findAll(Company.class,"company");
	}

}
