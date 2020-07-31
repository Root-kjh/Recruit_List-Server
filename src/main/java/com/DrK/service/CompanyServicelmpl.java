package com.DrK.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.DrK.DTO.CompanyFilterDTO;
import com.DrK.Entities.CompanyEntity;
import com.DrK.Repositories.CompanyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyServicelmpl implements CompanyService{

	private CompanyRepository companyRepo;

	@Override
	public List<CompanyEntity> getCompanyList(int page) {
		return companyRepo.findAll(PageRequest.of(page, 20)).getContent();
	}

	@Override
	public List<CompanyEntity> getCompanyFilterd(CompanyFilterDTO companyFilterDTO) {
		return null;
	}

	@Override
	public List<CompanyEntity> companyNameSearch(String companyName, int page) {
		return companyRepo.findBycompanyNameContaining(companyName, PageRequest.of(page, 20)).getContent();
	}


}
