package com.DrK.lib;

import com.DrK.repositories.CompanyRepository;
import com.DrK.repositories.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class TestLib {

    private UserRepository userRepository;

    private CompanyRepository companyRepository;

    public final class testUser{
        public final static String email = "test@test.com";
        public final static String name = "test";
        public final static String password = "testpw";    
    } 

    public final class newTestUser{
        public final static String email = "new@test.com";
        public final static String name = "new user";
        public final static String password = "newpw";    
    }

    public final class testCompany{
        public final static String companyName = "testCompany";
        public final static int foundingYear = 2020;
        public final static int employeesNum = 100;
    }

    public final class testCompanyInfo{
        public final static String siteName = "testSite";
        public final static String uri = "www.test.com";
    }

    public final class testRecruitmentNotice{
        public final static String siteName= "testRecruit";
        public final static String uri = "www.testRecruit.com";
    }

    public final void clearDB() {
        userRepository.deleteAll();
        companyRepository.deleteAll();
    }
}