package com.example.test.Repositories;

import com.example.test.Entities.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {
    public ClienteEntity findByIdIs(int id);
}
