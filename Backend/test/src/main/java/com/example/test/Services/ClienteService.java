package com.example.test.Services;

import com.example.test.Entities.ClienteEntity;
import com.example.test.Repositories.ClienteRepository;
import com.example.test.Request.CreateClientRequest;
import com.example.test.Request.UpdateClienteRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
public class ClienteService {
    private final ClienteRepository clienteRepository;
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteEntity createCliente(CreateClientRequest userRequest){
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setNombre(userRequest.getNombre());
        clienteEntity.setApellido(userRequest.getApellido());
        clienteEntity.setDireccion(userRequest.getDireccion());
        clienteEntity.setTelefono(userRequest.getTelefono());
        clienteEntity.setCorreo(userRequest.getCorreo());

        return clienteRepository.save(clienteEntity);
    }

    public ArrayList<ClienteEntity> readClientes() {
        return (ArrayList<ClienteEntity>) clienteRepository.findAll();
    }

    public ClienteEntity updateCliente(UpdateClienteRequest clienteRequest) {
        Optional<ClienteEntity> clienteEntityOptional = clienteRepository.findById(clienteRequest.getId());
        if (clienteEntityOptional.isPresent()) {
            ClienteEntity clienteEntity = clienteEntityOptional.get();
            clienteEntity.setNombre(clienteRequest.getNombre());
            clienteEntity.setApellido(clienteRequest.getApellido());
            clienteEntity.setDireccion(clienteRequest.getDireccion());
            clienteEntity.setTelefono(clienteRequest.getTelefono());
            clienteEntity.setTelefono(clienteRequest.getTelefono());

            return clienteRepository.save(clienteEntity);
        } else {
            return null;
        }
    }

    public void deleteCliente(int id){
        Optional<ClienteEntity> clienteEntityOptional = clienteRepository.findById(id);
        if (clienteEntityOptional.isPresent()) {
            clienteRepository.deleteById(id);
        }
    }

    public ClienteEntity getClienteById(int id){
        return clienteRepository.findByIdIs(id);
    }
}
