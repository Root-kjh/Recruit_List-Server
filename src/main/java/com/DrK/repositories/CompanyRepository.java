package com.DrK.Repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.DrK.Entities.CompanyEntity;

public interface CompanyRepository extends MongoRepository<CompanyEntity, String>, CompanyRepositoryCustom{

	public Page<CompanyEntity> findBycompanyNameContaining(String keywork,Pageable pageable);
	
	public List<CompanyEntity> findByIdIn(List<String> ids);
}