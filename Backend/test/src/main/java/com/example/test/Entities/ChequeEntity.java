package com.example.test.Entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "Cheque")
public class ChequeEntity {
    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Chequera_Id", nullable = false)
    private int chequeraid;
    @Column(name = "Numero", nullable = false)
    private String numero;
    @Column(name = "Lugar", nullable = false)
    private String lugar;
    @Column(name = "Fecha", nullable = false)
    private Date fecha;
    @Column(name = "Monto", nullable = false)
    private double monto;
    @Column(name = "Estado", nullable = true)
    private int estado;
}
