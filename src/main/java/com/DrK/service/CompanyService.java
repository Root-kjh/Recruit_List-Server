package com.DrK.Service;

import java.util.List;

import com.DrK.DTO.CompanyFilterDTO;
import com.DrK.Entities.Company;

public interface CompanyService {

	public List<Company> getAllCompanyList();
	
	public List<Company> getCompanyList(int page);

	public List<Company> getCompanyFilterd(CompanyFilterDTO companyFilterDTO);
	
	public List<Company> companyNameSearch(String companyName, int page);
}
