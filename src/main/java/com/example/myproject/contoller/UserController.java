package com.example.myproject.contoller;


import com.example.myproject.model.User;
import com.example.myproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins="*", allowedHeaders = "*")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/api/v1/userAll")
    public ResponseEntity<List<User>> consultAllUser() {
        List<User> users = service.consultAllUser();
        // Verifica que users no sea null
        if (users != null && !users.isEmpty()) {
            System.out.println("Primer usuario: " + users.get(0).getNombre());
        } else {
            System.out.println("No se encontraron usuarios.");
        }

        return new ResponseEntity<>(users, HttpStatus.OK);


    }


}
