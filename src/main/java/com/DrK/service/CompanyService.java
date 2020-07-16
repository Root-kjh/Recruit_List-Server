package com.DrK.Service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.DrK.Entities.Company;

public interface CompanyService {

	public List<Company> getAllCompanyList();
	
	public List<Company> getCompanyList(int page);

	public List<Company> getCompanyFilterd(boolean isRecruit, int year,int empNum, int page);
	
	public List<Company> companyNameSearch(String companyName, int page);
}
