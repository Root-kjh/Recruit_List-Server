package com.DrK.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DrK.Entities.UserLikeCompanyEntity;

public interface UserLikeCompanyRepository extends JpaRepository<UserLikeCompanyEntity, Integer>{

	public void deleteByUserIdxAndCompanyIdx(long userIdx, String companyIdx);
	
	public List<UserLikeCompanyEntity> findByUserIdx(long userIdx);
}
