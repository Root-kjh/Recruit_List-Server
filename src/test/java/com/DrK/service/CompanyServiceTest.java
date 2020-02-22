package com.DrK.service;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.DrK.entities.Company;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.DrK.config.root.class, com.DrK.config.DB.class})
@Log4j
public class CompanyServiceTest {

	@Setter(onMethod_ = {@Autowired})
	private  CompanyService companyService;
	
//	@Test
//	public void getListTest() {
//		for (Company companyVO : companyService.getList()) {
//			log.info(companyVO);
//		}
//	}
	
	@Test
	public void CompanyPagingTest() {
		for (Company company: companyService.getList(PageRequest.of(0, 20))) {
			log.info(company.getCompanyName());
		}
	}
}
