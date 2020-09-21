package com.DrK.repositories;

import java.util.List;

import com.DrK.DTO.CompanyFilterDTO;
import com.DrK.Entities.CompanyEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CompanyRepositoryCustomImpl implements CompanyRepositoryCustom {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<CompanyEntity> findFilteredCompany(CompanyFilterDTO companyFilterDTO) {
        Query query = new Query();

        if(companyFilterDTO.getEmployeesNum()!=0){
            query.addCriteria(Criteria.where("employeesNum").gte(companyFilterDTO.getEmployeesNum()));
        }
        if(companyFilterDTO.getFoundingYear()!=0){
            query.addCriteria(Criteria.where("foundingYear").gte(companyFilterDTO.getFoundingYear()));
        }
        if(companyFilterDTO.isRecruting()){
            query.addCriteria(Criteria.where("recruitmentNotice").exists(true));
        }
        query.with(companyFilterDTO.getPage());
        List<CompanyEntity> companyResult = mongoTemplate.find(query, CompanyEntity.class);
        return companyResult;
    }
    
}