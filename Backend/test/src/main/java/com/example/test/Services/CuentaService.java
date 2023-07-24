package com.example.test.Services;

import com.example.test.Entities.CuentaEntity;
import com.example.test.Repositories.CuentaRepository;
import com.example.test.Request.CuentaRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
public class CuentaService {
    private final CuentaRepository cuentaRepository;

    public CuentaService(CuentaRepository cuentaRepository) {
        this.cuentaRepository = cuentaRepository;
    }

    public CuentaEntity createChequera(CuentaRequest cuentaRequest){
        CuentaEntity cuentaEntity = new CuentaEntity();
        cuentaEntity.setTipocuentaid(cuentaRequest.getTipocuentaid());
        cuentaEntity.setClienteid(cuentaRequest.getClienteid());
        cuentaEntity.setNumero(cuentaRequest.getNumero());
        cuentaEntity.setSaldo(cuentaRequest.getSaldo());

        return cuentaRepository.save(cuentaEntity);
    }

    public ArrayList<CuentaEntity> readCuentas() {
        return (ArrayList<CuentaEntity>) cuentaRepository.findAll();
    }

    public CuentaEntity updateCuenta(CuentaRequest cuentaRequest) {
        Optional<CuentaEntity> cuentaEntityOptional = cuentaRepository.findById(cuentaRequest.getId());
        if (cuentaEntityOptional.isPresent()) {
            CuentaEntity cuentaEntity = cuentaEntityOptional.get();
            cuentaEntity.setSaldo(cuentaRequest.getSaldo());

            return cuentaRepository.save(cuentaEntity);
        } else {
            return null;
        }
    }

    public void deleteCuenta(int id){
        Optional<CuentaEntity> cuentaEntityOptional = cuentaRepository.findById(id);
        if (cuentaEntityOptional.isPresent()) {
            cuentaRepository.deleteById(id);
        }
    }

    public ArrayList<CuentaEntity> GetCuentasByClienteId(int id_cliente){
        return cuentaRepository.findByClienteid(id_cliente);
    }
}
