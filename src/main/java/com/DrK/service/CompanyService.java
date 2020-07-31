package com.DrK.Service;

import java.util.List;

import com.DrK.DTO.CompanyFilterDTO;
import com.DrK.Entities.CompanyEntity;

public interface CompanyService {
	public List<CompanyEntity> getCompanyList(int page);

	public List<CompanyEntity> getCompanyFilterd(CompanyFilterDTO companyFilterDTO);
	
	public List<CompanyEntity> companyNameSearch(String companyName, int page);
}
