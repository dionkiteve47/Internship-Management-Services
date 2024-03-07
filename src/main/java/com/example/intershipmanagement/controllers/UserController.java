package com.example.intershipmanagement.controllers;



import com.example.intershipmanagement.entities.User;
import com.example.intershipmanagement.services.IUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("api/user")
public class UserController {


     private IUserService userService;

    @PostMapping("add")
    public User addingUser(@RequestBody User user){
        return userService.addUser(user);
    }

    // Order 2
    @GetMapping("getAll")
    public List<User> gettingAllUser(){

        return userService.getAllUsers();
    }

    // Order 3
    @GetMapping("get")
    public User gettingUser(@RequestParam("idUser") long idUser){

        return userService.getUserById(idUser);
    }

    // Order 4
    @DeleteMapping("delete/{idUser}")
    public void deletingUser(@PathVariable("idUser") long idUser){
        userService.deleteUser(idUser);
    }

    // Order 5
    @PutMapping("update")
    public User updatingUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam String searchText) {
       return userService.searchUsers(searchText);

    }




}
