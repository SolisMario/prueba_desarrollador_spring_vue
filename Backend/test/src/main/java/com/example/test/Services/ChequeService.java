package com.example.test.Services;

import com.example.test.Entities.ChequeEntity;
import com.example.test.Entities.ChequeraEntity;
import com.example.test.Entities.CuentaEntity;
import com.example.test.Repositories.ChequeRepository;
import com.example.test.Repositories.ChequeraRepository;
import com.example.test.Repositories.CuentaRepository;
import com.example.test.Request.ChequeRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
public class ChequeService {
    private final ChequeRepository chequeRepository;
    private final ChequeraRepository chequeraRepository;
    private final CuentaRepository cuentaRepository;

    public ChequeService(ChequeRepository chequeRepository, ChequeraRepository chequeraRepository, CuentaRepository cuentaRepository) {
        this.chequeRepository = chequeRepository;
        this.chequeraRepository = chequeraRepository;
        this.cuentaRepository = cuentaRepository;
    }

    public ChequeEntity createCheque(ChequeRequest chequeRequest){
        ChequeEntity chequeEntity = new ChequeEntity();
        chequeEntity.setChequeraid(chequeRequest.getChequeraid());
        chequeEntity.setNumero(chequeRequest.getNumero());
        chequeEntity.setLugar(chequeRequest.getLugar());
        chequeEntity.setFecha(chequeRequest.getFecha());
        chequeEntity.setMonto(chequeRequest.getMonto());
        chequeEntity.setEstado(0);

        return chequeRepository.save(chequeEntity);
    }

    public ArrayList<ChequeEntity> readCheques() {
        return (ArrayList<ChequeEntity>) chequeRepository.findAll();
    }

    public ChequeEntity cancelCheque(ChequeRequest chequeRequest){
        Optional<ChequeEntity> chequeEntityOptional = chequeRepository.findById(chequeRequest.getId());
        if (chequeEntityOptional.isPresent()) {
            ChequeEntity chequeEntity = chequeEntityOptional.get();
            chequeEntity.setEstado(2);

            return chequeRepository.save(chequeEntity);
        } else {
            return null;
        }
    }

    public ChequeEntity pagarCheque(ChequeRequest chequeRequest){
        Optional<ChequeEntity> chequeEntityOptional = chequeRepository.findById(chequeRequest.getId());
        if (chequeEntityOptional.isPresent()) {
            ChequeEntity chequeEntity = chequeEntityOptional.get();
            Optional<ChequeraEntity> chequeraEntityOptional = chequeraRepository.findById(chequeEntity.getChequeraid());
            if (chequeraEntityOptional.isPresent()) {
                ChequeraEntity chequeraEntity = chequeraEntityOptional.get();
                Optional<CuentaEntity> cuentaEntityOptional = cuentaRepository.findById(chequeraEntity.getCuentaid());
                if (cuentaEntityOptional.isPresent()) {
                    CuentaEntity cuentaEntity = cuentaEntityOptional.get();
                    double saldoDespues = cuentaEntity.getSaldo() - chequeEntity.getMonto();
                    if (saldoDespues >= 0){
                        cuentaEntity.setSaldo(saldoDespues);
                        cuentaRepository.save(cuentaEntity);
                        chequeEntity.setEstado(1);
                        return chequeRepository.save(chequeEntity);
                    }
                }
            }
        }
        return null;
    }

    public ArrayList<ChequeEntity> GetChequesByChequeraId(int id){
        return chequeRepository.findByChequeraid(id);
    }
}
