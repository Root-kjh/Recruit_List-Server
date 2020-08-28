package com.DrK.DTO;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.PageRequest;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CompanyFilterDTO {
    
    @NotNull
    private boolean isRecruting;

    private int foundingYear;

    private int employeesNum;

    @NotNull
    private PageRequest page;
}