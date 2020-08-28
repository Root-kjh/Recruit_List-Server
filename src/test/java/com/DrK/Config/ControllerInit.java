package com.DrK.Config;

import com.DrK.DTO.SigninDTO;
import com.DrK.Service.CompanyService;
import com.DrK.Service.UserService;
import com.DrK.lib.TestLib;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class ControllerInit extends TestInit{
    
    @Autowired
    WebApplicationContext context;

    protected MockMvc mockMvc;
    
    @Autowired
    protected UserService userService;
    @Autowired
    protected CompanyService companyService;

    protected final static String TOKEN_HEADER = "X-AUTH-TOKEN";
    
    @Before
    public void mockmvcSetup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Before
    public void setTestDTO(){
        SigninDTO signinDTO = new SigninDTO();
        signinDTO.setUserName(TestLib.testUser.name);
        signinDTO.setPassword(TestLib.testUser.password);
    }

    public String getJwt() throws Exception {
        SigninDTO signinDTO = new SigninDTO();
        signinDTO.setUserName(TestLib.testUser.name);
        signinDTO.setPassword(TestLib.testUser.password);

        return this.userService.createToken(signinDTO);
    }

    public String getJwt(SigninDTO signinDTO) throws Exception{
        return this.userService.createToken(signinDTO);
    }
}