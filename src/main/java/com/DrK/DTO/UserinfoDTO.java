package com.DrK.DTO;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserinfoDTO {
    
    @NotBlank
    private String userName;

    @NotBlank
    private String email;

    @NotBlank
    private String jwt;
}