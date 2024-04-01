package com.example.intershipmanagement.services;


import com.example.intershipmanagement.entities.User;

import com.example.intershipmanagement.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService implements IUserService {
    UserRepository userRepository;


    //SEARCH USER
    public List<User> searchUsers(String searchText) {
        return userRepository.findByNomUserContainingOrPrenomUserContaining(searchText, searchText);
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return /*(List<User>)*/ userRepository.findAll();
    }

    @Override
    public User getUserById(long idUser) {
        return userRepository.findById(idUser).get();
    }

    @Override
    public void deleteUser(long idUser) {
        userRepository.deleteById(idUser);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean getUserOnlineStatus(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.map(User::isOnline).orElse(false);
    }

    @Override
    public void updateUserOnlineStatus(Long userId, boolean online) {
        Optional<User> optionalUser = userRepository.findById(userId);
        optionalUser.ifPresent(user -> {
            user.setOnline(online);
            userRepository.save(user);
        });
    }
}
