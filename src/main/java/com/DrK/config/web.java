package com.DrK.Config;

import javax.servlet.Filter;

import com.DrK.Security.Cors;

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
		return new String[] {"/"};
	}
	
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter=new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		
		Cors cors=new Cors();
		
		return new Filter[]{encodingFilter, cors};
	}
}
