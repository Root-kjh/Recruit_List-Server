package com.DrK.DTO;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditPasswordDTO {
    
    @NotBlank
    private String password;
}
