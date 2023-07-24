package com.example.test.Entities;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Cliente")
public class ClienteEntity {
    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Nombre", nullable = false)
    private String nombre;
    @Column(name = "Apellido", nullable = false)
    private String apellido;
    @Column(name = "Correo", nullable = false)
    private String correo;
    @Column(name = "Direccion", nullable = false)
    private String direccion;
    @Column(name = "Telefono", nullable = false)
    private String telefono;
}