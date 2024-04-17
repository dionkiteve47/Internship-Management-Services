package com.example.intershipmanagement.services;



import com.example.intershipmanagement.entities.User;

import com.example.intershipmanagement.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Scheduled;
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

    public boolean getUserOnlineStatus(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            return optionalUser.get().isOnline();
        } else {
            return false; // or return a default value based on your application logic
        }
    }

    public User updateUserOnlineStatus(Long userId, boolean online) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setOnline(online);
            return userRepository.save(user);
        } else {
            // User with the given ID was not found
            return null; // Or you can return a default user or handle it based on your requirement
        }
    }

    @Scheduled(fixedDelay = 10000) // Run every second (adjust the delay as needed)
    public void updateOfflineTimeForUsers() {
        List<User> allUsers = userRepository.findAll();
        for (User user : allUsers) {
            if (user.isOnline()) {
                user.setOfflineTimeInSeconds(0); // Reset offline time if user goes online
            } else {
                long currentOfflineTime = user.getOfflineTimeInSeconds(); // Get current offline time
                currentOfflineTime=currentOfflineTime+10; // Increment offline time by 1 second
                user.setOfflineTimeInSeconds(currentOfflineTime); // Update offline time for user
            }
            userRepository.save(user); // Save the updated user in the database
        }
    }
    @Override
    public Long getOfflineTime(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getOfflineTimeInSeconds(); // Assuming offline time is stored in seconds
        } else {
            return null; // Or handle the case where the user is not found in the database
        }
    }




}
