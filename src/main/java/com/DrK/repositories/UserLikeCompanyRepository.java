package com.DrK.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DrK.Entities.UserLikeCompany;

public interface UserLikeCompanyRepository extends JpaRepository<UserLikeCompany, Integer>{

	public void deleteByUserIdxAndCompanyIdx(long userIdx, String companyIdx);
	
	public List<UserLikeCompany> findByUserIdx(long userIdx);
}
