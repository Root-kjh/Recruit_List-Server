package com.DrK.entities;

import java.util.List;

import javax.persistence.Id;

import lombok.Data;

@Data
public class Company {
	
	@Id
	private String id;
	
	private String companyName;
	private String foundingYear;
	private int employeesNum;
	private List<CompanyInfos> companyInfos;
	private List<RecruitmentNotices> recruitmentNotices;
}