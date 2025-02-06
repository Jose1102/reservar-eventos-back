package com.example.myproject.service.impl;

import com.example.myproject.model.Reserva;
import com.example.myproject.model.User;
import com.example.myproject.repository.ReservaRepository;
import com.example.myproject.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    ReservaRepository reservaRepository;

    @Override
    public List<Reserva> consultAllReserva() {
        List<Reserva> reserva = reservaRepository.findAll();
        // Verifica si hay resultados
        if (reserva.isEmpty()) {
            System.out.println("No hay usuarios en la base de datos.");
        }
        return reserva;
    }
}
