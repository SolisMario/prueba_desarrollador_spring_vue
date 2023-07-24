package com.example.test.Entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Cuenta")
public class CuentaEntity {
    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Cliente_Id", nullable = false)
    private int clienteid;
    @Column(name = "Tipo_Cuenta_Id", nullable = false)
    private int tipocuentaid;
    @Column(name = "Saldo", nullable = false)
    private double saldo;
    @Column(name = "Numero", nullable = false)
    private String numero;
}
