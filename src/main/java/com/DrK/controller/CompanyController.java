package com.DrK.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.DrK.config.UrlConfig;
import com.DrK.DTO.CompanyFilterDTO;
import com.DrK.Entities.CompanyEntity;
import com.DrK.service.CompanyService;

import lombok.Setter;

@RestController
public class CompanyController {

	@Setter(onMethod_ = {@Autowired})
	private  CompanyService companyService;
		
	@RequestMapping(path = UrlConfig.Company.show ,method = RequestMethod.GET )
	public List<CompanyEntity> Paging(@PathVariable int page) {
		return companyService.getCompanyList(page);
	}
	
	@RequestMapping(path = UrlConfig.Company.filterCompany ,method = RequestMethod.GET)
	public List<CompanyEntity> RecruitCompany(@RequestBody CompanyFilterDTO companyFilterDTO){
		return companyService.getCompanyFilterd(companyFilterDTO);
	}

	@RequestMapping(path = UrlConfig.Company.searchCompany ,method = RequestMethod.GET)
	public List<CompanyEntity> searchByCompanyName(
			@PathVariable String companyName,
			@PathVariable int page){
		return companyService.companyNameSearch(companyName, page);
	}
}
