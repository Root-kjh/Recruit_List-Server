package com.DrK.Predicate;

import com.DrK.Entities.Company;
import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Pageable;

public class CompanyPredicate {
    
    public static Predicate filter(boolean isRecruit, int year,int empNum,Pageable pageable) {
        Company company = new Company();
        return null;
    }
}