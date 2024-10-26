package com.chancelu.ecommercewebsite.service.impl;

import com.chancelu.ecommercewebsite.dao.UserDao;
import com.chancelu.ecommercewebsite.dto.UserRegisterRequest;
import com.chancelu.ecommercewebsite.model.User;
import com.chancelu.ecommercewebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }
}
