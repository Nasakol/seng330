//package org.springframework.samples.petclinic.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
//			throws Exception {
////		auth.inMemoryAuthentication().withUser("ketty@gmail.com").password("1234")
////				.roles("CUSTOMER");
////		auth.inMemoryAuthentication().withUser("lucy@company.com").password("1234")
////		.roles("STAFFMANAGER");
////		auth.inMemoryAuthentication().withUser("Mike@company.com").password("1234")
////		.roles("KITCHENMANAGER");
////		auth.inMemoryAuthentication().withUser("nancy@company.com").password("1234")
////		.roles("GENERALMANAGER");
////		auth.inMemoryAuthentication().withUser("ketty@company.com").password("1234")
////		.roles("NORMALEMPLOYEE");
////		
////		 // For User in database.
////        auth.userDetailsService(myDBAauthenticationService);
//	}
//
////	@Override
////	protected void configure(HttpSecurity http) throws Exception {
////		http.authorizeRequests().antMatchers("/login").permitAll()
////				.antMatchers("/", "/*todo*/**").access("hasRole('USER')").and()
////				.formLogin();
////	}
//}
//
//
//	   
