package com.DrK.service;

import java.util.List;

import com.DrK.Config.ServiceTest;
import com.DrK.Entities.CompanyEntity;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

@Log4j
public class CompanyServiceTest extends ServiceTest{
	
	@Test
	public void getCompanyTest() {
		
		for(int i=0;i<40;i++){
			this.makeTestCompany(testlib)
		}
		List<CompanyEntity> companies = this.companyService.getAllCompanyList();
		
	}

	@Test
	public void companyFilterTest(){

	}

	@Test
	public void companySearchTest(){

	}
}
