package com.DrK.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.DrK.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	public User findByName(String username);
}
