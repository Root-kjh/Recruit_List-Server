package com.DrK.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.DrK.entities.User;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class UserRepositoryTest {

	@Setter(onMethod_ = @Autowired)
	private UserRepository userRepository;
	
	@Test
	public void getListTest() {
		for (User user: userRepository.findAll()) {
			log.info(user.getId());
		}
	}
}
