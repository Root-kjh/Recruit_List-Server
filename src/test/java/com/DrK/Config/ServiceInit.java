package com.DrK.Config;

import com.DrK.service.CompanyService;
import com.DrK.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

public class ServiceInit extends TestInit{

    @Autowired
    protected UserService userService;
    @Autowired
    protected CompanyService companyService;
}