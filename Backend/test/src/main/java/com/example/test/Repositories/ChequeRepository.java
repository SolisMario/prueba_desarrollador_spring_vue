package com.example.test.Repositories;

import com.example.test.Entities.ChequeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface ChequeRepository extends JpaRepository<ChequeEntity, Integer> {
    public ArrayList<ChequeEntity> findByChequeraid(int id);
}
