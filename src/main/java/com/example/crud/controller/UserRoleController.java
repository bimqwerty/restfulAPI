package com.example.crud.controller;


import com.example.crud.model.Role;
import com.example.crud.service.RoleService;
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
    private RoleService roleService;


    @RequestMapping(value = "/roles", method = RequestMethod.GET)
    public ResponseEntity<List<Role>> findAllProvider() {
        List<Role> roles = roleService.findAllRole();
        if (roles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @RequestMapping(value = "/role/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> getProviderById(
            @PathVariable("id") Integer id) {
        Optional<Role> role = roleService.findById(id);

        if (!role.isPresent()) {
            return new ResponseEntity<>(role.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(role.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/role",
            method = RequestMethod.POST)
    public ResponseEntity<Role> createProvider(@RequestBody Role role) {
        roleService.save(role);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/role/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Role> updateProvider(
            @PathVariable("id") Integer id,
            @RequestBody Role role) {
        Optional<Role> currentRole  = roleService.findById(id);

        if (!currentRole.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        currentRole.get().setRolename(role.getRolename());
        currentRole.get().setDescription(role.getDescription());
//        currentUser.get().setPassword(user.getAddress());
//        currentUser.get().set(user.getDescription());
//        currentUser.get().setTaxCode(user.getTaxCode());
//        currentUser.get().setEmail(user.getEmail());
//        currentUser.get().setPhoneNumber(user.getPhoneNumber());
//      currentUser.get().setDescription(user.getDescription());

      roleService.save(currentRole.get());
        return new ResponseEntity<>(currentRole.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/role/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Role> deleteProvider(
            @PathVariable("id") Integer id) {
        Optional<Role> role = roleService.findById(id);
        if (!role.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        roleService.remove(role.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

