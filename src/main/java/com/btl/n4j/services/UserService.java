package com.btl.n4j.services;

import com.btl.n4j.models.User;

import java.util.List;

public interface UserService {
    User getUser(Integer userId);
    List<User> getAll();
    User findById(Integer id);
    Boolean update(User user);
}
