package com.DrK.Config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Web extends AbstractAnnotationConfigDispatcherServletInitializer{
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {
				DB.class,
				Root.class
		};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {Servlet.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/recruit-list"};
	}
	
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter=new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		
		return new Filter[]{encodingFilter};
	}
}
