package com.DrK.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.DrK.DTO.CompanyFilterDTO;
import com.DrK.DTO.CompanyInfoDTO;
import com.DrK.service.CompanyService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/company")
@CrossOrigin
@AllArgsConstructor
public class CompanyController {

	private final CompanyService companyService;
		
	@GetMapping("/page/{page}")
	public List<CompanyInfoDTO> Paging(@PathVariable int page) {
		return companyService.getCompanyList(page);
	}
	
	@PostMapping("/filter")
	public List<CompanyInfoDTO> RecruitCompany(@RequestBody CompanyFilterDTO companyFilterDTO){
		return companyService.getCompanyFilterd(companyFilterDTO);
	}

	@GetMapping("/companyName/{companyName}/page/{page}")
	public List<CompanyInfoDTO> searchByCompanyName(
			@PathVariable String companyName,
			@PathVariable int page){
		return companyService.companyNameSearch(companyName, page);
	}
}
