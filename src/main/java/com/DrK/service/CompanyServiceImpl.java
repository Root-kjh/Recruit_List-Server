package com.DrK.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.DrK.DTO.CompanyFilterDTO;
import com.DrK.Entities.CompanyEntity;
import com.DrK.Repositories.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	private CompanyRepository companyRepo;

	@Override
	public List<CompanyEntity> getCompanyList(int page) {
		return companyRepo.findAll(PageRequest.of(page, 20)).getContent();
	}

	@Override
	public List<CompanyEntity> getCompanyFilterd(CompanyFilterDTO companyFilterDTO) {
		return companyRepo.findFilteredCompany(companyFilterDTO);
	}

	@Override
	public List<CompanyEntity> companyNameSearch(String companyName, int page) {
		return companyRepo.findBycompanyNameContaining(companyName, PageRequest.of(page, 20)).getContent();
	}


}
