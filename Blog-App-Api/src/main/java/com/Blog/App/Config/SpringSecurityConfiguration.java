package com.Blog.App.Config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.Blog.App.Config.JwtImplementation.JwtAuthenticationEntryPoint;
import com.Blog.App.Config.JwtImplementation.JwtAuthenticationFilter;

import jakarta.servlet.FilterRegistration;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebMvc
public class SpringSecurityConfiguration  {
	
	 public static final String[] PUBLIC_URLS = {"/api/auth/**", "/v3/api-docs", "/v2/api-docs",
	            "/swagger-resources/**", "/swagger-ui/**", "/webjars/**"

	    };

	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;
	
//latest way to do spring security configuration is Component based
	//allowing certain urls to be accessed with login in
	//adding filter so we can authenticate user before accessing the apis 
	
	@Bean
	public SecurityFilterChain chain(HttpSecurity http) throws Exception {
		
		 http.csrf(csrf -> csrf.disable())
		 				.authorizeHttpRequests(authorize -> authorize
    		   //Used to make urls public permitAll and requestMatcher
		 				.requestMatchers(PUBLIC_URLS)
		 				.permitAll()
		 				.requestMatchers("/v3/api-docs").permitAll()
		 				.requestMatchers(HttpMethod.GET).permitAll()
		 				.anyRequest()
		 				.authenticated())
		 				.exceptionHandling((exception)->exception.authenticationEntryPoint(jwtAuthenticationEntryPoint))
		 				.sessionManagement((sessionManagement)->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		 
		                http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		                http.authenticationProvider(daoAuthenticationProvider());
		                DefaultSecurityFilterChain defaultSecurity= http.build();
	                    return defaultSecurity;
		
	}
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    	provider.setUserDetailsService(customUserDetailsService);
    	provider.setPasswordEncoder(passwordEncoder());
    	return provider;
    } 

    @Bean
    AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
	
//enabling cors , so that our front end can access the backend apis
@Bean
public FilterRegistrationBean coresFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.setAllowCredentials(true);
    corsConfiguration.addAllowedOriginPattern("*");
    corsConfiguration.addAllowedHeader("Authorization");
    corsConfiguration.addAllowedHeader("Content-Type");
    corsConfiguration.addAllowedHeader("Accept");
    corsConfiguration.addAllowedMethod("POST");
    corsConfiguration.addAllowedMethod("GET");
    corsConfiguration.addAllowedMethod("DELETE");
    corsConfiguration.addAllowedMethod("PUT");
    corsConfiguration.addAllowedMethod("OPTIONS");
    corsConfiguration.setMaxAge(3600L);
    source.registerCorsConfiguration("/**", corsConfiguration);
    FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
    bean.setOrder(-110);
    return bean;
}
		
}
