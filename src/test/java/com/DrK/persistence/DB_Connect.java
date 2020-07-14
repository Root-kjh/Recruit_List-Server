package com.DrK.persistence;

import static org.junit.Assert.assertNotNull;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.DrK.config.root.class, com.DrK.config.DB.class})
@Log4j
public class DB_Connect {

	@Setter(onMethod_ = {@Autowired})
	private MongoTemplate mongoTemplate;
	
	@Setter(onMethod_ = {@Autowired})
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
}