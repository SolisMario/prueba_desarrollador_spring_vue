package com.example.test.Repositories;

import com.example.test.Entities.ChequeraEntity;
import com.example.test.Entities.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ChequeraRepository extends JpaRepository<ChequeraEntity, Integer> {
    public ArrayList<ChequeraEntity> findByCuentaid(int id);
}
