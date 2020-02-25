package com.DrK.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.DrK.entities.Company;

public interface CompanyRepository extends MongoRepository<Company, String>{
	
	@Query("{'foundingYear':{$lte:?0},'employeesNum':{$gte:?1}}")
	public Page<Company> findByRecruitNoticeNotExsistGen(int year,int empNum,Pageable pageable);

	@Query("{'recruitmentNotices.1':{$exists:true},'foundingYear':{$lte:?0},'employeesNum':{$gte:?1}}")
	public Page<Company> findByRecruitNoticeExsistGen(int year,int empNum,Pageable pageable);

	public Page<Company> findBycompanyNameContaining(String keywork,Pageable pageable);
	
	public List<Company> findByIdIn(List<String> ids);
}
