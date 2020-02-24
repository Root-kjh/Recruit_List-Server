package com.DrK.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.DrK.entities.Company;
import com.DrK.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	public User findByName(String username);
	
	public List<Company> findCompaniesByName(String username);
}
