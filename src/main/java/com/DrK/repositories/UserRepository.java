package com.DrK.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DrK.Entities.Company;
import com.DrK.Entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	public User findByName(String username);
	
	public List<Company> findCompaniesByName(String username);
}
