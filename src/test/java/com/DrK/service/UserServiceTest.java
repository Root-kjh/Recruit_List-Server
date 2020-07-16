package com.DrK.service;

import com.DrK.Service.UserService;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {com.DrK.Config.Root.class, com.DrK.Config.DB.class})
@Log4j
public class UserServiceTest {
	
	@Setter(onMethod_ = {@Autowired})
	private UserService userService;
	
}
