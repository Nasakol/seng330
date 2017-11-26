package org.springframework.samples.petclinic.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("ketty@gmail.com").password("1234")
				.roles("CUSTOMER");
		auth.inMemoryAuthentication().withUser("lucy@company.com").password("1234")
		.roles("STAFFMANAGER");
		auth.inMemoryAuthentication().withUser("Mike@company.com").password("1234")
		.roles("KITCHENMANAGER");
		auth.inMemoryAuthentication().withUser("nancy@company.com").password("1234")
		.roles("GENERALMANAGER");
		auth.inMemoryAuthentication().withUser("ketty@company.com").password("1234")
		.roles("NORMALEMPLOYEE");
		
//		 // For User in database.
//        auth.userDetailsService();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// The pages does not require login
        http.authorizeRequests().antMatchers("/", "/welcome", "/login", "/logout").permitAll();
 
		
		 // /userInfo page requires login as USER or ADMIN.
        // If no login, it will redirect to /login page.
        http.authorizeRequests().antMatchers("/userInfo").access("hasAnyRole('CUSTOMER', 'STAFFMANAGER', 'KITCHENMANAGER', 'GENERALMANAGER', 'NORMALEMPLOYEE')");
		
        // For ADMIN only.
        http.authorizeRequests().antMatchers("/admin").access("hasRole('CUSTOMER', 'STAFFMANAGER', 'KITCHENMANAGER', 'GENERALMANAGER', 'NORMALEMPLOYEE')");
        
     // When the user has logged in as XX.
        // But access a page that requires role YY,
        // AccessDeniedException will throw.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
 
        // Config for Login Form
        http.authorizeRequests().and().formLogin()//
                // Submit URL of login page.
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/userInfo")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Config for Logout Page
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/logoutSuccessful");
	}
}


	   
