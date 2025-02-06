package com.example.myproject.service.impl;

import com.example.myproject.model.User;
import com.example.myproject.repository.UserRepository;
import com.example.myproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> consultAllUser() {
        List<User> users = userRepository.findAll();
        // Verifica si hay resultados
        if (users.isEmpty()) {
            System.out.println("No hay usuarios en la base de datos.");
        }
        return users;
    }
}
