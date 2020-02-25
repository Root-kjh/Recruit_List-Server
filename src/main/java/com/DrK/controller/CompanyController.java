package com.DrK.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.DrK.entities.Company;
import com.DrK.service.CompanyService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/company")
@CrossOrigin("*")
@Log4j
public class CompanyController {

	@Setter(onMethod_ = {@Autowired})
	private  CompanyService companyService;
	
	@RequestMapping(path ="/",method = RequestMethod.GET )
	public List<Company> list() {
		return companyService.getList();
	}
	
	@RequestMapping(path ="/page/{page}",method = RequestMethod.GET )
	public List<Company> Paging(@PathVariable("page") int page) {
		return companyService.getList(PageRequest.of(page, 20));
	}
	
	@RequestMapping(path = "/is-recruit/{isRecruitFlag}/employeesnum-min/{employeesNum}/foundingyear-max/{foundingYear}/page/{page}",method = RequestMethod.GET)
	public List<Company> RecruitCompany(@PathVariable("isRecruitFlag")boolean isRecruitFlag,
			@PathVariable("employeesNum")int empNum,
			@PathVariable("foundingYear")int year,
			@PathVariable("page")int page){
		if(isRecruitFlag) {
			return companyService.RecruitCompanyGen(year, empNum, PageRequest.of(page, 20));
		}else {
			return companyService.NotRecruitCompanyGen(year, empNum, PageRequest.of(page, 20));
		}
	}

	@RequestMapping(path = "/search/companyname/{companyName}/page/{page}",method = RequestMethod.GET)
	public List<Company> searchByCompanyName(@PathVariable("companyName")String companyName,
			@PathVariable("page")int page){
		return companyService.CompanyNameSearch(companyName, PageRequest.of(page, 20));
	}
	
	@RequestMapping(path="/test", method = RequestMethod.POST)
	public String test() {
		return "Test";
	}
}
