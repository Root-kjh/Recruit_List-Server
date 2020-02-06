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
import com.DrK.entities.CompanyInfos;
import com.DrK.entities.RecruitmentNotices;

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
		for (int i = 0; i < 40; i++) {
			Company company=new Company();
			company.setEmployeesNum(50);
			company.setCompanyName("test"+Integer.toString(i));
			company.setFoundingYear("2020");
			
			List<RecruitmentNotices> recruitmentNotices=new ArrayList<RecruitmentNotices>();
			List<CompanyInfos> CompanyInfos=new ArrayList<CompanyInfos>();
			CompanyInfos companyInfo=new CompanyInfos();
			companyInfo.setSiteName("test");
			companyInfo.setUri("localhost");
			
			RecruitmentNotices recruitmentNotice=new RecruitmentNotices();
			recruitmentNotice.setSiteName("test");
			recruitmentNotice.setUri("localhost");
			RecruitmentNotices recruitmentNotice2=new RecruitmentNotices();
			recruitmentNotice2.setSiteName("test2");
			recruitmentNotice2.setUri("localhost2");

			recruitmentNotices.add(recruitmentNotice);
			recruitmentNotices.add(recruitmentNotice2);
			CompanyInfos.add(companyInfo);
			
			company.setCompanyInfos(CompanyInfos);
			company.setRecruitmentNotices(recruitmentNotices);
			
			companyRepository.insert(company);
		}
	}
}
