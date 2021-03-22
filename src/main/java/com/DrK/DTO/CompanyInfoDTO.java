package com.DrK.DTO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyInfoDTO {
    
    private String companyName;
    private int foundingYear;
    private int employeesNum;
    private List<CompanyInfoSiteDTO> companyInfoSiteDTOs;
    private List<RecruitNoticeDTO> recruitNoticeDTOs;
}
