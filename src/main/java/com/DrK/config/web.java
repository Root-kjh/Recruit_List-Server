package com.DrK.config;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class web extends AbstractAnnotationConfigDispatcherServletInitializer{

	
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {
				root.class,
				DB.class
		};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {servlet.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter=new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}
}
