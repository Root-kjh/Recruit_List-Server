package com.DrK.Entities;

import java.util.List;

import javax.persistence.Id;

import lombok.Data;

@Data
public class Company {
	
	@Id
	private String id;
	
	private String companyName;
	private int foundingYear;
	private int employeesNum;
	private List<CompanyInfo> companyInfo;
	private List<RecruitmentNotice> recruitmentNotice;
}