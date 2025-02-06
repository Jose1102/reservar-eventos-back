package com.example.myproject.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Table(name = "usuarios")
@Entity
@Data
@AllArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // AUTO_INCREMENT en la base de datos
    private Long id;

    @Column(nullable = false, length = 100)  // Especifica que no puede ser nulo y el tamaño de la columna
    private String nombre;

    @Column(nullable = false, length = 100, unique = true)  // Campo correo no nulo y único
    private String correo;

    public User(){

    }

}
