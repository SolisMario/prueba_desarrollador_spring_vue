package com.example.test.Request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CuentaRequest implements Serializable {

    private int id;
    private int clienteid;
    private int tipocuentaid;
    private String numero;
    private double saldo;

}
