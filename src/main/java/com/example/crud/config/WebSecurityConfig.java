package com.example.crud.config;




import java.util.Iterator;
import java.util.List;

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
import com.example.crud.model.Bill;
import com.example.crud.model.User;
import com.example.crud.repository.UserRoleRepository;
import com.example.crud.service.UserService;





@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http.csrf().disable().authorizeRequests()
       			
               .antMatchers("/").permitAll() 
               .antMatchers(HttpMethod.POST, "/login").permitAll() 
   	        	.antMatchers("/admin**").hasRole("ADMIN")
   	        	
//               .anyRequest().permitAll() 
               .anyRequest().authenticated() 
               .and()                        
               .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class) 
               .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
   }

  
   
   @Autowired
   UserService userService;
   @Autowired
   UserRoleRepository userRoleRepository;

   
   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//       auth.inMemoryAuthentication().withUser("aaa").password("{noop}qwerty").roles("ADMIN");
//       String test="qwerty2";
//       auth.inMemoryAuthentication().withUser("aaa2").password("{noop}"+test).roles("USER");
//       auth.inMemoryAuthentication().withUser("aaa4").password("{noop}"+test).roles("USER");
	   
	   List<User> userlist = userService.findAllUser();
       Iterator<User> iter = userlist.iterator();
       while (iter.hasNext()) {
    	   User user =iter.next();
    	   List<Integer> roleIds =userRoleRepository.findByUserId(user.getId());
    	   String username = user.getUsername();
    	   String password = user.getPassword();
    	   if(roleIds.contains(1)) {
    		   auth.inMemoryAuthentication().withUser(username).password("{noop}"+password).roles("ADMIN");
    	   }
    	   else
    	   {
    		   auth.inMemoryAuthentication().withUser(username).password("{noop}"+password).roles("USER");
    	   }
       }
   }
}