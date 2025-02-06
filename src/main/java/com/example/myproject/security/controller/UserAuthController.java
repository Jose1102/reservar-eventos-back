package com.example.myproject.security.controller;


import com.example.myproject.model.User;
import com.example.myproject.security.util.JwtUtil;
import com.example.myproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.io.IOException;

@RestController
@CrossOrigin(origins="*", allowedHeaders = "*")
public class UserAuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService service;


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/api/v1/token")
    public ResponseEntity<String> generateToken(@RequestParam String email) throws ServletException, IOException {

        User user = service.consultUserByEmail(email);

        try {
            if(user.getNombre()!=null){
                String token =  jwtUtil.generateToken(email);
                HttpHeaders headers = new HttpHeaders();
                headers.set("Authorization", "Bearer " + token);

                return new ResponseEntity<>("Token generado correctamente", headers, HttpStatus.OK);
            }else {
                return  new ResponseEntity<>("Token no se pudo generar", HttpStatus.BAD_REQUEST);

            }
        }catch (Exception exception){
            return new ResponseEntity<>("No se pudo generar el TOKEN, error al encontrar email", HttpStatus.BAD_REQUEST);
        }






    }


}
