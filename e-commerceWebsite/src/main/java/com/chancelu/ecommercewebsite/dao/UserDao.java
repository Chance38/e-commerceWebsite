package com.chancelu.ecommercewebsite.dao;

import com.chancelu.ecommercewebsite.dto.UserRegisterRequest;
import com.chancelu.ecommercewebsite.model.User;

public interface UserDao {

    User getUserById(Integer userId);

    Integer createUser(UserRegisterRequest userRegisterRequest);
}
