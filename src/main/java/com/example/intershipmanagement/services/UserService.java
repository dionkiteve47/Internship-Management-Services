package com.example.intershipmanagement.services;

import com.example.intershipmanagement.entities.Chat;
import com.example.intershipmanagement.entities.User;
import com.example.intershipmanagement.repositories.ChatRepository;
import com.example.intershipmanagement.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService implements IUserService{
    UserRepository userRepository;


    public List<User> searchUsers(String searchText) {
         return userRepository.findByNomUserContainingOrPrenomUserContaining(searchText, searchText);
    }
    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
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
        return  userRepository.save(user);
    }


}
