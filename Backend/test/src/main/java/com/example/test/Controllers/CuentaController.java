package com.example.test.Controllers;

import com.example.test.Entities.CuentaEntity;
import com.example.test.Request.CuentaRequest;
import com.example.test.Services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/cuenta")
@CrossOrigin("*")
public class CuentaController {
    @Autowired
    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService) {
        this.cuentaService = cuentaService;
    }

    @PostMapping()
    public CuentaEntity saveCuenta(@RequestBody CuentaRequest cuentaRequest){
        return cuentaService.createChequera(cuentaRequest);
    }

    @GetMapping()
    public ArrayList<CuentaEntity> readCuentas() { return cuentaService.readCuentas(); }

    @PutMapping()
    public CuentaEntity updateCuenta(@RequestBody CuentaRequest cuentaRequest) {
        return cuentaService.updateCuenta(cuentaRequest);
    }

    @DeleteMapping("/{Id}")
    public void deleteCuenta(@PathVariable("Id") int id) {
        cuentaService.deleteCuenta(id);
    }

    @GetMapping("/{Id}")
    public ArrayList<CuentaEntity> getCuentasByCliente(@PathVariable("Id") int id) { return cuentaService.GetCuentasByClienteId(id); }
}
