package com.DrK.persistence;

import static org.junit.Assert.assertNotNull;

import javax.sql.DataSource;

import com.DrK.DTO.CompanyFilterDTO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.DrK.Config.Root.class, com.DrK.Config.DB.class})
@WebAppConfiguration
@Log4j
public class DB_Connect {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private DataSource MySQLConnector;
	
	@Test
	public void test_connection() {
		log.info(mongoTemplate);
		assertNotNull(mongoTemplate);
	}
	
	@Test
	public void MySQLConnectTest() {
		log.info(MySQLConnector);
		assertNotNull(MySQLConnector);
	}

	@Test
	public void test(){
		CompanyFilterDTO companyFilterDTO = new CompanyFilterDTO();
		companyFilterDTO.setRecruting(true);
		log.info(companyFilterDTO.getEmployeesNum());
	}
}