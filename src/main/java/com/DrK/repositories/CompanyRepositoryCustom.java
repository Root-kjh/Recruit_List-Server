package com.DrK.Repositories;

import java.util.List;

import com.DrK.DTO.CompanyFilterDTO;
import com.DrK.Entities.CompanyEntity;

public interface CompanyRepositoryCustom {
    public List<CompanyEntity> findFilteredCompany(CompanyFilterDTO companyFilterDTO); 
}