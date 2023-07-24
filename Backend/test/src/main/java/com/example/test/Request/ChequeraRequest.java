package com.example.test.Request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ChequeraRequest implements Serializable {

    private int id;
    private int cuentaid;
    private String numero;
    private double cantidadcheques;

}
