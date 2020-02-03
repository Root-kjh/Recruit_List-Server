package com.DrK.repositories;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.DrK.entities.User;

@Repository
public class UserRepository{

	@PersistenceContext // EntityManagerFactory가 DI 할 수 있도록 설정
	private EntityManager em;
	
	public List<User> findAll(){
		String jpql="select u from User u order by u.idx desc";
		TypedQuery<User> query=em.createQuery(jpql,User.class);
		
		return query.getResultList();
	}
	
}
