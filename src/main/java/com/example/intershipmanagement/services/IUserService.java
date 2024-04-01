package com.example.intershipmanagement.services;

import com.example.intershipmanagement.entities.Chat;
import com.example.intershipmanagement.entities.Message;
import com.example.intershipmanagement.entities.User;

import java.util.List;

public interface IUserService {
    User addUser(User user);
    List<User> getAllUsers();
    User getUserById(long idUser);
    void deleteUser(long idUser);
    User updateUser(User user);
    List<User> searchUsers(String searchText);
    boolean getUserOnlineStatus(Long userId);
    void updateUserOnlineStatus(Long userId, boolean online);

}
