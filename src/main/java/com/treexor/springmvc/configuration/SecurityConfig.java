package com.treexor.springmvc.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	  auth.inMemoryAuthentication().withUser("sinacceso").password("123456").roles("USER");
	  auth.inMemoryAuthentication().withUser("laura").password("123456").roles("ADMIN");
	  auth.inMemoryAuthentication().withUser("dani").password("123456").roles("SUPERADMIN");
	}
 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
 
	  http.authorizeRequests()
	  .antMatchers("/api/**").permitAll()
      .anyRequest().authenticated()
      .antMatchers("/j_spring_security_check").permitAll()
      .antMatchers("/apiSecure/**").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/apiSecure/**").access("hasRole('ROLE_SUPERADMIN')")
		.and().
		formLogin().
			usernameParameter("j_username").
			passwordParameter("j_password").loginProcessingUrl("/j_spring_security_check");
 
	}
	

}