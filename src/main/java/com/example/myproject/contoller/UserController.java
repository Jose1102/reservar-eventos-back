package com.example.myproject.contoller;


import com.example.myproject.dto.UserDto;
import com.example.myproject.model.User;
import com.example.myproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.io.IOException;
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


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/api/v1/userByEmail")
    public ResponseEntity<User> consultUserByEmail(
            @RequestParam(required = true, name = "email") String email
    ) throws Exception {


        try{
            User users = service.consultUserByEmail(email);
            return new ResponseEntity<>(users, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/api/v1/addUser")
    public ResponseEntity<User> addUser(@RequestBody UserDto user) throws ServletException, IOException {
        try{
            User newUser = service.addUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


}