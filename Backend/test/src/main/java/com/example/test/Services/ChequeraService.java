package com.example.test.Services;

import com.example.test.Entities.ChequeraEntity;
import com.example.test.Repositories.ChequeraRepository;
import com.example.test.Request.ChequeraRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
public class ChequeraService {
    private final ChequeraRepository chequeraRepository;

    public ChequeraService(ChequeraRepository chequeraRepository) {
        this.chequeraRepository = chequeraRepository;
    }

    public ChequeraEntity createChequera(ChequeraRequest chequeraRequest){
        ChequeraEntity chequeraEntity = new ChequeraEntity();
        chequeraEntity.setCuentaid(chequeraRequest.getCuentaid());
        chequeraEntity.setNumero(chequeraRequest.getNumero());
        chequeraEntity.setCantidadcheques(chequeraRequest.getCantidadcheques());

        return chequeraRepository.save(chequeraEntity);
    }

    public ArrayList<ChequeraEntity> readChequeras() {
        return (ArrayList<ChequeraEntity>) chequeraRepository.findAll();
    }

//    public ChequeraEntity updateChequera(ChequeraRequest chequeraRequest) {
//        Optional<ChequeraEntity> chequeraEntityOptional = chequeraRepository.findById(chequeraRequest.getId());
//        if (chequeraEntityOptional.isPresent()) {
//            ChequeraEntity chequeraEntity = chequeraEntityOptional.get();
//            chequeraEntity.setSaldo(chequeraRequest.getSaldo());
//
//            return chequeraRepository.save(chequeraEntity);
//        } else {
//            return null;
//        }
//    }

    public void deleteChequera(int id){
        Optional<ChequeraEntity> chequeraEntityOptional = chequeraRepository.findById(id);
        if (chequeraEntityOptional.isPresent()) {
            chequeraRepository.deleteById(id);
        }
    }

    public ArrayList<ChequeraEntity> GetChequerasByClienteId(int id_cliente){
        return chequeraRepository.findByCuentaid(id_cliente);
    }
}
