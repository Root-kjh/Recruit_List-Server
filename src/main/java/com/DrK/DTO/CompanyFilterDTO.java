package com.DrK.DTO;

import javax.validation.constraints.NotBlank;

import org.springframework.data.domain.PageRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CompanyFilterDTO {
    
    @NotBlank
    private boolean isRecruting;

    @NotBlank
    private int foundingYear;

    @NotBlank
    private int employeesNum;

    @NotBlank
    private PageRequest page;
}