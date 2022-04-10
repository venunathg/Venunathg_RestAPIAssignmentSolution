package com.greatLearning.employeeRegistration.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.greatLearning.employeeRegistration.service.UserDetailsServiceImpl;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	 @Bean
	    public UserDetailsService userDetailsService() {
	        return new UserDetailsServiceImpl();
	    }
	     
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return NoOpPasswordEncoder.getInstance();
	    }
	     
	    @Bean
	    public DaoAuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(userDetailsService());
	        authProvider.setPasswordEncoder(passwordEncoder());
	         
	        return authProvider;
	    }
	 
	    @Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        auth.authenticationProvider(authenticationProvider());
	    }
	 
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.authorizeRequests()
	           // .antMatchers("/","/employees/save","/employees/showFormForAdd","/employees/403").hasAnyAuthority("USER","ADMIN")
	            .antMatchers("/employees/showFormForUpdate","/employees/delete").hasAuthority("ADMIN")
	            .anyRequest().authenticated()
	            .and()
	            .formLogin().loginProcessingUrl("/login").successForwardUrl("/employees/list").permitAll()
	            .and()
	            .logout().logoutSuccessUrl("/login").permitAll()
	            .and()
	            .exceptionHandling().accessDeniedPage("/employees/403")
	            .and()
	            .cors().and().csrf().disable();
	    }

}
