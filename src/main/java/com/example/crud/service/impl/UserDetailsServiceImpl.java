package com.example.crud.service.impl;

import com.example.crud.model.Role;
import com.example.crud.model.User;
import com.example.crud.model.UserRole;
import com.example.crud.repository.RoleRepository;
import com.example.crud.repository.UserRepository;
import com.example.crud.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRoleRepository userRoleRepository ;
	
    @Override
     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		

        User appUser = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));
		
        List<UserRole> userRoles=userRoleRepository.findByUserId(appUser.getId());
        
    List grantList = new ArrayList();
    for (use role: userRoles) {
        // ROLE_USER, ROLE_ADMIN,..
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRolename());
            grantList.add(grantedAuthority);
    }
	
    UserDetails user = (UserDetails) new org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(), grantList);
         return user;
    }
}
