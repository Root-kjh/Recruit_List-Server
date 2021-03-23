package com.DrK.lib;

import java.util.ArrayList;
import java.util.List;

import com.DrK.DTO.CompanyInfoDTO;
import com.DrK.DTO.CompanyInfoSiteDTO;
import com.DrK.DTO.RecruitNoticeDTO;
import com.DrK.Entities.CompanyEntity;
import com.DrK.Entities.CompanyInfoEntity;
import com.DrK.Entities.RecruitmentNoticeEntity;

public class EntityToInfoDTO {

    public static CompanyInfoDTO companyEntityToCompanyInfoDTO(CompanyEntity companyEntity){
        CompanyInfoDTO companyInfoDTO = new CompanyInfoDTO();

        companyInfoDTO.setCompanyName(companyEntity.getCompanyName());
        companyInfoDTO.setFoundingYear(companyEntity.getFoundingYear());
        companyInfoDTO.setEmployeesNum(companyEntity.getEmployeesNum());
        
        List<CompanyInfoSiteDTO> companyInfoSiteDTOs = new ArrayList<CompanyInfoSiteDTO>(companyEntity.getCompanyInfo().size());
        for (CompanyInfoEntity companyInfoEntity : companyEntity.getCompanyInfo()) {
            CompanyInfoSiteDTO companyInfoSiteDTO = new CompanyInfoSiteDTO();
            companyInfoSiteDTO.setSiteName(companyInfoEntity.getSiteName());
            companyInfoSiteDTO.setUri(companyInfoEntity.getUri());
            companyInfoSiteDTOs.add(companyInfoSiteDTO);
        }
        companyInfoDTO.setCompanyInfoSiteDTOs(companyInfoSiteDTOs);

        List<RecruitNoticeDTO> recruitNoticeDTOs = new ArrayList<RecruitNoticeDTO>(companyEntity.getRecruitmentNotice().size());
        for (RecruitmentNoticeEntity recruitmentNoticeEntity : companyEntity.getRecruitmentNotice()) {
            RecruitNoticeDTO recruitNoticeDTO = new RecruitNoticeDTO();
            recruitNoticeDTO.setSiteName(recruitmentNoticeEntity.getSiteName());
            recruitNoticeDTO.setUri(recruitmentNoticeEntity.getUri());
            recruitNoticeDTOs.add(recruitNoticeDTO);
        }
        companyInfoDTO.setRecruitNoticeDTOs(recruitNoticeDTOs);

        return companyInfoDTO;
    }
}
