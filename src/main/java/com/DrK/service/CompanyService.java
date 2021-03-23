package com.DrK.service;

import java.util.List;

import com.DrK.DTO.CompanyFilterDTO;
import com.DrK.DTO.CompanyInfoDTO;

public interface CompanyService {
	public List<CompanyInfoDTO> getCompanyList(int page);

	public List<CompanyInfoDTO> getCompanyFilterd(CompanyFilterDTO companyFilterDTO);
	
	public List<CompanyInfoDTO> companyNameSearch(String companyName, int page);
}
