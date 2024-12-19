package com.btl.n4j.services;

import com.btl.n4j.models.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User findById(Integer userId);

    Boolean update(User user);
}
