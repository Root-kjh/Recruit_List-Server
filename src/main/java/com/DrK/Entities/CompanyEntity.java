package com.DrK.Entities;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "Company")
public class CompanyEntity {
	
	@Id
	private String id;
	
	private String companyName;
	private int foundingYear;
	private int employeesNum;
	private List<CompanyInfoEntity> companyInfo;
	private List<RecruitmentNoticeEntity> recruitmentNotice;

	public boolean equals(CompanyEntity company) {
		return this.companyName.equals(company.getCompanyName()) &&
			this.employeesNum == company.getEmployeesNum() &&
			this.foundingYear == company.getFoundingYear();
	}
}