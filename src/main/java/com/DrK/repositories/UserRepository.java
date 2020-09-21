package com.DrK.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.DrK.Entities.CompanyEntity;
import com.DrK.Entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer>{
	public UserEntity findByName(String username);
	
	public List<CompanyEntity> findCompaniesByName(String username);

	@Query("select count(u)>0 from User u where u.name = ?1")
	public boolean isExistUser(String username);
}
