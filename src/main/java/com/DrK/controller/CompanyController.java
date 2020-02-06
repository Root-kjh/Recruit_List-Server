package com.DrK.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
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
@Log4j
public class CompanyController {

	@Setter(onMethod_ = {@Autowired})
	private  CompanyService companyService;
	
	@RequestMapping(path ="/paging/",method = RequestMethod.GET )
	public List<Company> list() {
		return companyService.getList();
	}
	
	@RequestMapping(path = "/is-recruit")
	public List<Company> RecruitCompany(){
		return companyService.RecruitCompany();
	}
	
	@RequestMapping(path ="/paging/{page}",method = RequestMethod.GET )
	public List<Company> Paging(@PathVariable("page") int page) {
		return companyService.getList(PageRequest.of(page, 20));
	}
	
	@RequestMapping(path = "/is-recruit/paging/{page}")
	public List<Company> RecruitCompanyPaging(@PathVariable("page") int page){
		return companyService.RecruitCompany(PageRequest.of(page, 20));
	}
}
