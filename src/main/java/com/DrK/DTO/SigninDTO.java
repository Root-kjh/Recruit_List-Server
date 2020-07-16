package com.DrK.DTO;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SigninDTO {
    
    @NotNull
    private String userName;
    
    @NotNull
    private String password;
}