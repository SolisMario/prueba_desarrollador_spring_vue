package com.example.test.Request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UpdateClienteRequest implements Serializable {

    private int id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String correo;

}
