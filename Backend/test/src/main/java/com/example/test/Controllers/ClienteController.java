package com.example.test.Controllers;

import com.example.test.Entities.ClienteEntity;
import com.example.test.Request.CreateClientRequest;
import com.example.test.Request.UpdateClienteRequest;
import com.example.test.Services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class ClienteController {
    @Autowired
    private final ClienteService clienteService;

    public ClienteController(ClienteService userService) {
        this.clienteService = userService;
    }

    @PostMapping()
    public ClienteEntity saveUser(@RequestBody CreateClientRequest userRequest){
        return clienteService.createCliente(userRequest);
    }

    @GetMapping()
    public ArrayList<ClienteEntity> readClientes() { return clienteService.readClientes(); }

    @PutMapping()
    public ClienteEntity updateCliente(@RequestBody UpdateClienteRequest clienteRequest) {
        return clienteService.updateCliente(clienteRequest);
    }

    @DeleteMapping("/{Id}")
    public void deleteCliente(@PathVariable("Id") int id) {
        clienteService.deleteCliente(id);
    }

    @GetMapping("byId/{Id}")
    public ClienteEntity getClienteById(@PathVariable("Id") int id) {
        return clienteService.getClienteById(id);
    }
}
