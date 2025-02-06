package com.example.myproject.contoller;


import com.example.myproject.model.Reserva;
import com.example.myproject.model.User;
import com.example.myproject.service.ReservaService;
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
public class ReservaController {


    @Autowired
    ReservaService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/api/v1/reservaAll")
    public ResponseEntity<List<Reserva>> consultAllReserva() {
        List<Reserva> reserva = service.consultAllReserva();
        // Verifica que users no sea null
        if (reserva != null && !reserva.isEmpty()) {
            System.out.println("Primer usuario: " + reserva.get(0).getNombre());
        } else {
            System.out.println("No se encontraron usuarios.");
        }

        return new ResponseEntity<>(reserva, HttpStatus.OK);


    }

}
