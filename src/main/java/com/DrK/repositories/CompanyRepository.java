package com.DrK.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.DrK.entities.Company;

public interface CompanyRepository extends MongoRepository<Company, String>{
	
	@Query("{'recruitmentNotices.1':{$exists:true}}")
	public List<Company> findByRecruitmentNoticesNotNullQuery();
	
	@Query("{'recruitmentNotices.1':{$exists:true}}")
	public Page<Company> findByRecruitmentNoticesNotNullQuery(Pageable pageable);
}
