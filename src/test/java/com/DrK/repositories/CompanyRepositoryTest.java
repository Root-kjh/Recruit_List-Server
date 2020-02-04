package com.DrK.repositories;

import org.bson.types.ObjectId;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.DrK.entities.Company;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class CompanyRepositoryTest {

	@Setter(onMethod_ = {@Autowired})
	private CompanyRepository companyRepository;
	
	@Test
	public void getAllList() {
		for (Company company: companyRepository.findAll()) {
			log.info(company.getCompanyName());
		}
	}
}
