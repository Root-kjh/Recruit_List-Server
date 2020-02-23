package com.DrK.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.DrK.config.root.class, com.DrK.config.DB.class})
@Log4j
public class pwEncoder {
	
	@Test
	public void pwEncoder() {
		BCryptPasswordEncoder bcr=new BCryptPasswordEncoder();
		log.info("1234 == "+bcr.encode("1234"));
	}
}
