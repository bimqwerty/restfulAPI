package com.example.crud.controller;


import com.example.crud.model.Provider;
import com.example.crud.model.User;
import com.example.crud.service.ProviderService;
import com.example.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
	
	@Autowired
    private UserService userService;


    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> findAllUser() {
        List<User> users = userService.findAllUser();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/user/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUserById(
            @PathVariable("id") Integer id) {
        Optional<User> user = userService.findById(id);

        if (!user.isPresent()) {
            return new ResponseEntity<>(user.get(),
                    HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/user",
            method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/admin/user/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(
            @PathVariable("id") Integer id,
            @RequestBody User user) {
        Optional<User> currentUser = userService.findById(id);

        if (!currentUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        currentUser.get().setUsername(user.getUsername());
//        currentUser.get().setPassword(user.getAddress());
//        currentUser.get().set(user.getDescription());
//        currentUser.get().setTaxCode(user.getTaxCode());
//        currentUser.get().setEmail(user.getEmail());
//        currentUser.get().setPhoneNumber(user.getPhoneNumber());
//      currentUser.get().setDescription(user.getDescription());

      userService.save(currentUser.get());
        return new ResponseEntity<>(currentUser.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/admin/user/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Provider> deleteUser(
            @PathVariable("id") Integer id) {
        Optional<User> user = userService.findById(id);
        if (!user.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.remove(user.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

