package org.bildit.library.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("SELECT username, password, enabled from USERS where username=?")
				.authoritiesByUsernameQuery("SELECT username, role from USERS where username=?");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests().antMatchers("/", "/home").permitAll().antMatchers("/admin/**")
				.access("hasRole('ROLE_ADMIN')").antMatchers("/user/**")
				.access("hasRole('ROLE_PREMIUM') or hasRole('ROLE_ADMIN')").and().formLogin().loginPage("/login")
				.usernameParameter("username").passwordParameter("password").and().csrf().and().exceptionHandling()
				.accessDeniedPage("/no_admittance");
	}

}
