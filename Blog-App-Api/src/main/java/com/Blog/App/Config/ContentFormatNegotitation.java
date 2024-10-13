package com.Blog.App.Config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ContentFormatNegotitation implements WebMvcConfigurer {

	@Override
	 public void configureContentNegotiation(ContentNegotiationConfigurer config) {
		config.favorParameter(true)
		.parameterName("mediaType")
		.defaultContentType(MediaType.APPLICATION_JSON)
		.mediaType("json", MediaType.APPLICATION_JSON)
		.mediaType("xml", MediaType.APPLICATION_XML);
	}
}
