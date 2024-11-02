package com.chancelu.ecommercewebsite.service;

import com.chancelu.ecommercewebsite.dto.UserLoginRequest;
import com.chancelu.ecommercewebsite.dto.UserRegisterRequest;
import com.chancelu.ecommercewebsite.model.User;


public interface UserService {

    User getUserById(Integer userId);

    Integer register(UserRegisterRequest userRegisterRequest);

    User login (UserLoginRequest userLoginRequest);

}
