package com.dali.security.Controller;


import com.dali.security.Entity.User;
import com.dali.security.Service.IUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public User gettingUser(@RequestParam("idUser") Integer idUser){

        return userService.getUserById(idUser);
    }


    @DeleteMapping("delete/{idUser}")
    public void deletingUser(@PathVariable("idUser") Integer idUser){
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



}
