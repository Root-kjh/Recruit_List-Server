package com.DrK.DTO;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserinfoDTO {
    
    private String userName;

    private String email;

    private Date signupDate;
}