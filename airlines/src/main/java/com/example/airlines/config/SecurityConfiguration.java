package com.example.airlines.config;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.airlines.controller.LoginController;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private DataSource dataSource;

	@Value("${spring.queries.users-query}")
	private String usersQuery;
	
	@Value("${spring.queries.supervizors-query}")
	private String supervizorsQuery;

	@Value("${spring.queries.admins-query}")
	private String adminsQuery;


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		if(LoginController.user) {
			LoginController.user=false;
		auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(usersQuery).dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
		}else if(LoginController.admin) {
			LoginController.admin=false;
			auth.jdbcAuthentication().usersByUsernameQuery(adminsQuery).authoritiesByUsernameQuery(adminsQuery).dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
		}else {
			LoginController.supervizor=false;
			auth.jdbcAuthentication().usersByUsernameQuery(supervizorsQuery).authoritiesByUsernameQuery(supervizorsQuery).dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
		}
	}

	//// @formatter:off



	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
				.authorizeRequests().antMatchers(HttpMethod.POST, "/auth/**").permitAll()
				//.and().authorizeRequests().antMatchers(HttpMethod.POST, "/api/**").authenticated()
				.and().authorizeRequests().anyRequest().authenticated()
				.and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout/**")).and()
				.exceptionHandling().accessDeniedPage("/access-denied");


	}

	// @formatter:on

	// asd

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}

}
