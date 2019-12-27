package com.example.crud.controller;


import com.example.crud.model.Role;
import com.example.crud.model.User;
import com.example.crud.model.UserRole;
import com.example.crud.repository.UserRoleRepository;
import com.example.crud.service.RoleService;
import com.example.crud.service.UserRoleService;
import com.example.crud.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserRoleController {
	
	@Autowired
    private UserRoleService userRoleService;
	
	@Autowired
    private UserRoleRepository userRoleRepository;
	
	@Autowired
	 private UserService userService;
	
	@Autowired
	 private RoleService roleService;


    @RequestMapping(value = "/admin/userRoles", method = RequestMethod.GET)
    public ResponseEntity<List<UserRole>> findAllUserRole() {
        List<UserRole> userRoles = userRoleService.findAllUserRole();
        if (userRoles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userRoles, HttpStatus.OK);
    }

 
    @RequestMapping(value = "/admin/userRole",
            method = RequestMethod.POST)
    public ResponseEntity<UserRole> createUserRole(@RequestBody UserRole userRole) {
    	Optional<User> user = userService.findById(userRole.getUserId());
    	userRole.setUserName(user.get().getUsername());
    	Optional<Role> role = roleService.findById(userRole.getRoleId());
    	userRole.setRoleName(role.get().getRolename());
        userRoleService.save(userRole);
        return new ResponseEntity<>(userRole, HttpStatus.CREATED);
    }



    @RequestMapping(value = "/admin/userRole/{userId}/{roleId}",
            method = RequestMethod.DELETE)
    public ResponseEntity<UserRole> deleteUserRole(
            @PathVariable("userId") Integer userId,@PathVariable("roleId") Integer roleId) {
    	userRoleRepository.deleteUR(userId,roleId);
        Optional<UserRole> userRole = userRoleService.findById(userId);
        if (!userRole.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userRoleService.remove(userRole.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

