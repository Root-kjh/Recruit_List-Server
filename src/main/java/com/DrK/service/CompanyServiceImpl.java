package com.DrK.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DrK.DTO.CompanyFilterDTO;
import com.DrK.DTO.CompanyInfoDTO;
import com.DrK.Entities.CompanyEntity;
import com.DrK.lib.EntityToInfoDTO;
import com.DrK.repositories.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	private CompanyRepository companyRepo;

	@Override
	@Transactional(readOnly = true)
	public List<CompanyInfoDTO> getCompanyList(int page) {
		List<CompanyInfoDTO> companyInfoDTOs = new ArrayList<CompanyInfoDTO>();
		for (CompanyEntity company : companyRepo.findAll(PageRequest.of(page, 20)).getContent()) {
			companyInfoDTOs.add(EntityToInfoDTO.companyEntityToCompanyInfoDTO(company));
		}
		return companyInfoDTOs;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CompanyInfoDTO> getCompanyFilterd(CompanyFilterDTO companyFilterDTO) {
		List<CompanyInfoDTO> companyInfoDTOs = new ArrayList<CompanyInfoDTO>();
		for (CompanyEntity company : companyRepo.findFilteredCompany(companyFilterDTO)) {
			companyInfoDTOs.add(EntityToInfoDTO.companyEntityToCompanyInfoDTO(company));
		}
		return companyInfoDTOs;
	}

	@Override
	@Transactional(readOnly = true)
	public List<CompanyInfoDTO> companyNameSearch(String companyName, int page) {
		List<CompanyInfoDTO> companyInfoDTOs = new ArrayList<CompanyInfoDTO>();
		for (CompanyEntity company : companyRepo.findBycompanyNameContaining(companyName, PageRequest.of(page, 20)).getContent()) {
			companyInfoDTOs.add(EntityToInfoDTO.companyEntityToCompanyInfoDTO(company));
		}
		return companyInfoDTOs;
	}


}
