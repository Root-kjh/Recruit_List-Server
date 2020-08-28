package com.DrK.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.DrK.Config.ServiceInit;
import com.DrK.DTO.CompanyFilterDTO;
import com.DrK.Entities.CompanyEntity;
import com.DrK.Entities.RecruitmentNoticeEntity;
import com.DrK.lib.TestLib;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;

import lombok.extern.log4j.Log4j;

@Log4j
public class CompanyServiceTest extends ServiceInit{
	
	@Before
	public void setCompanies() throws Exception {
		for(int i=0;i<40;i++){
			this.makeTestCompany(TestLib.testCompany.companyName+Integer.toString(i));
		}
	}

	@Test
	public void getCompanyTest() {
		List<CompanyEntity> companies = this.companyService.getCompanyList(1);
		assertEquals(companies.get(0).getCompanyName(),TestLib.testCompany.companyName+"20");
		assertEquals(companies.get(19).getCompanyName(),TestLib.testCompany.companyName+"39");
	}

	@Test
	public void companyFilterTest(){
		final int filterEmployeesNum=250;
		final int filterFoundingYear=2000;
		final String filterCompanyName = "filterTest";
		CompanyEntity companyEntity = new CompanyEntity();
		companyEntity.setCompanyName(filterCompanyName);
		companyEntity.setEmployeesNum(filterEmployeesNum);
		companyEntity.setFoundingYear(filterFoundingYear);
		this.companyRepository.save(companyEntity);

		CompanyFilterDTO companyFilterDTO = new CompanyFilterDTO();
		companyFilterDTO.setEmployeesNum(filterEmployeesNum);
		companyFilterDTO.setFoundingYear(filterFoundingYear);
		companyFilterDTO.setRecruting(false);
		companyFilterDTO.setPage(PageRequest.of(0, 20));

		List<CompanyEntity> filterCompanyEntities =
			this.companyService.getCompanyFilterd(companyFilterDTO);
		assertEquals(filterCompanyEntities.get(0).getCompanyName(), filterCompanyName);

		final String recruitingCompanyName = "recruitingCompany";

		CompanyEntity recruitingCompanyEntity = new CompanyEntity();
		recruitingCompanyEntity.setCompanyName(recruitingCompanyName);
		recruitingCompanyEntity.setEmployeesNum(TestLib.testCompany.employeesNum);
		recruitingCompanyEntity.setFoundingYear(TestLib.testCompany.foundingYear);
		List<RecruitmentNoticeEntity> recruitmentNotices = new ArrayList<>();
		RecruitmentNoticeEntity recruitmentNotice = new RecruitmentNoticeEntity();
		recruitmentNotice.setSiteName("testSite");
		recruitmentNotice.setUri("www.test.com");
		recruitmentNotices.add(recruitmentNotice);
		recruitingCompanyEntity.setRecruitmentNotice(recruitmentNotices);
		this.companyRepository.save(recruitingCompanyEntity);

		companyFilterDTO = new CompanyFilterDTO();
		companyFilterDTO.setRecruting(true);
		companyFilterDTO.setPage(PageRequest.of(0,20));

		filterCompanyEntities = this.companyService.getCompanyFilterd(companyFilterDTO);
		log.info(filterCompanyEntities.get(0).getRecruitmentNotice());
		assertEquals(filterCompanyEntities.get(0).getCompanyName(), recruitingCompanyName);
	}

	@Test
	public void companySearchTest(){
		final String searchTestCompanyName = "searchTest";
		CompanyEntity companyEntity = new CompanyEntity();
		companyEntity.setCompanyName(searchTestCompanyName);
		companyEntity.setEmployeesNum(TestLib.testCompany.employeesNum);
		companyEntity.setFoundingYear(TestLib.testCompany.foundingYear);
		this.companyRepository.save(companyEntity);

		List<CompanyEntity> companyEntities =
			this.companyService.companyNameSearch(searchTestCompanyName, 0);
		assertEquals(companyEntities.get(0).getCompanyName(), searchTestCompanyName);
	}
}
