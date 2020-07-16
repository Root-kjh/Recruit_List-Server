package com.DrK.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.DrK.Config.UrlConfig;
import com.DrK.DTO.CompanyFilterDTO;
import com.DrK.Entities.Company;
import com.DrK.Service.CompanyService;

import lombok.Setter;

@RestController
@RequestMapping("/company")
@CrossOrigin("*")
public class CompanyController {

	@Setter(onMethod_ = {@Autowired})
	private  CompanyService companyService;
		
	@RequestMapping(path =UrlConfig.Company.show ,method = RequestMethod.GET )
	public List<Company> Paging(@PathVariable("page") int page) {
		return companyService.getList(PageRequest.of(page, 20));
	}
	
	@RequestMapping(path =UrlConfig.Company.filterCompany ,method = RequestMethod.GET)
	public List<Company> RecruitCompany(@RequestBody CompanyFilterDTO companyFilterDTO){
		return companyService.getCompanyFilterd(
			companyFilterDTO.isRecruting(), 
			companyFilterDTO.getFoundingYear(), 
			companyFilterDTO.getEmployeesNum(), 
			companyFilterDTO.getPage());
	}

	@RequestMapping(path = UrlConfig.Company.searchCompany ,method = RequestMethod.GET)
	public List<Company> searchByCompanyName(@PathVariable("companyName")String companyName,
			@PathVariable("page")int page){
		return companyService.CompanyNameSearch(companyName, PageRequest.of(page, 20));
	}
	
	@RequestMapping(path="/test", method = RequestMethod.POST)
	public String test() {
		return "Test";
	}
}
