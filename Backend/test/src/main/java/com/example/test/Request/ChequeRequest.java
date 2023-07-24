package com.example.test.Request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class ChequeRequest implements Serializable {

    private int id;
    private int chequeraid;
    private String numero;
    private String lugar;
    private Date fecha;
    private double monto;
    private int estado;
}
