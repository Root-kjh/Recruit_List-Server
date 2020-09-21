package com.DrK.Config;

import java.util.Date;

import com.DrK.config.JWT.JwtTokenProvider;
import com.DrK.Entities.CompanyEntity;
import com.DrK.Entities.UserEntity;
import com.DrK.repositories.CompanyRepository;
import com.DrK.repositories.UserLikeCompanyRepository;
import com.DrK.repositories.UserRepository;
import com.DrK.lib.TestLib;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.DrK.config.root.class, com.DrK.config.DB.class, com.DrK.config.web.class})
@WebAppConfiguration
@Log4j
public class TestInit {
    
    // Repositories
    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected CompanyRepository companyRepository;
    @Autowired
    protected UserLikeCompanyRepository userLikeCompanyRepository;

    @Autowired
    protected PasswordEncoder passwordEncoder;
    @Autowired
    protected JwtTokenProvider jwtTokenProvider;

    @Before
    public void startTestAlert() {
        log.info("===========Start Test===========");
    }

    @After
    public void endTestAlert() {
        log.info("============End Test============");
    }

    // DB Init
    @Before
    @After
    public void clearDB(){
        log.info("ClearDB");
        userRepository.deleteAll();
        companyRepository.deleteAll();
        userLikeCompanyRepository.deleteAll();
    }

    public UserEntity makeTestUser() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(TestLib.testUser.email);
        userEntity.setName(TestLib.testUser.name);
        userEntity.setPassword(passwordEncoder.encode(TestLib.testUser.password));
        userEntity.setSignupDate(new Date());
        this.userRepository.save(userEntity);
        return userEntity;
    }

    public CompanyEntity makeTestCompany() throws Exception {
        CompanyEntity company = new CompanyEntity();
        company.setCompanyName(TestLib.testCompany.companyName);
        company.setFoundingYear(TestLib.testCompany.foundingYear);
        company.setEmployeesNum(TestLib.testCompany.employeesNum);
        this.companyRepository.save(company);
        return company;
    }

    public CompanyEntity makeTestCompany(String companyName) throws Exception {
        CompanyEntity company = new CompanyEntity();
        company.setCompanyName(companyName);
        company.setFoundingYear(TestLib.testCompany.foundingYear);
        company.setEmployeesNum(TestLib.testCompany.employeesNum);
        this.companyRepository.save(company);
        return company;
    }

}