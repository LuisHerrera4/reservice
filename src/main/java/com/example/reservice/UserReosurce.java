package com.example.reservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UserReosurce.USERS)
public class UserReosurce {
    public static final String USERS= "/api/v0/users";

    @Autowired
    UserController userController;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers(){
        return ResponseEntity.ok().body(userController.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable int id){
        return ResponseEntity.ok().body(userController.getUser(id));
    }
/*
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDTO> removeUser(@PathVariable int id){
        UserDTO u =userController.getUser(id);
        userController.removeUser(id);
        return ResponseEntity.ok().body(u);
    }

 */

    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO user){
        userController.newUser(user);
        return ResponseEntity.ok().body(user);
    }
}
