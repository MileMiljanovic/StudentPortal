package com.ftn.student.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserSvc userService;

	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
	    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

    	http
    		.anonymous().disable()
		    .httpBasic()
		    .and()
		    .authorizeRequests()
		    .antMatchers(HttpMethod.POST, "/login").hasAnyRole("SEF", "KOORDINATOR", "ADMIN")
		    .antMatchers(HttpMethod.POST, "/api/formulari").hasAnyRole("SEF", "KOORDINATOR", "ADMIN")
		    .antMatchers(HttpMethod.PUT, "/api/formulari/*/koordinatorConfirm").hasAnyRole("KOORDINATOR", "ADMIN")
		    .antMatchers(HttpMethod.PUT, "/api/formulari/*/sefConfirm").hasAnyRole("SEF", "ADMIN")
//		    .antMatchers(HttpMethod.GET, "/departmani/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.GET, "/formular/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.GET, "/korisnik/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.GET, "/nastavnik/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.GET, "/predDomaci/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.GET, "/predStrani/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.GET, "/progDomaci/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.GET, "/progStrani/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.GET, "/student/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.GET, "/zamena/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.POST, "/departmani/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.POST, "/formular/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.POST, "/korisnik/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.POST, "/nastavnik/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.POST, "/predDomaci/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.POST, "/predStrani/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.POST, "/progDomaci/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.POST, "/progStrani/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.POST, "/student/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.POST, "/zamena/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.PUT, "/departmani/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.PUT, "/formular/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.PUT, "/korisnik/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.PUT, "/nastavnik/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.PUT, "/predDomaci/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.PUT, "/predStrani/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.PUT, "/progDomaci/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.PUT, "/progStrani/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.PUT, "/student/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.PUT, "/zamena/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.DELETE, "/departmani/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.DELETE, "/formular/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.DELETE, "/korisnik/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.DELETE, "/nastavnik/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.DELETE, "/predDomaci/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.DELETE, "/predStrani/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.DELETE, "/progDomaci/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.DELETE, "/progStrani/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.DELETE, "/student/**").hasRole("ADMIN")
//		    .antMatchers(HttpMethod.DELETE, "/zamena/**").hasRole("ADMIN")
		    .and()
		    .csrf().disable()
		    .formLogin().disable();
    }
}