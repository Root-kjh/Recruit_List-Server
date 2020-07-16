package com.DrK.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.DrK.DTO.CompanyFilterDTO;
import com.DrK.Entities.Company;
import com.DrK.Repositories.CompanyRepository;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Service
@AllArgsConstructor
public class CompanyServicelmpl implements CompanyService{

	@Setter(onMethod_ = {@Autowired})
	private CompanyRepository companyRepo;
	
	@Override
	public List<Company> getAllCompanyList() {
		return companyRepo.findAll();
	}

	@Override
	public List<Company> getCompanyList(int page) {
		return companyRepo.findAll(PageRequest.of(page, 20)).getContent();
	}

	@Override
	public List<Company> getCompanyFilterd(CompanyFilterDTO companyFilterDTO) {
		return null;
	}

	@Override
	public List<Company> companyNameSearch(String companyName, int page) {
		return companyRepo.findBycompanyNameContaining(companyName, PageRequest.of(page, 20)).getContent();
	}


}
