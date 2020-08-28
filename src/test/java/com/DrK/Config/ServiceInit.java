package com.DrK.Config;

import com.DrK.Service.CompanyService;
import com.DrK.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

public class ServiceInit extends TestInit{

    @Autowired
    protected UserService userService;
    @Autowired
    protected CompanyService companyService;
}