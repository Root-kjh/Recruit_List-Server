package com.DrK.DTO;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignupDTO {
    
    @NotNull
    private String email;

    @NotNull
    private String userName;

    @NotNull
    private String password;
}