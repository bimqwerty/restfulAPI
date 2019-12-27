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
public class RoleController {
	
	@Autowired
    private RoleService roleService;


    @RequestMapping(value = "/admin/roles", method = RequestMethod.GET)
    public ResponseEntity<List<Role>> findAllRole() {
        List<Role> roles = roleService.findAllRole();
        if (roles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/role/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Role> getRoleById(
            @PathVariable("id") Integer id) {
        Optional<Role> role = roleService.findById(id);

        if (!role.isPresent()) {
            return new ResponseEntity<>(role.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(role.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/role",
            method = RequestMethod.POST)
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        roleService.save(role);
        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/admin/role/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Role> updateRole(
            @PathVariable("id") Integer id,
            @RequestBody Role role) {
        Optional<Role> currentRole  = roleService.findById(id);

        if (!currentRole.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        currentRole.get().setRolename(role.getRolename());
        currentRole.get().setDescription(role.getDescription());

      roleService.save(currentRole.get());
        return new ResponseEntity<>(currentRole.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/role/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Role> deleteRole(
            @PathVariable("id") Integer id) {
        Optional<Role> role = roleService.findById(id);
        if (!role.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        roleService.remove(role.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

