package com.DrK.service;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.DrK.domain.CompanyVO;
import com.DrK.persistence.DB_Connect;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class CompanyServiceTest {

	@Setter(onMethod_ = {@Autowired})
	private  CompanyService companyService;
	
	@Test
	public void getListTest() {
		for (CompanyVO companyVO : companyService.getList()) {
			log.info(companyVO);
		}
	}
}
