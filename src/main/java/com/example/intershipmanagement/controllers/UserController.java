package com.example.intershipmanagement.controllers;



import com.example.intershipmanagement.entities.User;
import com.example.intershipmanagement.services.IUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @GetMapping("getAll")
    public List<User> gettingAllUser(){

        return userService.getAllUsers();
    }


    @GetMapping("get")
    public User gettingUser(@RequestParam("idUser") long idUser){

        return userService.getUserById(idUser);
    }


    @DeleteMapping("delete/{idUser}")
    public void deletingUser(@PathVariable("idUser") long idUser){
        userService.deleteUser(idUser);
    }


    @PutMapping("update")
    public User updatingUser(@RequestBody User user){
        return userService.updateUser(user);
    }

    //SEARCH USER BY FIRSTNAME + LASTNAME USER
    @GetMapping("/search")
    public List<User> searchUsers(@RequestParam String searchText) {
       return userService.searchUsers(searchText);

    }


    @GetMapping("/{id}/online")
    public ResponseEntity<Boolean> getUserOnlineStatus(@PathVariable Long id) {
        boolean online = userService.getUserOnlineStatus(id);
        return ResponseEntity.ok(online);
    }
    @PutMapping("/{id}/online")
    public ResponseEntity<Void> updateUserOnlineStatus(@PathVariable Long id, @RequestParam boolean online) {
        userService.updateUserOnlineStatus(id, online);
        return ResponseEntity.noContent().build();
    }

}
