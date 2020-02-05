package com.DrK.repositories;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.DrK.entities.Company;
import com.DrK.entities.CompanyInfo;
import com.DrK.entities.RecruitmentNotice;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class CompanyListInsertTest {

	@Setter(onMethod_ = {@Autowired})
	private CompanyRepository companyRepository;
	
	@Test
	public void InsertTest() {
		Company company=new Company();
		company.setEmployeesNum(50);
		company.setCompanyName("test");
		company.setFoundingYear("2020");
		List<CompanyInfo> companyInfos=new ArrayList<CompanyInfo>();
		List<RecruitmentNotice> recruitmentNotices=new ArrayList<RecruitmentNotice>();
		CompanyInfo companyInfo=new CompanyInfo();
		companyInfo.setSiteName("test");
		companyInfo.setUri("localhost");
		companyInfos.add(companyInfo);
		
		RecruitmentNotice recruitmentNotice=new RecruitmentNotice();
		recruitmentNotice.setSiteName("test");
		recruitmentNotice.setUri("localhost");
		RecruitmentNotice recruitmentNotice2=new RecruitmentNotice();
		recruitmentNotice2.setSiteName("test2");
		recruitmentNotice2.setUri("localhost2");
		recruitmentNotices.add(recruitmentNotice);
		recruitmentNotices.add(recruitmentNotice2);
		
		company.setCompanyInfos(companyInfos);
		company.setRecruitmentNotices(recruitmentNotices);
	}
}
