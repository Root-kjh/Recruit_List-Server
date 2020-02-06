package com.DrK.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.DrK.entities.Company;
import com.DrK.repositories.CompanyRepository;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Service
@AllArgsConstructor
public class CompanyServicelmpl implements CompanyService{

	@Setter(onMethod_ = {@Autowired})
	private CompanyRepository companyRepo;
	
	@Override
	public List<Company> getList() {
		return companyRepo.findAll();
	}

	@Override
	public List<Company> getList(Pageable pageable) {
		return companyRepo.findAll(pageable).getContent();
	}

	@Override
	public List<Company> RecruitCompanyGen(int year, int empNum, Pageable pageable) {
		return companyRepo.findByRecruitNoticeExsistGen(year, empNum, pageable).getContent();
	}

	@Override
	public List<Company> NotRecruitCompanyGen(int year, int empNum, Pageable pageable) {
		return companyRepo.findByRecruitNoticeNotExsistGen(year, empNum, pageable).getContent();
	}

	@Override
	public List<Company> CompanyNameSearch(String companyName, Pageable pageable) {
		return companyRepo.findBycompanyNameContaining(companyName, pageable).getContent();
	}


}
