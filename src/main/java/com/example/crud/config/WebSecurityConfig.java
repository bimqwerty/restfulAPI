package com.example.crud.config;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.crud.filtes.JWTAuthenticationFilter;
import com.example.crud.filtes.JWTLoginFilter;
import com.example.crud.service.impl.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http.csrf().disable().authorizeRequests()
       
               .antMatchers("/").permitAll() 
               .antMatchers(HttpMethod.POST, "/login").permitAll() 
   	        	.antMatchers("/admin**").access("hasRole('ADMIN')")
   	        	.antMatchers("/user**").access("hasRole('USER') or hasRole('ADMIN')")
//               .anyRequest().permitAll() 
               .anyRequest().authenticated() 
               .and()                        
               .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class) 
               .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
   }

   BCryptPasswordEncoder bCryptPasswordEncoder;
   @Bean
   public BCryptPasswordEncoder passwordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
       return bCryptPasswordEncoder;
   }
   
   
   @Autowired
   UserDetailsServiceImpl userDetailsService;
	
   //Registra el service para usuarios y el encriptador de contrasena
   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

       // Setting Service to find User in the database.
       // And Setting PassswordEncoder
       auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());     
   }
   
   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//       auth.inMemoryAuthentication().withUser("aaa").password("{noop}qwerty").roles("ADMIN");
	     auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());     
   }
}