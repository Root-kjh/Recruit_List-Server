package com.DrK.entities;

import java.util.List;

import javax.persistence.Id;

import lombok.Data;

@Data
public class Company {
	
	@Id
	private String id;
	
	private String CompanyName;
	private String FoundingYear;
	private int EmployeesNum;
	private List<CompanyInfos> CompanyInfos;
	private List<RecruitmentNotices> RecruitmentNotices;
}