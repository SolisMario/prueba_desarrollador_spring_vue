package com.example.test.Request;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CreateClientRequest implements Serializable {

    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private String correo;

}
