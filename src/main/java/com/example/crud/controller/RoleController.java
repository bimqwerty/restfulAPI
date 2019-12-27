package com.example.crud.controller;


import com.example.crud.model.Role;
import com.example.crud.model.UserRole;
import com.example.crud.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RoleController {
	
	@Autowired
    private UserRoleService userRoleService;


    @RequestMapping(value = "/userRoles", method = RequestMethod.GET)
    public ResponseEntity<List<UserRole>> findAllProvider() {
        List<UserRole> userRoles = userRoleService.findAllUserRole();
        if (userRoles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userRoles, HttpStatus.OK);
    }

    @RequestMapping(value = "/userRole/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserRole> getProviderById(
            @PathVariable("id") Integer id) {
        Optional<UserRole> userRole = userRoleService.findById(id);

        if (!userRole.isPresent()) {
            return new ResponseEntity<>(userRole.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userRole.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/userRole",
            method = RequestMethod.POST)
    public ResponseEntity<UserRole> createProvider(@RequestBody UserRole userRole) {
        userRoleService.save(userRole);
        return new ResponseEntity<>(userRole, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/userRole/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<UserRole> updateProvider(
            @PathVariable("id") Integer id,
            @RequestBody UserRole userRole) {
        Optional<UserRole> currentRole  = userRoleService.findById(id);

        if (!currentRole.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        currentRole.get().setUserName(userRole.getUserName());
        currentRole.get().setDescription(userRole.getDescription());
        currentRole.get().setRoleName(userRole.getRoleName());
        currentRole.get().setRoleId(userRole.getRoleId());
        currentRole.get().setUserId(userRole.getUserId());


      userRoleService.save(currentRole.get());
        return new ResponseEntity<>(currentRole.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/userRole/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<UserRole> deleteProvider(
            @PathVariable("id") Integer id) {
        Optional<UserRole> userRole = userRoleService.findById(id);
        if (!userRole.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userRoleService.remove(userRole.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

