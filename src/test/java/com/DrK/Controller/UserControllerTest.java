package com.DrK.Controller;

import com.DrK.Config.ControllerInit;
import com.DrK.DTO.SigninDTO;
import com.DrK.DTO.SignupDTO;
import com.DrK.Entities.CompanyEntity;
import com.DrK.Entities.UserEntity;
import com.DrK.Entities.UserLikeCompanyEntity;
import com.DrK.config.UrlConfig;
import com.DrK.lib.TestLib;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

@Log4j
public class UserControllerTest extends ControllerInit{
    
    @Test
    public void test() throws Exception{
        this.makeTestUser();
        String jwt = this.getJwt();
        log.info(jwt);
        this.mockMvc.perform(get(UrlConfig.User.getLikeCompany)
            .header(TOKEN_HEADER, jwt))
        .andDo(print())
        .andExpect(status().isOk());
    }

    @Test
    @Transactional
    public void signupTest() throws Exception{
        SignupDTO signupDTO = new SignupDTO();
        signupDTO.setEmail(TestLib.testUser.email);
        signupDTO.setUserName(TestLib.testUser.name);
        signupDTO.setPassword(TestLib.testUser.password);

        this.mockMvc.perform(post(UrlConfig.User.signup)
            .content(TestLib.asJsonString(signupDTO))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn().getResponse().getContentAsString().equals("true");

        assertEquals(
            this.userRepository.findAll().get(0).getName(),
            TestLib.testUser.name
        );
    }

    @Test
    @Transactional
    public void signinTest() throws Exception{
        this.makeTestUser();

        SigninDTO signinDTO = new SigninDTO();
        signinDTO.setUserName(TestLib.testUser.name);
        signinDTO.setPassword(TestLib.testUser.password);

        String jwt = this.mockMvc.perform(post(UrlConfig.User.signin)
            .content(TestLib.asJsonString(signinDTO))
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(print())
        .andReturn().getResponse().getContentAsString();

        log.info("jwt token");
        log.info(jwt);

        Authentication auth = this.jwtTokenProvider.getAuthentication(jwt);
        log.info(auth);
        assertEquals(((UserEntity)auth.getPrincipal()).getName(), TestLib.testUser.name);
        }

        @Test
        @Transactional
        public void addLikeCompanyTest() throws Exception{
            this.makeTestUser();
            this.makeTestCompany();

            CompanyEntity companyEntity = this.companyRepository.findAll().get(0);

            String jwt = this.getJwt();

            this.mockMvc.perform(put(UrlConfig.User.addLikeCompany)
                // .header(TOKEN_HEADER, jwt)
                .param("companyId", companyEntity.getId()))
            .andExpect(status().isOk())
            .andDo(print())
            .andReturn().getResponse().getContentAsString().equals("true");

            UserEntity userEntity = this.userRepository.findAll().get(0);
            List<UserLikeCompanyEntity> test = this.userLikeCompanyRepository.findAll();
            assertEquals(
                this.userLikeCompanyRepository.findByUserIdx(userEntity.getIdx()).get(0).getCompanyIdx(),
                companyEntity.getId()
            );
        }
}