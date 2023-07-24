package com.example.test.Repositories;

import com.example.test.Entities.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CuentaRepository extends JpaRepository<CuentaEntity, Integer> {
    public ArrayList<CuentaEntity> findByClienteid(int id);
}
