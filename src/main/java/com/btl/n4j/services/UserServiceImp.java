package com.btl.n4j.services;

import com.btl.n4j.models.User;
import com.btl.n4j.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public User getUser(Integer userId) {
        try {
            User user = this.userRepository.findById(userId).get();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User findById(Integer userId) {
        return this.userRepository.findById(userId).get();
    }

    @Override
    public Boolean update(User user) {
        try {
            this.userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
