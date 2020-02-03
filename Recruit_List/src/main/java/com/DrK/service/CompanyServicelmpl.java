package com.DrK.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.DrK.domain.CompanyVO;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Service
@AllArgsConstructor
public class CompanyServicelmpl implements CompanyService{

	@Setter(onMethod_ = {@Autowired})
	private MongoTemplate MongoTemplate;
	
	@Override
	public List<CompanyVO> getList() {
		return MongoTemplate.findAll(CompanyVO.class, "company_list");
	}

}
