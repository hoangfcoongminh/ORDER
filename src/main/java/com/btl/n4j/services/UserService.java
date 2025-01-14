package com.btl.n4j.services;

import com.btl.n4j.models.User;

public interface UserService {
    User getUser(Integer userId);
    Boolean update(User user);
}
