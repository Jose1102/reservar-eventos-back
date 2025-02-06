package com.example.myproject.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Table(name = "reserva")
@Entity
@Data
@AllArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // AUTO_INCREMENT en la base de datos
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false)
    private Boolean estado;

    public Reserva(){}
}
