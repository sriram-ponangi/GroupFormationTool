package com.group14.app.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.group14.app.services.UserPrincipalDetailsService;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserPrincipalDetailsService userPrincipalDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());
		daoAuthenticationProvider.setUserDetailsService(userPrincipalDetailsService);

		return daoAuthenticationProvider;
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// mention from most restrictive to least restrictive urls while authorization
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").antMatchers("/instructor/**")
				.hasAnyRole("INSTRUCTOR", "ADMIN").antMatchers("/ta/**").hasAnyRole("TA", "INSTRUCTOR", "ADMIN")
				.antMatchers("/student/**").hasAnyRole("STUDENT", "TA", "INSTRUCTOR", "ADMIN")
				.antMatchers("/", "/guest/**").hasAnyRole("GUEST", "STUDENT", "TA", "INSTRUCTOR", "ADMIN")
				.antMatchers("/resources/**").permitAll().and().formLogin().loginPage("/login").permitAll();

	}

}
