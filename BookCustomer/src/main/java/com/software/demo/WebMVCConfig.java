package com.software.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {
	String myExternalFilePath = "file:///E:/SpringbootExs/BooksExercise/uploads/";
	public void addResourceHandlers(final ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/uploads/**").addResourceLocations(myExternalFilePath);
	}
	
	

}
