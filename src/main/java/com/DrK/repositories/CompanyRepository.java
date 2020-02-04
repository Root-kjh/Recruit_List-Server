package com.DrK.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.DrK.entities.Company;

public interface CompanyRepository extends MongoRepository<Company, String>{
}
