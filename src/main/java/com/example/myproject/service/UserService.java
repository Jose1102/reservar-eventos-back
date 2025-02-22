package com.example.myproject.service;

import com.example.myproject.dto.UserDto;
import com.example.myproject.model.User;

import java.util.List;

public interface UserService {

    List<User> consultAllUser();

    User consultUserByEmail(String email);

    User addUser(UserDto userDto);
}
