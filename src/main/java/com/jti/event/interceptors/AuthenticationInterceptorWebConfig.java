package com.jti.event.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class AuthenticationInterceptorWebConfig extends WebMvcConfigurerAdapter {

	@Autowired
	AdminInterceptor adminInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(adminInterceptor)
		.addPathPatterns(new String[] { "/adm/**" });
		

		/*
		.excludePathPatterns(
				new String[] { "/admin/**", "classpath:/admin/", "/front/**", "classpath:/front/" , "/images/**", "classpath:/images/" });
		;*/
	}
}
