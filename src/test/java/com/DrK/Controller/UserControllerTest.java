package com.DrK.Controller;

import com.DrK.Config.ControllerInit;
import com.DrK.config.UrlConfig;

import org.junit.Test;

import lombok.extern.log4j.Log4j;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
}