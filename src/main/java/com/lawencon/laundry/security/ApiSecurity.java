package com.lawencon.laundry.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Rian Rivaldo
 */
@EnableWebSecurity
public class ApiSecurity extends WebSecurityConfigurerAdapter {

  private final ApiSecurityServiceImpl apiSecurityServiceImpl;

  @Autowired
  public ApiSecurity(ApiSecurityServiceImpl apiSecurityServiceImpl) {
	this.apiSecurityServiceImpl = apiSecurityServiceImpl;
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
	return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
	http.cors()
	    .and()
	    .csrf()
	    .disable()
	    .authorizeRequests()
	    .anyRequest()
	    .authenticated();

	http.addFilter(new AuthenticationFilter(super.authenticationManager()));
	http.addFilter(new AuthorizationFilter(super.authenticationManager()));
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(apiSecurityServiceImpl)
	    .passwordEncoder(bCryptPasswordEncoder());
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
	web.ignoring()
	    .antMatchers(HttpMethod.POST, "/api/user");
  }

}
