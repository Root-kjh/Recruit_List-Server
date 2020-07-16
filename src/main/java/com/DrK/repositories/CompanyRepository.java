package com.DrK.Repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.DrK.Entities.Company;

public interface CompanyRepository extends MongoRepository<Company, String>, QuerydslPredicateExecutor<Company>{

	public Page<Company> findBycompanyNameContaining(String keywork,Pageable pageable);
	
	public List<Company> findByIdIn(List<String> ids);
}
