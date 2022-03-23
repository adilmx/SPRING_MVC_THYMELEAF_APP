package com.adilmx.spring_mvc_app.service.impl;

import com.adilmx.spring_mvc_app.entities.User;
import com.adilmx.spring_mvc_app.repository.UserRepo;
import com.adilmx.spring_mvc_app.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public User save(User user) {
        return userRepo.save(user);
    }
}
