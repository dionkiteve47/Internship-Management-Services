package com.dali.security.Service;


import com.dali.security.Entity.User;

import java.util.List;

public interface IUserService {
    User addUser(User user);
    List<User> getAllUsers();
    User getUserById(Integer idUser);
    void deleteUser(Integer idUser);
    User updateUser(User user);
    List<User> searchUsers(String searchText);
    boolean getUserOnlineStatus(Integer userId);
    User updateUserOnlineStatus(Integer userId, boolean online);

    public Long getOfflineTime(Integer userId);


}
