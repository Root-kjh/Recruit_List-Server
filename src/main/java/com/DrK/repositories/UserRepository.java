package com.DrK.repositories;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.DrK.domain.UserVO;

@Repository
public class UserRepository{

	@PersistenceContext // EntityManagerFactory가 DI 할 수 있도록 설정
	private EntityManager em;
	
	public List<UserVO> findAll(){
		String jpql="select id from User order by idx desc";
		TypedQuery<UserVO> query=em.createQuery(jpql,UserVO.class);
		
		return query.getResultList();
	}
}
