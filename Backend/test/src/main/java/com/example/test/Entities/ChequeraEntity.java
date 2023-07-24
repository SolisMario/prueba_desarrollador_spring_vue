package com.example.test.Entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Chequera")
public class ChequeraEntity {
    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Cuenta_Id", nullable = false)
    private int cuentaid;
    @Column(name = "Numero", nullable = false)
    private String numero;
    @Column(name = "Cantidad_Cheques", nullable = false)
    private double cantidadcheques;
}
